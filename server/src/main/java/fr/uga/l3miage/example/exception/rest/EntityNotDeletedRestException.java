package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;


public class EntityNotDeletedRestException extends RuntimeException{
    public EntityNotDeletedRestException(String message) {
        super(message);
    }

    public EntityNotDeletedRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {return HttpStatus.I_AM_A_TEAPOT;}

    public ErrorCode getErrorCode(){return ErrorCode.ENTITY_NOT_DELETED_ERROR;}
}
