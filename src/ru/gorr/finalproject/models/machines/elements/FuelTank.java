package ru.gorr.finalproject.models.machines.elements;

public class FuelTank extends SpaceMachineElement{
    private float fuelQuantity = 40;

    @Override
    public float getWeight() {
        return fuelQuantity;
    }

    public void reduceFuel(float fuelQuantity) {
        this.fuelQuantity -= fuelQuantity;

        if (this.fuelQuantity < 0) {
            this.fuelQuantity = 0;
        }
    }

    public static class Builder {
        private FuelTank fuelTank = new FuelTank();

        public Builder setFuelQuantity(float fuelQuantity) {
            fuelTank.fuelQuantity = fuelQuantity;

            return this;
        }

        public FuelTank build() {
            return fuelTank;
        }
    }
}
