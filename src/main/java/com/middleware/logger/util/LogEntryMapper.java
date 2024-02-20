package com.middleware.logger.util;

import com.middleware.logger.entity.LogEntryEntity;
import com.middleware.logger.dto.LogEntryDto;
public class LogEntryMapper {

    public static LogEntryEntity mapToEntity(LogEntryDto dto) {
        LogEntryEntity entity = new LogEntryEntity();
        entity.setTime(dto.getTime());
        entity.setLog(dto.getLog());
        return entity;
    }

    public static LogEntryDto mapToDTO(LogEntryEntity entity) {
        LogEntryDto dto = new LogEntryDto();
        dto.setTime(entity.getTime());
        dto.setLog(entity.getLog());
        return dto;
    }
}
