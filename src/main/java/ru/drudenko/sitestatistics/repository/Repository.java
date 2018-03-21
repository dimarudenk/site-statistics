package ru.drudenko.sitestatistics.repository;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Transactional
public interface Repository {
    void createAttendance(String userId, String pageId, Date createDate);

    long getNumberOfVisits(Date after, Date before);

    long getNumberOfUniqueUsers(Date after, Date before);

    long getNumberOfRegularUsers(Date after, Date before);
}
