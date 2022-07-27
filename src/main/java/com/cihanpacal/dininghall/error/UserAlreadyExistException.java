package com.cihanpacal.dininghall.error;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
