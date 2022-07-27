package com.cihanpacal.dininghall.error;

import lombok.Data;

@Data
public class ResourceAlreadyExistException extends RuntimeException{

    private Object[] args;

    public ResourceAlreadyExistException() {
    }

    public ResourceAlreadyExistException(String message,Object[] args) {
        super(message);
        this.args=args;
    }

    public ResourceAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
