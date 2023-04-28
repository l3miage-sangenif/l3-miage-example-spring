package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.request.CreateTestRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IsNotMiahootException extends RuntimeException {
    //a faire
    private final MiahootEntity request;

    public IsNotMiahootException(String message, MiahootEntity request) {
        super(message);
        this.request = request;
    }
    public IsNotMiahootException(String message,MiahootEntity request, Throwable cause) {
        super(message, cause);
        this.request = request;
    }
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public ErrorCode getErrorCode(){return ErrorCode.IS_NOT_TEST_ERROR;}

}
