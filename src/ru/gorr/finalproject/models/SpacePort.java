package ru.gorr.finalproject.models;

import java.util.function.Consumer;

import ru.gorr.finalproject.models.machines.Rocket;
import ru.gorr.finalproject.models.news.News;

public class SpacePort {
    
    private Rocket rocket;
    private Consumer<News> newsConsumer;
    
    public void setNewsConsumer(Consumer<News> newsConsumer) {
        this.newsConsumer = newsConsumer;
    }
    
    public void mount(Rocket rocket) {
        this.rocket = rocket;
        // TODO: Тестирование каждого элемента рокеты, вызывая метод тестирования
    }
    
    public void launch() {
        if (newsConsumer != null)
            newsConsumer.accept(generateLaunchNews());
        
        rocket.startFlyingThread(20);
    }
    
    private News generateLaunchNews() {
        String title = "Ракета \"" + rocket.getName() + "\" Была Запущена!";
        
        String message = "Ракета \"" + rocket.getName() + "\" была запущена сегодня.";
        
        return new News(title, message);
    }
}
