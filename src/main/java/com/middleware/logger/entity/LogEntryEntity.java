package com.middleware.logger.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Component
public class LogEntryEntity {

    private Long timeStamp;
    private String log;

    public LogEntryEntity() {
        // Default constructor
    }

    public LogEntryEntity(Long timeStamp, String log) {
        this.timeStamp = timeStamp;
        this.log = log;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
