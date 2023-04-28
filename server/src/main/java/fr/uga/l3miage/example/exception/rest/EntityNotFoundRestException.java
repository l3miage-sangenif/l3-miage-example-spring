package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class EntityNotFoundRestException extends RuntimeException {
    private final int id;

    public EntityNotFoundRestException(String message, int id) {
        super(message);
        this.id = id;
    }

    public EntityNotFoundRestException(String message, int id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public ErrorCode getErrorCode(){return ErrorCode.IS_NOT_FOUND;}
}
