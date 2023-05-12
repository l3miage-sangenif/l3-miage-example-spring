package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.IsNotTestException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.mapper.UserMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.MiahootPresentationEntity;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.repository.MiahootPresentationRepository;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.repository.UserRepository;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Pour respecter l'architecture hexagonale, ici nous ne traitons que les données
 * <br>
 * Les Annotations :
 * <ul>
 *     <li>{@link Component} permet de créer un composant spring, cette annotation est en quelque sorte un stéréotype d'un @{@link Bean}, car elle va permettre de créer un candidat à l'injection.</li>
 *     <li>{@link RequiredArgsConstructor} crée un constructeur avec les attributs finaux, ou les attributs annotés par {@link lombok.NonNull}.<br>Voir la doc <a href="https://projectlombok.org/features/constructor">projectlombok/feature/RequiredArgConstructor</a></li>
 * </ul>
 */

@Component
@RequiredArgsConstructor
public class UserComponent {
    private final UserRepository userRepository;
    private final MiahootPresentationRepository miahootPresentationRepository;
    private final UserMapper userMapper;

    /**
     * @param entity à créer en base de données
     * @throws IsNotTestException si dans l'entité à créer le champ isTest est égal à <b>false</b>
     * @throws DescriptionAlreadyExistException Si la description dans l'entité à créer existe déjà en BD
     */
    public void createUser(final UserEntity entity){
        if (entity == null) {
            throw new IllegalArgumentException("L'entité Miahoot ne peut pas être null");
        }
        try {
            userRepository.save(entity);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Erreur lors de la création de l'entité Miahoot", ex);
        }
    }

    /**
     * @param uid la l'identificateur unique sous forme de string de l'entité en BD à mettre à jour
     * @param user le <b color="yellow"> DTO</b> de l'entité qui va permettre la modification
     * @throws EntityNotFoundException si l'entité avec uid n'est pas trouvée en BD
     */
    public void updateUser(final String uid, final User user) throws EntityNotFoundException {
        try {
            UserEntity actualEntity = userRepository.findByUid(uid)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour miahootId [%s]", uid), 404));
            userMapper.mergeUserEntity(actualEntity, user);
            userRepository.save(actualEntity);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de mettre à jour l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }

    /**
     * @param uid de l'entité à supprimer
     * @throws TestEntityNotFoundException si l'entité avec la description fournit n'est pas trouvée
     */
    public void deleteUser(final String uid) throws EntityNotFoundException {
        try {
            int deleted = userRepository.deleteByUid(uid);
            if (deleted == 0){
                throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", 404);
            }
        }catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de supprimer l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }

    public List<MiahootEntity> getAllMiahootByUserUid(final String uid) throws EntityNotFoundException {
        Optional<UserEntity> user = userRepository.findByUid(uid);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour user  [%s]", uid),404);
        }

        return user.get().getMiahoots();
    }

    public Set<MiahootPresentationEntity> getAllMiahootPresentationByUserUid(String uid) throws EntityNotFoundException {
        Optional<UserEntity> user = userRepository.findByUid(uid);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour user  [%s]", uid),404);
        }

        return user.get().getMiahootPresente();
    }

    public void addParticipant(final String userId,int miahootId) throws EntityNotFoundException {
        try {
            MiahootPresentationEntity miahoot = miahootPresentationRepository.findByMiahootId(miahootId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour miahootId [%s]", miahootId), miahootId));
            Optional<UserEntity> user = userRepository.findByUid(userId);
            if (user.isEmpty()) {
                throw new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour user  [%s]", userId),404);
            }
            else {
                Set<MiahootPresentationEntity> listMiahoot = user.get().getMiahootParticipes();
                listMiahoot.add(miahoot);
                user.get().setMiahootParticipes(listMiahoot);
            }
            userRepository.save(user.get());
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Impossible de mettre à jour l'entité Miahoot : " + ex.getMessage(), ex);
        }
    }
}



