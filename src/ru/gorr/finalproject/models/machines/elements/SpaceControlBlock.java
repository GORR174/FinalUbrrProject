package ru.gorr.finalproject.models.machines.elements;

public class SpaceControlBlock extends SpaceMachineElement {
    @Override
    public float getWeight() {
        return 0.1f;
    }

    public static class Builder {
        private SpaceControlBlock spaceControlBlock = new SpaceControlBlock();

        public SpaceControlBlock build() {
            return spaceControlBlock;
        }
    }
}
