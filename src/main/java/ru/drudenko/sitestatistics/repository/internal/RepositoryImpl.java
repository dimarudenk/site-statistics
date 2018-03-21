package ru.drudenko.sitestatistics.repository.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.drudenko.sitestatistics.repository.Repository;

import java.util.Date;

@Component("repository")
public class RepositoryImpl implements Repository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAttendance(String userId, String pageId, Date createDate) {
        jdbcTemplate.update("INSERT INTO ATTENDANCE(user_id, page_id, created_date) VALUES (?,?,?)", new Object[]{userId, pageId, createDate});
    }

    @Override
    public long getNumberOfVisits(Date after, Date before) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ATTENDANCE WHERE created_date >= ? AND created_date <= ?", new Object[]{after, before}, Long.class);
    }

    @Override
    public long getNumberOfUniqueUsers(Date after, Date before) {
        return jdbcTemplate.queryForObject("SELECT count(DISTINCT user_id) FROM ATTENDANCE WHERE created_date >= ? AND created_date <= ?", new Object[]{after, before}, Long.class);
    }

    @Override
    public long getNumberOfRegularUsers(Date after, Date before) {
        String sql = "select count(r.user) from (" +
                "select user_id as user, count( DISTINCT page_id) as countPage from attendance " +
                "where  created_date >= ? AND created_date <= ? group by user_id " +
                ") r where r.countPage > 9";
        return jdbcTemplate.queryForObject(sql, new Object[]{after, before}, Long.class);
    }
}
