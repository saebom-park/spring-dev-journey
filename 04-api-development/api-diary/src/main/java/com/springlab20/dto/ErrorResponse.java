package com.springlab20.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;

    // constructor
    public ErrorResponse(int status, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
    }

    // getter
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public List<String> getErrors() { return errors; }
}