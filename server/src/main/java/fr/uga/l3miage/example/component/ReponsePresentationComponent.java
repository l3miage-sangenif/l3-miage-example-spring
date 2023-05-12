package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponsePresentationMapper;
import fr.uga.l3miage.example.models.ReponsePresentationEntity;
import fr.uga.l3miage.example.repository.ReponsePresentationRepository;
import fr.uga.l3miage.example.response.ReponsePresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ReponsePresentationComponent {
    private final ReponsePresentationRepository reponseRepository;
    private final ReponsePresentationMapper reponseMapper;


    public ReponsePresentationEntity getReponsePresentation(final int reponseId) throws EntityNotFoundException {
        return reponseRepository.findByReponseId(reponseId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la reponse [%s]", reponseId), reponseId));
    }

    public void createReponsePresentation(final ReponsePresentationEntity entity) {
            reponseRepository.save(entity);
    }

    public void updateReponsePresentation(final int reponseId, final ReponsePresentation reponse) throws EntityNotFoundException {
            ReponsePresentationEntity actualEntity = reponseRepository.findByReponseId(reponseId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour reponseId [%s]", reponseId), reponseId));
            reponseMapper.mergeReponsePresentationEntity(actualEntity, reponse);
            reponseRepository.save(actualEntity);
    }


    public void deleteReponse(final int reponseId) throws EntityNotFoundException {
        int deleted = reponseRepository.deleteByReponseId(reponseId);
        if (deleted == 0){
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", reponseId);
        }


    }
}
