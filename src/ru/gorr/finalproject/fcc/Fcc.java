package ru.gorr.finalproject.fcc;

import ru.gorr.finalproject.models.machines.RocketFactory;
import ru.gorr.finalproject.models.news.News;
import ru.gorr.finalproject.models.machines.Rocket;
import ru.gorr.finalproject.models.telemetry.Telemetry;
import ru.gorr.finalproject.models.telemetry.TelemetryMessage;
import ru.gorr.finalproject.models.news.RocketCrashNews;
import ru.gorr.finalproject.models.news.SuccessLandingNews;

public class Fcc extends Thread {
    private long lastRocketMessageTime = Long.MAX_VALUE;
    private final long rocketMessageTimeout = 5000;

    private boolean shouldExit = false;
    
    public void recieveRocketMessageFromTelemetry(Rocket rocket, TelemetryMessage telemetryMessage) {
        System.out.println(telemetryMessage.getFormattedMessage());
        
        lastRocketMessageTime = System.currentTimeMillis();

        if (telemetryMessage.getMessageType() == TelemetryMessage.Type.FINISHED) {
            shouldExit = true;
            new SuccessLandingNews(rocket).print();
        }
    }
    
    @Override
    public void run() {
        Rocket rocket = RocketFactory.createProtonRocket();
        
        Telemetry<Rocket> rocketTelemetry = new Telemetry<>("Rocket", 1000, rocket, this::recieveRocketMessageFromTelemetry);
        rocketTelemetry.start();
        
        SpacePort port = new SpacePort();
        
        port.setNewsConsumer(News::print);
        
        port.mount(rocket);
        
        port.launch();
        
        while (!shouldExit) {
            try {
                if (checkRocketTelemetryTimeout()) {
                    new RocketCrashNews(rocket).print();
                    shouldExit = true;
                }
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        rocketTelemetry.close();
    }
    
    private boolean checkRocketTelemetryTimeout() {
        return System.currentTimeMillis() - lastRocketMessageTime > rocketMessageTimeout;
    }
}
