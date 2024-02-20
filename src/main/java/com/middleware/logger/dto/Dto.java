package com.middleware.logger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dto {
    @JsonProperty("Dto")
    List<LogEntryDto> logEntryDtos ;
    // Getter method for logEntryDtos
    public List<LogEntryDto> getLogEntryDtos() {
        return logEntryDtos;
    }
}
