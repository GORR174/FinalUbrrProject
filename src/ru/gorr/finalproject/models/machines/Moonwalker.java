package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.machines.elements.SpaceMachineElement;

public abstract class Moonwalker extends SpaceMachine {
    public Moonwalker(String name) {
        super(name);
    }

    @Override
    public float getWeight() {
        return 1;
    }

    public abstract void moveForward();
    public abstract void moveBack();
    public abstract void turnLeft();
    public abstract void turnRight();

    public abstract int takeShot();
}
