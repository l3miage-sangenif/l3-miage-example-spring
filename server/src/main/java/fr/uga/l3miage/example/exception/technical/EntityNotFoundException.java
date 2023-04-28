package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;


@Getter
public class EntityNotFoundException extends Exception {
    private final int reponseId;

    public EntityNotFoundException(String message, int reponseId) {
        super(message);
        this.reponseId = reponseId;
    }

    public EntityNotFoundException(String message, int reponseId, Throwable cause) {
        super(message, cause);
        this.reponseId = reponseId;
    }
}
