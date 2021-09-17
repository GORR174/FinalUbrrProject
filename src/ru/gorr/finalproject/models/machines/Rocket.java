package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.machines.elements.Lander;
import ru.gorr.finalproject.models.machines.elements.RocketEngine;
import ru.gorr.finalproject.models.machines.elements.RocketStage;
import ru.gorr.finalproject.models.telemetry.TelemetryMessage;
import ru.gorr.finalproject.utils.SpaceConst;

import java.util.ArrayDeque;
import java.util.Queue;

public class Rocket extends SpaceMachine {
    private final long timeStep = 100;

    private Queue<RocketStage> rocketStages = new ArrayDeque<>();
    private int stagesCount;
    private RocketStage brakeUnit;
    private Lander lander;

    private boolean isFinished = false;

    private double height = 0;

    private double velocity = 0;

    private double realFlyTime = 0;
    private double lastMessageTime = 0;

    public Rocket(String name) {
        super(name);
    }

    public float getWeight() {
        float rocketStagesWeight = rocketStages.stream().reduce(0f, (acc, stage) -> acc + stage.getWeight(), Float::sum);

        return rocketStagesWeight + brakeUnit.getWeight() + lander.getWeight();
    }

    private void calculate(int timeScale) {
        double elapsedSeconds = (timeStep / 1000.0);
        realFlyTime += elapsedSeconds / (float) timeScale;

        double earthGravityForce = SpaceConst.G * ((getWeight() * SpaceConst.EARTH_WEIGHT) / (getDistanceToEarthCenter() * getDistanceToEarthCenter()));
        double moonGravityForce = SpaceConst.G * ((getWeight() * SpaceConst.MOON_WEIGHT) / (getDistanceToMoonCenter() * getDistanceToMoonCenter()));

        if (rocketStages.peek() != null) {
            RocketStage currentStage = rocketStages.peek();
            RocketEngine engine = currentStage.getRocketEngine();

            currentStage.getFuelTank().reduceFuel((float) (engine.getFuelConsumption() * elapsedSeconds));

            double jetThrustForce = engine.getGasExitRate() * engine.getFuelConsumption();

            double forcesSum = -earthGravityForce + moonGravityForce + jetThrustForce;

            double acceleration = forcesSum / getWeight();

            velocity = velocity + acceleration * elapsedSeconds;

            if (velocity > 0) {
                height += velocity * elapsedSeconds;
            }

            if (currentStage.getFuelTank().getWeight() == 0) {
                rocketStages.poll();

                getTelemetry().sendImportantMessage(
                        "Отделил ступень " + (getCurrentStageNumber() - 1),
                        TelemetryMessage.Type.DISCONNECT_STAGE
                );
            }

            if (realFlyTime - lastMessageTime >= 3) {
                lastMessageTime = realFlyTime;
                getTelemetry().sendMessage(
                        "Velocity: " + velocity + "; Acceleration: " + acceleration + "; Moon distance: " + getDistanceToMoon()
                                + "; fuel: " + currentStage.getFuelTank().getWeight() + "; Current stage: " + getCurrentStageNumber(),
                        TelemetryMessage.Type.INFO
                );
            }
        } else {
            double forcesSum = -earthGravityForce + moonGravityForce;

            double acceleration = forcesSum / getWeight();

            velocity = velocity + acceleration * elapsedSeconds;

            if (velocity > 0) {
                height += velocity * elapsedSeconds;
            }

            if (realFlyTime - lastMessageTime >= 3) {
                lastMessageTime = realFlyTime;
                getTelemetry().sendMessage(
                        "Velocity: " + velocity + "; Acceleration: " + acceleration
                                + "; Moon distance: " + getDistanceToMoon() + "; Current stage: Free fly",
                        TelemetryMessage.Type.INFO
                );
            }
        }

        if (getDistanceToMoon() <= 0) {
            isFinished = true;
        }
    }

    private double getDistanceToEarthCenter() {
        return SpaceConst.EARTH_RADIUS + height;
    }

    private double getDistanceToMoonCenter() {
        return SpaceConst.EARTH_MOON_DISTANCE - getDistanceToEarthCenter();
    }

    private double getDistanceToMoon() {
        return getDistanceToMoonCenter() - SpaceConst.MOON_RADIUS;
    }

    private int getCurrentStageNumber() {
        return stagesCount - rocketStages.size() + 1;
    }

    public void startFlyingThread(int timeScale) {
        new Thread(() -> {
            stagesCount = rocketStages.size();

            while (!isFinished) {
                for (int i = 0; i < timeScale; i++) {
                    if (!isFinished)
                        calculate(timeScale);
                }

                try {
                    Thread.sleep(timeStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            getTelemetry().sendImportantMessage("Fly complete", TelemetryMessage.Type.FINISHED);
        }).start();
    }

    public static class Builder {
        private Rocket rocket;

        public Builder(String name) {
            rocket = new Rocket(name);
        }

        public Builder addRocketStage(RocketStage rocketStage) {
            rocket.rocketStages.add(rocketStage);

            return this;
        }

        public Builder setBrakeUnit(RocketStage brakeUnit) {
            rocket.brakeUnit = brakeUnit;

            return this;
        }

        public Builder setLander(Lander lander) {
            rocket.lander = lander;

            return this;
        }

        public Rocket build() {
            return rocket;
        }
    }
}
