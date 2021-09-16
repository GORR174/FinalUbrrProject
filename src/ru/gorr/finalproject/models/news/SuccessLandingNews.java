package ru.gorr.finalproject.models.news;

import ru.gorr.finalproject.models.machines.Rocket;

public class SuccessLandingNews extends News {
    public SuccessLandingNews(Rocket rocket) {
        super(
            "Ракета \"" + rocket.getName() + "\" Выполнила Миссию!", 
            "Недавно отправленная на Луну ракета \"" + rocket.getName() + "\" долетела до Луны в целости!"
        );
    }
}
