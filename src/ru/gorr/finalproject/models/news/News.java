package ru.gorr.finalproject.models.news;

public class News {
    private String title;
    private String message;

    public News(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
    
    public void print() {
        System.out.println("===========================");
        System.out.println("Новость дня: " + title);
        System.out.println("\n" + message);
        System.out.println("===========================");
    }
}
