package com.cihanpacal.dininghall.error;

import lombok.Data;

@Data
public class EntityMustHasRelationshipException extends  RuntimeException{
    private Object[] args;

    public EntityMustHasRelationshipException(Object[] args) {
        this.args = args;
    }

    public EntityMustHasRelationshipException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public EntityMustHasRelationshipException(String message, Throwable cause, Object[] args) {
        super(message, cause);
        this.args = args;
    }

    public EntityMustHasRelationshipException(Throwable cause, Object[] args) {
        super(cause);
        this.args = args;
    }


}
