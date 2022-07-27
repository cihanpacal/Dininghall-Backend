package com.cihanpacal.dininghall.error;

public class UsedVerificationTokenException extends RuntimeException{
    public UsedVerificationTokenException(String message) {
        super(message);
    }
}
