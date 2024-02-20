package com.middleware.logger.service;

import com.middleware.logger.entity.LogEntryEntity;
import com.middleware.logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    public ResponseEntity<String> ingestLogs(List<LogEntryEntity> logs) {
        try {
            // Sort logs by timestamp
            Collections.sort(logs, Comparator.comparingLong(LogEntryEntity::getTime));

            // Save sorted logs to S3
            return logRepository.saveLogsToS3(logs);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error ingesting logs: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<LogEntryEntity>> searchLogs(long start, long end, String text) {
        try {
            // Call the method in LogRepository to search logs in S3
            return logRepository.searchLogsInS3(start, end, text);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}