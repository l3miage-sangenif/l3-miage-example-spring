package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.exception.technical.technicalTestException.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.technicalTestException.IsNotTestException;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.response.Miahoot;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    private final TestRepository userRepository;
    private final TestMapper userMapper;

    /**
     * @param entity à créer en base de données
     * @throws IsNotTestException si dans l'entité à créer le champ isTest est égal à <b>false</b>
     * @throws DescriptionAlreadyExistException Si la description dans l'entité à créer existe déjà en BD
     */
    public void createUser(final UserEntity entity) throws IsNotTestException, DescriptionAlreadyExistException {

    }


}
