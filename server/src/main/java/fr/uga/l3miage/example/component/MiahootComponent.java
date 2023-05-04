package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.IsNotTestException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.MultipleEntityHaveSameDescriptionException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.response.Miahoot;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final MiahootMapper miahootMapper;

    /**
     * @param miahootId de l'entité Miahoot à récupérer
     * @return une {@link MiahootEntity} correspondant à description donnée
     * @throws EntityNotFoundException si aucune entité Miahoot n'est trouvée
     */
    public MiahootEntity getMiahoot(final Integer miahootId) throws EntityNotFoundException {
        return miahootRepository.findByMiahootId(miahootId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la Miahoot [%s]", miahootId), miahootId));
    }

    /**
     * @param uid qui est l'identificateur unique du user possédant les l'entité Miahoot à récupérer
     * @return une {@link List<MiahootEntity>} correspondant à miahootId donnée
     * @throws EntityNotFoundException si aucune entité Miahoot n'est trouvée
     */
    public List<MiahootEntity> getAllMiahootByUserUid(final String uid) throws EntityNotFoundException {
        List<MiahootEntity> miahoots = miahootRepository.findAllByUserUid(uid);
        if (miahoots.isEmpty()) {
            throw new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour user  [%s]", uid),404);
        }
        return miahoots;
    }

    /**
     * @param entity qui est l'entité Miahoot a creer
     */
    public void createMiahoot(final MiahootEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("L'entité Miahoot ne peut pas être null");
        }
        try {
            miahootRepository.save(entity);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Erreur lors de la création de l'entité Miahoot", ex);
        }
    }


    /**
     * @param miahootId la l'id de l'entité en BD à mettre à jour
     * @param miahoot le <b color="yellow"> DTO</b> de l'entité qui va permettre la modification
     * @throws EntityNotFoundException si l'entité avec une l'id n'est pas trouvée en BD
     */
    public void updateMiahoot(final int miahootId, final Miahoot miahoot) throws EntityNotFoundException {
        try {
            MiahootEntity actualEntity = miahootRepository.findByMiahootId(miahootId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour miahootId [%s]", miahootId), miahootId));
            miahootMapper.mergeMiahootEntity(actualEntity, miahoot);
            miahootRepository.save(actualEntity);
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
    public void deleteMiahoot(final Integer miahootId) throws EntityNotFoundException {
        try {
            int deleted = miahootRepository.deleteByMiahootId(miahootId);
            if (deleted == 0){
                throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", (Integer)miahootId);
            }
        }catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de supprimer l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }
}
