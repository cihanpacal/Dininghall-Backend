package com.cihanpacal.dininghall.error;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

    private Object[] args;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message,Object[] args) {
        super(message);
        this.args=args;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}
