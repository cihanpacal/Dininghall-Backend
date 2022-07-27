package com.cihanpacal.dininghall.error;

import lombok.Data;

@Data
public class EntityHasRelationshipException extends RuntimeException {

    private Object[] args;

    public EntityHasRelationshipException(Object[] args) {
        this.args = args;
    }

    public EntityHasRelationshipException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public EntityHasRelationshipException(String message, Throwable cause, Object[] args) {
        super(message, cause);
        this.args = args;
    }

    public EntityHasRelationshipException(Throwable cause, Object[] args) {
        super(cause);
        this.args = args;
    }
}
