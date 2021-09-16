package ru.gorr.finalproject.models.machines.elements;

public class RocketStage extends SpaceMachineElement {

    private FuelTank fuelTank;
    private RocketEngine rocketEngine;
    private ConstructionElements constructionElements;

    public FuelTank getFuelTank() {
        return fuelTank;
    }

    public RocketEngine getRocketEngine() {
        return rocketEngine;
    }

    public ConstructionElements getConstructionElements() {
        return constructionElements;
    }

    @Override
    public float getWeight() {
        return fuelTank.getWeight() + rocketEngine.getWeight() + constructionElements.getWeight();
    }

    public static class Builder {
        private RocketStage rocketStage = new RocketStage();

        public Builder setFuelTank(FuelTank fuelTank) {
            rocketStage.fuelTank = fuelTank;

            return this;
        }

        public Builder setRocketEngine(RocketEngine rocketEngine) {
            rocketStage.rocketEngine = rocketEngine;

            return this;
        }

        public Builder setConstructionElements(ConstructionElements constructionElements) {
            rocketStage.constructionElements = constructionElements;

            return this;
        }

        public RocketStage build() {
            return rocketStage;
        }
    }
}
