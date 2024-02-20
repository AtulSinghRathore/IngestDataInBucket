package com.middleware.logger.service;

import com.middleware.logger.dto.Dto;
import com.middleware.logger.entity.LogEntryEntity;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface LogService {

    ResponseEntity<String> ingestLogs(Dto logs);

    ResponseEntity<List<LogEntryEntity>> searchLogs(long start, long end, String text);
}
