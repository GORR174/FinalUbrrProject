package ru.gorr.finalproject.models.telemetry;

public class TelemetryMessage {
    private String message;
    private String tag;

    public TelemetryMessage(String tag, String message) {
        this.message = message;
        this.tag = tag;
    }
    
    public String getFormattedMessage() {
        return "Telemetry (" + tag + "): " + message;
    }

    public String getMessage() {
        return message;
    }

    public String getTag() {
        return tag;
    }
}
