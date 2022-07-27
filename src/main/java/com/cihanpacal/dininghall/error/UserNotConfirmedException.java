package com.cihanpacal.dininghall.error;

public class UserNotConfirmedException extends  RuntimeException{

    public UserNotConfirmedException(String message) {
        super(message);
    }
}
