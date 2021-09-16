package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.machines.elements.*;

public class RocketFactory {
    public static Rocket createProtonRocket() {
        RocketStage firstRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(490000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(30000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(5800).setFuelConsumption(3900f).build())
                .build();

        RocketStage secondRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(207000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(11000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(4700).setFuelConsumption(1800f).build())
                .build();

        RocketStage thirdRocketStage = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(73000).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(10000).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(5000).setFuelConsumption(100f).build())
                .build();

        RocketStage brakeUnit = new RocketStage.Builder()
                .setFuelTank(new FuelTank.Builder().setFuelQuantity(300f).build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(600f).build())
                .setRocketEngine(new RocketEngine.Builder().setGasExitRate(3000).setFuelConsumption(30f).build())
                .build();

        Lander lander = new Lander.Builder()
                .setSpaceControlBlock(new SpaceControlBlock.Builder().build())
                .setConstructionElements(new ConstructionElements.Builder().setWeight(100f).build())
                .setMoonwalker(new Moonwalker())
                .build();

        return new Rocket.Builder("Протон")
                .addRocketStage(firstRocketStage)
                .addRocketStage(secondRocketStage)
                .addRocketStage(thirdRocketStage)
                .setBrakeUnit(brakeUnit)
                .setLander(lander)
                .build();
    }
}
