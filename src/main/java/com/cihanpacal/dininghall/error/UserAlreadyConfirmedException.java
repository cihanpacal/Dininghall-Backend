package com.cihanpacal.dininghall.error;

public class UserAlreadyConfirmedException extends RuntimeException{
    public UserAlreadyConfirmedException(String message) {
        super(message);
    }
}
