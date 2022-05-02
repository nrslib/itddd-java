package com.example.itddd.sns.application.service.user;

public class CannotRegisterUserException extends RuntimeException {
    public CannotRegisterUserException(String message) {
        super(message);
    }
}
