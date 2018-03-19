package ru.drudenko.sitestatistics.services;

public class CurrentStatistics {
    //Общее количество посещений за текущие сутки
    private long numberOfVisits;
    //Количество уникальных пользователей за текущие сутки
    private long numberOfUniqueUsers;

    public CurrentStatistics(long numberOfVisits, long numberOfUniqueUsers) {
        this.numberOfVisits = numberOfVisits;
        this.numberOfUniqueUsers = numberOfUniqueUsers;
    }

    public long getNumberOfVisits() {
        return numberOfVisits;
    }

    public long getNumberOfUniqueUsers() {
        return numberOfUniqueUsers;
    }
}
