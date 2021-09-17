package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.machines.elements.*;

import java.util.Random;

public class RocketFactory {
    public static Rocket createProtonRocket() {
        RocketStage firstRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(490000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(30000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(5800).setFuelConsumption(3900f).build())
                .build();

        RocketStage secondRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(200000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(11000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(4700).setFuelConsumption(1800f).build())
                .build();

        RocketStage thirdRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(65000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(10000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(5000).setFuelConsumption(100f).build())
                .build();

        RocketStage brakeUnit = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(20000f).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(600f).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(3200).setFuelConsumption(15f).build())
                .build();

        Moonwalker moonwalker = new Moonwalker("Луноход-1") {
            @Override
            public void moveForward() {

            }

            @Override
            public void moveBack() {

            }

            @Override
            public void turnLeft() {

            }

            @Override
            public void turnRight() {

            }

            @Override
            public int takeShot() {
                return new Random().nextInt(1000000);
            }
        };

        Lander lander = new Lander.Builder()
                .setSpaceControlBlock(new SpaceControlBlock.Builder().build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(100f).build())
                .setMoonwalker(moonwalker)
                .build();

        return new Rocket.Builder("Протон")
                .addRocketStage(firstRocketStage)
                .addRocketStage(secondRocketStage)
                .addRocketStage(thirdRocketStage)
                .setBrakeUnit(brakeUnit)
                .setBrakeUnitActivator(moonDistance -> moonDistance < 6.708815e6) //6.708815e6  6.662305e6
                .setLander(lander)
                .build();
    }
}
