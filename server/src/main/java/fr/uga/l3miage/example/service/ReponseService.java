package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.exception.rest.*;
import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Reponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@RequiredArgsConstructor
public class ReponseService {
    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité à été détecté.";
    private final ReponseComponent reponseComponent;
    private final ReponseMapper reponseMapper;



    public Reponse getReponse(final int reponseId) {
        try {
            return reponseMapper.toDto(reponseComponent.getReponse(reponseId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), reponseId, ex);
        }
    }


    public void createReponse(final CreateReponseRequest createReponseRequest) {
        ReponseEntity newReponseEntity = reponseMapper.toEntity(createReponseRequest);
        reponseComponent.createReponse(newReponseEntity);
    }


    public void updateReponse(final int reponseId, final Reponse reponse) {
            try {
                reponseComponent.updateReponse(reponseId, reponse);
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), reponseId, ex);
            }
    }

    @Transactional
    public void deleteReponse(int reponseId) {
        try {
            reponseComponent.deleteReponse(reponseId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }

}
