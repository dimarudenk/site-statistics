package ru.drudenko.sitestatistics.domain;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional
public interface Repository {
    Attendance createAttendance(String userId, String pageId, Instant createDate);

    long getNumberOfVisits(Instant after, Instant before);

    long getNumberOfUniqueUsers(Instant after, Instant before);

    long getNumberOfRegularUsers(Instant after, Instant before);
}
