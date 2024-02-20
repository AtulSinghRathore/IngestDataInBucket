package com.middleware.logger.service;

import com.middleware.logger.dto.Dto;
import com.middleware.logger.dto.LogEntryDto;
import com.middleware.logger.entity.LogEntryEntity;
import com.middleware.logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;
    @Override


    public ResponseEntity<String> ingestLogs(Dto logs) {
        try {
            //Sort logs by timestamp
            List<LogEntryDto> logEntryDtos = logs.getLogEntryDtos();
            List<LogEntryEntity> logEntryEntities = new ArrayList<>();
            for (LogEntryDto logEntryDto : logEntryDtos) {
                // Performing the conversion here and add to the list
                LogEntryEntity logEntryEntity = new LogEntryEntity();
                logEntryEntity.setTimeStamp(logEntryDto.getTimeStamp());
                logEntryEntity.setLog(logEntryDto.getLog());
                logEntryEntities.add(logEntryEntity);
            }
            Collections.sort(logEntryEntities, Comparator.comparing(LogEntryEntity::getTimeStamp));

            // Save sorted logs to S3
            return logRepository.saveLogsToS3(logEntryEntities);
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