package ru.gorr.finalproject.models.machines;

import ru.gorr.finalproject.models.telemetry.Telemetry;

public class SpaceMachine {
    private final String name;

    private Telemetry telemetry;

    public SpaceMachine(String name) {
        this.name = name;
    }

    public void connectToTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public String getName() {
        return name;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }
}
