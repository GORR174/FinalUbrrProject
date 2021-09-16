package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.machines.elements.Lander;
import ru.gorr.finalproject.models.machines.elements.RocketEngine;
import ru.gorr.finalproject.models.machines.elements.RocketStage;
import ru.gorr.finalproject.models.machines.elements.SpaceMachineElement;
import ru.gorr.finalproject.utils.SpaceConst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Rocket extends SpaceMachine {
    private final long timeStep = 100;

    private Queue<RocketStage> rocketStages = new ArrayDeque<>();
    private RocketStage brakeUnit;
    private Lander lander;

    private boolean isFinished = false;

    private double height = 0;

    private double velocity = 0;

    private double totalFlyTime = 0;

    public Rocket(String name) {
        super(name);
    }

    public float getWeight() {
        float rocketStagesWeight = rocketStages.stream().reduce(0f, (acc, stage) -> acc + stage.getWeight(), Float::sum);

        return rocketStagesWeight + brakeUnit.getWeight() + lander.getWeight();
    }
    
    public void fly() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getTelemetry().sendMessage("I'm flying! " + i);
        }
        
        getTelemetry().sendMessage("FINISHED");
    }

    private double last = 0;
    private void calculate(float timeScale) {
        double elapsedSeconds = (timeStep / 1000.0);
        totalFlyTime += elapsedSeconds;

        double earthGravityForce = SpaceConst.G * ((getWeight() * SpaceConst.EARTH_WEIGHT) / (getDistanceToEarthCenter() * getDistanceToEarthCenter()));
        double moonGravityForce = SpaceConst.G * ((getWeight() * SpaceConst.MOON_WEIGHT) / (getDistanceToMoonCenter() * getDistanceToMoonCenter()));

        if (rocketStages.peek() != null) {
            RocketStage currentStage = rocketStages.peek();
            RocketEngine engine = currentStage.getRocketEngine();

            currentStage.getFuelTank().reduceFuel((float) (engine.getFuelConsumption() * elapsedSeconds * timeScale));

            double jetThrustForce = engine.getGasExitRate() * engine.getFuelConsumption();

            double forcesSum = -earthGravityForce + moonGravityForce + jetThrustForce;

            double acceleration = forcesSum / getWeight();

            velocity = velocity + acceleration * elapsedSeconds * timeScale;

            if (velocity > 0) {
                height += velocity * elapsedSeconds * timeScale;
            }

            if (currentStage.getFuelTank().getWeight() == 0) {
                rocketStages.poll();

                getTelemetry().sendMessage("Отделил ступень " + (getDistanceToMoonCenter() - SpaceConst.MOON_RADIUS));
            }

            if (totalFlyTime - last >= 3) {
                last = totalFlyTime;
                getTelemetry().sendMessage("My velocity: " + velocity + "\nTo the Moon: " + (getDistanceToMoonCenter() - SpaceConst.MOON_RADIUS) + "\nCount: " + currentStage.getFuelTank().getWeight());
            }
        } else {
            double forcesSum = -earthGravityForce + moonGravityForce;

            double acceleration = forcesSum / getWeight();

            velocity = velocity + acceleration * elapsedSeconds * timeScale;

            if (velocity > 0) {
                height += velocity * elapsedSeconds * timeScale;
            }

            if (totalFlyTime - last >= 3) {
                last = totalFlyTime;
                getTelemetry().sendMessage("My velocity: " + velocity + "\nTo the Moon: " + (getDistanceToMoonCenter() - SpaceConst.MOON_RADIUS));
            }
        }

//        if (new Random().ne)
    }

    private double getDistanceToEarthCenter() {
        return SpaceConst.EARTH_RADIUS + height;
    }

    private double getDistanceToMoonCenter() {
        return SpaceConst.EARTH_MOON_DISTANCE - getDistanceToEarthCenter();
    }

    public void startFlyingThread(float timeScale) {
        new Thread(() -> {
            while (!isFinished) {
                calculate(timeScale);

                try {
                    Thread.sleep(timeStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            getTelemetry().sendMessage("FINISHED");
        }).start();

//        new Thread(this::fly).start();
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
