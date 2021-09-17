package ru.gorr.finalproject.models.telemetry;

public class TelemetryMessage {
    private String message;
    private String tag;
    private Type messageType;

    public TelemetryMessage(String tag, String message, Type messageType) {
        this.message = message;
        this.tag = tag;
        this.messageType = messageType;
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

    public Type getMessageType() {
        return messageType;
    }

    public enum Type {
        INFO, FINISHED, DISCONNECT_STAGE
    }
}
