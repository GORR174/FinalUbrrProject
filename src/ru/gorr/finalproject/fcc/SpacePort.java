package ru.gorr.finalproject.fcc;

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
        // TODO: ������������ ������� �������� ������, ������� ����� ������������
    }
    
    public void launch() {
        if (newsConsumer != null)
            newsConsumer.accept(generateLaunchNews());
        
        rocket.startFlyingThread(10);
    }
    
    private News generateLaunchNews() {
        String title = "������ \"" + rocket.getName() + "\" ���� ��������!";
        
        String message = "������ \"" + rocket.getName() + "\" ���� �������� �������.";
        
        return new News(title, message);
    }
}
