package ru.drudenko.sitestatistics.rest;

public class PeriodStatistics extends CurrentStatistics {
    // Количество постоянных пользователей за указанный период
    // (пользователей, которые за период просмотрели не менее 10 различных страниц).
    private long numberOfRegularUsers;

    public PeriodStatistics(long numberOfVisits, long numberOfUniqueUsers, long numberOfRegularUsers) {
        super(numberOfVisits, numberOfUniqueUsers);
        this.numberOfRegularUsers = numberOfRegularUsers;
    }

    public long getNumberOfRegularUsers() {
        return numberOfRegularUsers;
    }
}
