package com.middleware.logger.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
@Data
public class LogEntryEntity {
    private long time;
    private String log;


    public long getTime() {
        return time;
    }

    public String getLog() {
        return log;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
