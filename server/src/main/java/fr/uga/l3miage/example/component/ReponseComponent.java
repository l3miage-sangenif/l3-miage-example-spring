package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.*;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.repository.ReponseRepository;
import fr.uga.l3miage.example.response.Reponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final ReponseRepository reponseRepository;
    private final ReponseMapper reponseMapper;


    public ReponseEntity getReponse(final int reponseId) throws EntityNotFoundException {
        return reponseRepository.findByReponseId(reponseId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la reponse [%s]", reponseId), reponseId));
    }

    public void createReponse(final ReponseEntity entity) {
            reponseRepository.save(entity);
    }

    public void updateReponse(final int reponseId, final Reponse reponse) throws EntityNotFoundException {
            ReponseEntity actualEntity = reponseRepository.findByReponseId(reponseId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour reponseId [%s]", reponseId), reponseId));
            reponseMapper.mergeReponseEntity(actualEntity, reponse);
            reponseRepository.save(actualEntity);
    }


    public void deleteReponse(final int reponseId) throws EntityNotFoundException {
        int deleted = reponseRepository.deleteByReponseId(reponseId);
        if (deleted == 0){
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", reponseId);
        }


    }
}
