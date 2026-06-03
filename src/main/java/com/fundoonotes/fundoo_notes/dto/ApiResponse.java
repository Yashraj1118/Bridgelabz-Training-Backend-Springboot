package com.fundoonotes.fundoo_notes.dto;

public class ApiResponse {

    private int status;
    private String message;
    private Object data;

    // Constructor for error responses
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor for success responses with data
    public ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}