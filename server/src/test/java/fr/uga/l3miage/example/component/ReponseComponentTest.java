package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.repository.ReponseRepository;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Reponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Ici on teste le composant Reponse<br>
 * <br>
 * Les annotations :
 * <ul>
 *     <li>{@link SpringBootTest} permet de monter le framework spring pour les tests<br>
 *     <b color="red">indispensable si vous avez besoin d'injection</b><br>
 *     Les 2 arguments importants sont :
 *     <ul>
 *         <li><b>webEnvironnement</b> qui correspond à comment démarrer spring boot, options possibles : <br>
 *         <ul>
 *             <li>{@link SpringBootTest.WebEnvironment#MOCK} permet de dire que l'environnement web est simulé</li>
 *             <li>{@link SpringBootTest.WebEnvironment#RANDOM_PORT} permet de dire qu'un port random est utilisé pour lancer réellement le serveur</li>
 *             <li>{@link SpringBootTest.WebEnvironment#DEFINED_PORT} permet de donner le port où le serveur doit démarrer</li>
 *             <li>{@link SpringBootTest.WebEnvironment#NONE} permet de dire qu'aucun environment web est utile</li>
 *         </ul>
 *         </li>
 *         <li><b>properties</b> permet d'override des configurations dans l'application.yml, lorsque vous utilisez :
 *         <ul>
 *             <li><b>spring.jpa.database-platform=org.hibernate.dialect.H2Dialect</b> permet de dire que vous utilisez une base H2 pour vos tests</li>
 *         </ul>
 *         </li>
 *     </ul>
 *     </li>
 *     <li>{@link AutoConfigureTestDatabase} permet de configurer le serveur de manière automatique sur la BD H2</li>
 * </ul>
 */
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
class ReponseComponentTest {

    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private ReponseComponent reponseComponent;
    @Autowired
    private ReponseMapper reponseMapper;

    /**
     * Éxécuté à chaque fin de test
     */
    @AfterEach
    void clear() {
        reponseRepository.deleteAll();
    }

    @Test
    void getReponse() throws EntityNotFoundException {
        // Given
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        // When
        ReponseEntity actualEntity = reponseComponent.getReponse(savedEntity.getReponseId());

        // Then
        assertThat(actualEntity).isEqualTo(expectedEntity);
    }

    @Test
    void CreateReponse() {
        // Given
        CreateReponseRequest request = CreateReponseRequest.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();

        // When
        reponseComponent.createReponse(reponseMapper.toEntity(request));

        // Then
        List<ReponseEntity> entities = reponseRepository.findAll();
        assertThat(entities).isNotNull();
        assertThat(entities.get(0).getLabel()).isEqualTo(request.getLabel());
        assertThat(entities.get(0).getEstValide()).isEqualTo(request.getEstValide());
    }

    @Test
    void updateReponse() throws EntityNotFoundException {
        // Given
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        Reponse updatedReponse = Reponse.builder()
                .label("Updated Test Reponse")
                .estValide(false)
                .build();

        // When
        reponseComponent.updateReponse(savedEntity.getReponseId(), updatedReponse);

        // Then
        ReponseEntity actualEntity = reponseRepository.findById(savedEntity.getReponseId()).get();
        assertThat(actualEntity.getLabel()).isEqualTo(updatedReponse.getLabel());
        assertThat(actualEntity.getEstValide()).isEqualTo(updatedReponse.getEstValide());
    }

    @Transactional
    @Test
    void DeleteReponse() throws EntityNotFoundException{
        // Given
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        // When
        reponseComponent.deleteReponse(savedEntity.getReponseId());

        // Then
        assertThat(reponseRepository.findById(savedEntity.getReponseId())).isEmpty();
    }

}
