package com.middleware.logger.controller;

import com.middleware.logger.entity.LogEntryEntity;
import com.middleware.logger.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestLogs(@RequestBody List<LogEntryEntity> logs) {
        try {
            return logService.ingestLogs(logs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error ingesting logs: " + e.getMessage());
        }
    }

    @GetMapping("/query")
    public ResponseEntity<List<LogEntryEntity>> searchLogs(@RequestParam long start, @RequestParam long end, @RequestParam String text) {
        try {
            return logService.searchLogs(start, end, text);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
