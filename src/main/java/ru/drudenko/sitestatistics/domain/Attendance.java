package ru.drudenko.sitestatistics.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "attendance_seq_gen")
    @SequenceGenerator(name = "attendance_seq_gen", sequenceName = "attendance_seq")
    private long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String pageId;
    @Column(nullable = false)
    private Instant createdDate;

    public Attendance() {
        // this form used by Hibernate
    }

    public Attendance(String userId, String pageId, Instant createdDate) {
        this.userId = userId;
        this.pageId = pageId;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPageId() {
        return pageId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}
