package ru.gorr.finalproject.models.news;

import ru.gorr.finalproject.models.machines.Rocket;

public class RocketCrashNews extends News {
    public RocketCrashNews(Rocket rocket) {
        super(
            "������ \"" + rocket.getName() + "\" ��������� ��������!", 
            "������� ������������ �� ���� ������ \"" + rocket.getName() + "\" ��������� �������.\n��� �������� �������� �����-���� ��������� �� ������"
        );
    }
}
