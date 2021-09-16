package ru.gorr.finalproject.models.machines.elements;

import ru.gorr.finalproject.models.machines.Moonwalker;

public class Lander extends SpaceMachineElement {
    private Moonwalker moonwalker;
    private SpaceControlBlock spaceControlBlock;
    private ConstructionElements constructionElements;

    @Override
    public float getWeight() {
        return moonwalker.getWeight() + spaceControlBlock.getWeight() + constructionElements.getWeight();
    }

    public Moonwalker getMoonwalker() {
        return moonwalker;
    }

    public SpaceControlBlock getSpaceControlBlock() {
        return spaceControlBlock;
    }

    public ConstructionElements getConstructionElements() {
        return constructionElements;
    }

    public static class Builder {
        private Lander lander = new Lander();

        public Builder setMoonwalker(Moonwalker moonwalker) {
            lander.moonwalker = moonwalker;

            return this;
        }

        public Builder setSpaceControlBlock(SpaceControlBlock controlBlock) {
            lander.spaceControlBlock = controlBlock;

            return this;
        }

        public Builder setConstructionElements(ConstructionElements constructionElements) {
            lander.constructionElements = constructionElements;

            return this;
        }

        public Lander build() {
            return lander;
        }
    }
}
