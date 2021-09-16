package ru.gorr.finalproject.models.machines.elements;

public class RocketEngine extends SpaceMachineElement {
    private float gasExitRate = 3000;
    private float fuelConsumption = 3;

    @Override
    public float getWeight() {
        return 0.1f;
    }

    public float getGasExitRate() {
        return gasExitRate;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public static class Builder {
        private RocketEngine engine = new RocketEngine();

        public Builder setGasExitRate(float gasExitRate) {
            engine.gasExitRate = gasExitRate;

            return this;
        }

        public Builder setFuelConsumption(float fuelConsumption) {
            engine.fuelConsumption = fuelConsumption;

            return this;
        }

        public RocketEngine build() {
            return engine;
        }
    }
}
