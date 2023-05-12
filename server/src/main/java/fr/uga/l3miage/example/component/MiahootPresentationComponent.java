package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootPresentationMapper;
import fr.uga.l3miage.example.models.MiahootPresentationEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.repository.MiahootPresentationRepository;
import fr.uga.l3miage.example.repository.UserRepository;
import fr.uga.l3miage.example.response.MiahootPresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MiahootPresentationComponent {
    private final MiahootPresentationRepository miahootPresentationRepository;
    private final UserRepository userRepository;
    private final MiahootPresentationMapper miahootPresentationMapper;

    /**
     * @param miahootId de l'entité Miahoot à récupérer
     * @return une {@link MiahootPresentationEntity} correspondant à description donnée
     * @throws EntityNotFoundException si aucune entité Miahoot n'est trouvée
     */
    public MiahootPresentationEntity getMiahoot(final Integer miahootId) throws EntityNotFoundException {
        return miahootPresentationRepository.findByMiahootId(miahootId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la Miahoot [%s]", miahootId), miahootId));
    }

    /**
     * @param uid qui est l'identificateur unique du user possédant les l'entité Miahoot à récupérer
     * @return une {@link List<MiahootPresentationEntity>} correspondant à miahootId donnée
     * @throws EntityNotFoundException si aucune entité Miahoot n'est trouvée
     */
    /*
    public List<MiahootEntity> getAllMiahootByUserUid(final String uid) throws EntityNotFoundException {
        List<MiahootEntity> miahoots = miahootRepository.findAllByUserUid(uid);
        if (miahoots.isEmpty()) {
            throw new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour user  [%s]", uid),404);
        }
        return miahoots;
    }*/

    /**
     * @param entity qui est l'entité Miahoot a creer
     */
    public void createMiahootPresentation(final MiahootPresentationEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entité Miahoot ne peut pas être null");
        }
        try {
            miahootPresentationRepository.save(entity);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Erreur lors de la création de l'entité Miahoot", ex);
        }
    }


    /**
     * @param miahootId la l'id de l'entité en BD à mettre à jour
     * @param miahoot le <b color="yellow"> DTO</b> de l'entité qui va permettre la modification
     * @throws EntityNotFoundException si l'entité avec une l'id n'est pas trouvée en BD
     */
    public void updateMiahootPresentation(final int miahootId, final MiahootPresentation miahoot) throws EntityNotFoundException {
        try {
            MiahootPresentationEntity actualEntity = miahootPresentationRepository.findByMiahootId(miahootId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour miahootId [%s]", miahootId), miahootId));
            miahootPresentationMapper.mergeMiahootPresentationEntity(actualEntity, miahoot);
            miahootPresentationRepository.save(actualEntity);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de mettre à jour l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }


    /**
     * @param miahootId de l'entité à supprimer
     * @throws TestEntityNotFoundException si l'entité avec la description fournit n'est pas trouvée
     */
    public void deleteMiahootPresentation(final Integer miahootId) throws EntityNotFoundException {
        try {
            int deleted = miahootPresentationRepository.deleteByMiahootId(miahootId);
            if (deleted == 0){
                throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", (Integer)miahootId);
            }
        }catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de supprimer l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }

    public void closeMiahoot(final int miahootId) throws EntityNotFoundException {
        try {
            MiahootPresentationEntity actualEntity = miahootPresentationRepository.findByMiahootId(miahootId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour miahootId [%s]", miahootId), miahootId));
            actualEntity.setEnCours(false);
            miahootPresentationRepository.save(actualEntity);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de mettre à jour l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }

}
