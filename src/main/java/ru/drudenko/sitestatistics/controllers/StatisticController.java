package ru.drudenko.sitestatistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.drudenko.sitestatistics.repository.Repository;
import ru.drudenko.sitestatistics.rest.CurrentStatistics;
import ru.drudenko.sitestatistics.rest.Event;
import ru.drudenko.sitestatistics.rest.PeriodStatistics;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/services")
public class StatisticController {
    @Autowired
    private Repository repository;

    @RequestMapping(method = RequestMethod.POST, path = "/event")
    public CurrentStatistics createEvent(@RequestBody Event event) {
        Instant now = Instant.now();
        repository.createAttendance(event.getClientId(), event.getPageId(), Date.from(now));

        long numberOfVisits = repository.getNumberOfVisits(Date.from(now.truncatedTo(ChronoUnit.DAYS)), Date.from(now));
        long getNumberOfUniqueUsers = repository.getNumberOfUniqueUsers(Date.from(now.truncatedTo(ChronoUnit.DAYS)), Date.from(now));
        return new CurrentStatistics(numberOfVisits, getNumberOfUniqueUsers);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/statistics/{after}/{before}")
    PeriodStatistics getStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")@PathVariable("after") Date after, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")@PathVariable("before") Date before) {
        long numberOfVisits = repository.getNumberOfVisits(after, before);
        long numberOfUniqueUsers = repository.getNumberOfUniqueUsers(after, before);
        long numberOfRegularUsers = repository.getNumberOfRegularUsers(after, before);
        return new PeriodStatistics(numberOfVisits, numberOfUniqueUsers, numberOfRegularUsers);
    }
}
