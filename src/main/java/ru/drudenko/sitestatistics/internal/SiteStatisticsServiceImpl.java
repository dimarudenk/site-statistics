package ru.drudenko.sitestatistics.internal;

import ru.drudenko.sitestatistics.domain.Attendance;
import ru.drudenko.sitestatistics.domain.Repository;
import ru.drudenko.sitestatistics.services.CurrentStatistics;
import ru.drudenko.sitestatistics.services.Event;
import ru.drudenko.sitestatistics.services.PeriodStatistics;
import ru.drudenko.sitestatistics.services.SiteStatisticsService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class SiteStatisticsServiceImpl implements SiteStatisticsService {
    private final Repository repository;

    public SiteStatisticsServiceImpl(Repository repository) {
        this.repository = repository;
    }

    public void init(){
        repository.createAttendance("user1", "page1", Instant.parse("2018-01-01T01:00:00.03Z"));
        repository.createAttendance("user1", "page2", Instant.parse("2018-01-01T02:00:00.03Z"));
        repository.createAttendance("user1", "page3", Instant.parse("2018-01-01T03:00:00.03Z"));
        repository.createAttendance("user1", "page4", Instant.parse("2018-01-01T04:00:00.03Z"));
        repository.createAttendance("user1", "page5", Instant.parse("2018-01-01T05:00:00.03Z"));
        repository.createAttendance("user1", "page6", Instant.parse("2018-01-01T06:00:00.03Z"));
        repository.createAttendance("user1", "page7", Instant.parse("2018-01-01T07:00:00.03Z"));
        repository.createAttendance("user1", "page8", Instant.parse("2018-01-01T08:00:00.03Z"));
        repository.createAttendance("user1", "page9", Instant.parse("2018-01-01T09:00:00.03Z"));
        repository.createAttendance("user1", "page10", Instant.parse("2018-01-01T10:00:00.03Z"));

        repository.createAttendance("user2", "page1", Instant.parse("2018-01-01T01:00:00.03Z"));
        repository.createAttendance("user2", "page2", Instant.parse("2018-01-01T02:00:00.03Z"));
        repository.createAttendance("user2", "page3", Instant.parse("2018-01-01T03:00:00.03Z"));
        repository.createAttendance("user2", "page4", Instant.parse("2018-01-01T04:00:00.03Z"));
        repository.createAttendance("user2", "page5", Instant.parse("2018-01-01T05:00:00.03Z"));
        repository.createAttendance("user2", "page6", Instant.parse("2018-01-01T06:00:00.03Z"));
        repository.createAttendance("user2", "page7", Instant.parse("2018-01-01T07:00:00.03Z"));
        repository.createAttendance("user2", "page8", Instant.parse("2018-01-01T08:00:00.03Z"));
        repository.createAttendance("user2", "page9", Instant.parse("2018-01-01T09:00:00.03Z"));
        repository.createAttendance("user2", "page10", Instant.parse("2018-01-01T10:00:00.03Z"));

        repository.createAttendance("user3", "page1", Instant.parse("2018-01-01T01:00:00.03Z"));
        repository.createAttendance("user3", "page2", Instant.parse("2018-01-01T02:00:00.03Z"));
        repository.createAttendance("user3", "page3", Instant.parse("2018-01-01T03:00:00.03Z"));
        repository.createAttendance("user3", "page4", Instant.parse("2018-01-01T04:00:00.03Z"));
        repository.createAttendance("user3", "page5", Instant.parse("2018-01-01T05:00:00.03Z"));
        repository.createAttendance("user3", "page6", Instant.parse("2018-01-01T06:00:00.03Z"));
        repository.createAttendance("user3", "page7", Instant.parse("2018-01-01T07:00:00.03Z"));
        repository.createAttendance("user3", "page8", Instant.parse("2018-01-01T08:00:00.03Z"));
    }
    @Override
    public CurrentStatistics createEvent(Event event) {
       Attendance attendance = repository.createAttendance(event.getClientId(),event.getPageId(),Instant.now());

        long numberOfVisits = repository.getNumberOfVisits(attendance.getCreatedDate().truncatedTo(ChronoUnit.DAYS), attendance.getCreatedDate());
        long getNumberOfUniqueUsers = repository.getNumberOfUniqueUsers(attendance.getCreatedDate().truncatedTo(ChronoUnit.DAYS), attendance.getCreatedDate());
        return new CurrentStatistics(numberOfVisits,getNumberOfUniqueUsers);
    }

    @Override
    public PeriodStatistics getStatistics(LocalDateTime after, LocalDateTime before) {
        long numberOfVisits = repository.getNumberOfVisits(after.toInstant(ZoneOffset.ofHours(3)),before.toInstant(ZoneOffset.ofHours(3)));
        long numberOfUniqueUsers = repository.getNumberOfUniqueUsers(after.toInstant(ZoneOffset.ofHours(3)),before.toInstant(ZoneOffset.ofHours(3)));
        long numberOfRegularUsers = repository.getNumberOfRegularUsers(after.toInstant(ZoneOffset.ofHours(3)),before.toInstant(ZoneOffset.ofHours(3)));
        return new PeriodStatistics(numberOfVisits,numberOfUniqueUsers,numberOfRegularUsers);
    }
}
