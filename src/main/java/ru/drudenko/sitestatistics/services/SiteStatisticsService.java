package ru.drudenko.sitestatistics.services;

import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Transactional
public interface SiteStatisticsService {

    //Создание события посещения сайта пользователем. Параметры:
    //Идентификатор пользователя
    //Идентификатор страницы сайта
    @POST
    @Path("/event")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CurrentStatistics createEvent(Event event);

    //Получение статистики посещения за произвольный период. Параметр запроса:
    //период  учёта
    @GET
    @Path("/statistics/{after}/{before}")
    @Produces(MediaType.APPLICATION_JSON)
    PeriodStatistics getStatistics(@PathParam("after") LocalDateTime after, @PathParam("before") LocalDateTime before);
}
