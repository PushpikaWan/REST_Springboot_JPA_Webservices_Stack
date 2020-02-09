package com.spring.basic.rest.webservice.restcourse.app.user;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String description;

    protected ExceptionResponse(){

    }

    public ExceptionResponse(Date timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
