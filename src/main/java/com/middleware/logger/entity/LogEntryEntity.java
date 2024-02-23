package com.middleware.logger.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;


@Entity
@Component
public class LogEntryEntity {

    private Long timeStamp;
    private String log;

    private String severity;

    public LogEntryEntity() {
        // Default constructor
    }

    public LogEntryEntity(Long timeStamp, String log) {
        this.timeStamp = timeStamp;
        this.log = log;
        this.severity= severity;

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
    public String getSeverity() {return severity;}
    public String setSeverity(String severity) {return this.severity;}

    public void setLog(String log) {
        this.log = log;
    }
}
