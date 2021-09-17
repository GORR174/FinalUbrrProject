package ru.gorr.finalproject.models.telemetry;

import ru.gorr.finalproject.models.machines.Rocket;
import ru.gorr.finalproject.models.machines.SpaceMachine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;

public class Telemetry<T extends SpaceMachine> extends Thread {
    private String tag;
    private long period;
    
    private Deque<TelemetryMessage> messages = new ArrayDeque<>();
    
    private T machine;
    
    private BiConsumer<T, TelemetryMessage> messageConsumer;
    
    private boolean isClosed = false;
    
    public Telemetry(String tag, long period, T machine, BiConsumer<T, TelemetryMessage> messageConsumer) {
        this.tag = tag;
        this.period = period;
        this.messageConsumer = messageConsumer;
        this.machine = machine;

        machine.connectToTelemetry(this);
    }

    public String getTag() {
        return tag;
    }
    
    @Override
    public void run() {
        while (!isClosed) {
            while (messages.size() > 0) {
                messageConsumer.accept(machine, messages.pollFirst());
            }
    
            try {
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void close() {
        isClosed = true;
    }
    
    public void sendMessage(String message, TelemetryMessage.Type messageType) {
        messages.add(new TelemetryMessage(tag, message, messageType));
    }

    public void sendImportantMessage(String message, TelemetryMessage.Type messageType) {
        messageConsumer.accept(machine, new TelemetryMessage(tag, message, messageType));
    }
}
