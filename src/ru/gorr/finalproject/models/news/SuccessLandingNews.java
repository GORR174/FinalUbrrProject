package ru.gorr.finalproject.models.news;

import ru.gorr.finalproject.models.machines.Rocket;

public class SuccessLandingNews extends News {
    public SuccessLandingNews(Rocket rocket) {
        super(
            "������ \"" + rocket.getName() + "\" ��������� ������!", 
            "������� ������������ �� ���� ������ \"" + rocket.getName() + "\" �������� �� ���� � �������!"
        );
    }
}
