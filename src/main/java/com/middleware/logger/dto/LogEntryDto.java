package com.middleware.logger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntryDto {

    @JsonProperty("timestamp")
    private Long timeStamp;
    @JsonProperty("log")
    private String log;

    @JsonProperty("severity")
    private Severity severity;

    public enum Severity {
        DEBUG,
        ERROR,
        WARN
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

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
}
