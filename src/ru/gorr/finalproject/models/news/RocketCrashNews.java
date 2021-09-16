package ru.gorr.finalproject.models.news;

import ru.gorr.finalproject.models.machines.Rocket;

public class RocketCrashNews extends News {
    public RocketCrashNews(Rocket rocket) {
        super(
            "Ракета \"" + rocket.getName() + "\" Потерпела Крушение!", 
            "Недавно отправленная на Луну ракета \"" + rocket.getName() + "\" потерпела неудачу.\nЦУП перестал получать какие-либо сообщения от ракеты"
        );
    }
}
