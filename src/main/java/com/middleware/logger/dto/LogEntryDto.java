package com.middleware.logger.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LogEntryDto {
    private long time;
    private String log;

    public void setTime(long time) {
        this.time = time;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public long getTime() {
        return time;
    }

    public String getLog() {
        return log;
    }
}