package ru.kata.spring.boot_security.demo.model.Errors;

public class NotFoundError {
    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotFoundError() {
    }

    public NotFoundError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
