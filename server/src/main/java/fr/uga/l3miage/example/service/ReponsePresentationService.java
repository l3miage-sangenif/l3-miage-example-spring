package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ReponsePresentationComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponsePresentationMapper;
import fr.uga.l3miage.example.models.ReponsePresentationEntity;
import fr.uga.l3miage.example.request.CreateReponsePresentationRequest;
import fr.uga.l3miage.example.response.ReponsePresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ReponsePresentationService {
    private static final String ERROR_DETECTED = "Une erreur lors de la création de l'entité à été détecté.";
    private final ReponsePresentationComponent reponseComponent;
    private final ReponsePresentationMapper reponseMapper;



    public ReponsePresentation getReponse(final int reponseId) {
        try {
            return reponseMapper.toDto(reponseComponent.getReponsePresentation(reponseId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), reponseId, ex);
        }
    }

    public int getNbReponse(final int reponseId) {
        try {
            return reponseComponent.getReponsePresentation(reponseId).getUsers().size();
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), reponseId, ex);
        }
    }


    public void createReponse(final CreateReponsePresentationRequest createReponseRequest) {
        ReponsePresentationEntity newReponseEntity = reponseMapper.toEntity(createReponseRequest);
        reponseComponent.createReponsePresentation(newReponseEntity);
    }


    public void updateReponse(final int reponseId, final ReponsePresentation reponse) {
            try {
                reponseComponent.updateReponsePresentation(reponseId, reponse);
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

/*
    public void createReponse(CreateReponseRequest createReponseRequest, QuestionEntity question) {
        ReponseEntity newReponseEntity = reponseMapper.toEntity(createReponseRequest);
        newReponseEntity.setQuestion(question);
        reponseComponent.createReponse(newReponseEntity);
    }*/
}
