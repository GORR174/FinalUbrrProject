package ru.gorr.finalproject.models.machines.elements;

public class ConstructionElements extends SpaceMachineElement {
    private float weight = 10;

    @Override
    public float getWeight() {
        return weight;
    }

    public static class Builder {
        private ConstructionElements elements = new ConstructionElements();

        public Builder setWeight(float weight) {
            elements.weight = weight;

            return this;
        }

        public ConstructionElements build() {
            return elements;
        }
    }
}
