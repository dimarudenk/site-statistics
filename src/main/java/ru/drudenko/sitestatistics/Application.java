package ru.drudenko.sitestatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE ATTENDANCE(" +
                "id SERIAL, user_id VARCHAR(255), page_id VARCHAR(255), created_date TIMESTAMP )");
        List<Object[]> initEvents = new ArrayList<>();
        initEvents.add(new Object[]{"user1", "page1", Date.from(Instant.parse("2018-01-01T01:00:00.03Z"))});

        initEvents.add(new Object[]{"user1", "page2", Date.from(Instant.parse("2018-01-01T02:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page3", Date.from(Instant.parse("2018-01-01T03:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page4", Date.from(Instant.parse("2018-01-01T04:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page5", Date.from(Instant.parse("2018-01-01T05:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page6", Date.from(Instant.parse("2018-01-01T06:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page7", Date.from(Instant.parse("2018-01-01T07:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page8", Date.from(Instant.parse("2018-01-01T08:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page9", Date.from(Instant.parse("2018-01-01T09:00:00.03Z"))});
        initEvents.add(new Object[]{"user1", "page10", Date.from(Instant.parse("2018-01-01T10:00:00.03Z"))});

        initEvents.add(new Object[]{"user2", "page1", Date.from(Instant.parse("2018-01-01T01:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page2", Date.from(Instant.parse("2018-01-01T02:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page3", Date.from(Instant.parse("2018-01-01T03:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page4", Date.from(Instant.parse("2018-01-01T04:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page5", Date.from(Instant.parse("2018-01-01T05:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page6", Date.from(Instant.parse("2018-01-01T06:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page7", Date.from(Instant.parse("2018-01-01T07:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page8", Date.from(Instant.parse("2018-01-01T08:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page9", Date.from(Instant.parse("2018-01-01T09:00:00.03Z"))});
        initEvents.add(new Object[]{"user2", "page10", Date.from(Instant.parse("2018-01-01T10:00:00.03Z"))});

        initEvents.add(new Object[]{"user3", "page1", Date.from(Instant.parse("2018-01-01T01:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page2", Date.from(Instant.parse("2018-01-01T02:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page3", Date.from(Instant.parse("2018-01-01T03:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page4", Date.from(Instant.parse("2018-01-01T04:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page5", Date.from(Instant.parse("2018-01-01T05:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page6", Date.from(Instant.parse("2018-01-01T06:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page7", Date.from(Instant.parse("2018-01-01T07:00:00.03Z"))});
        initEvents.add(new Object[]{"user3", "page8", Date.from(Instant.parse("2018-01-01T08:00:00.03Z"))});

        jdbcTemplate.batchUpdate("INSERT INTO ATTENDANCE(user_id, page_id, created_date) VALUES (?,?,?)", initEvents);
    }
}
