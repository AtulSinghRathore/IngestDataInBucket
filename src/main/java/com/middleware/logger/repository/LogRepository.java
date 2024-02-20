package com.middleware.logger.repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.middleware.logger.entity.LogEntryEntity;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepository{

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;


    public ResponseEntity<String> saveLogsToS3(List<LogEntryEntity> logs) {
        try {
            AmazonS3 s3Client = getS3Client();

            for (LogEntryEntity log : logs) {
                // Convert log to JSON or text format as per requirement
                String logData = "{\"time\":" + log.getTime() + ",\"log\":\"" + log.getLog() + "\"}";

                // Upload log data to S3
                s3Client.putObject(bucketName, "logs/" + log.getTime() + ".json", new ByteArrayInputStream(logData.getBytes()), null);
            }

            return ResponseEntity.ok("Logs ingested and saved to S3 successfully.");
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving logs to S3: " + e.getMessage());
        }
    }


    public ResponseEntity<List<LogEntryEntity>> searchLogsInS3(long start, long end, String text) {
        try {
            AmazonS3 s3Client = getS3Client();
            List<LogEntryEntity> searchResults = new ArrayList<>();

            // Specify S3 bucket and prefix for log files
            String prefix = "logs/";

            // Get objects in S3 bucket matching the specified prefix
            ObjectListing objectListing = s3Client.listObjects(bucketName, prefix);
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                S3Object s3Object = s3Client.getObject(bucketName, objectSummary.getKey());
                S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

                // Read log data from S3 object
                byte[] bytes = objectInputStream.readAllBytes();
                String logData = new String(bytes);

                // Parse log data and check if it matches the search criteria
                LogEntryEntity LogEntryEntity = parseLogEntryEntity(logData);
                if (LogEntryEntity != null && LogEntryEntity.getTime() >= start && LogEntryEntity.getTime() <= end && LogEntryEntity.getLog().contains(text)) {
                    searchResults.add(LogEntryEntity);
                }
            }

            return ResponseEntity.ok(searchResults);
        } catch (AmazonServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private AmazonS3 getS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("s3.amazonaws.com", "us-east-1"))
                .build();
    }

    private LogEntryEntity parseLogEntryEntity(String logData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(logData, LogEntryEntity.class);
        } catch (Exception e) {
            // Log parsing error
            return null;
        }
    }

}
