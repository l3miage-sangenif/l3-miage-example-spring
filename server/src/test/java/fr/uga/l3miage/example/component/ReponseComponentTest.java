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
        //creation d'une reponse
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Victor Hugo")
                .estValide(true)
                .build();

        //enregistrement de la reponse créé précédement
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        // recupéraion d'une réponse en utilisant l'id de la réponse enrégistré pour ensuite verifié
        // que ce qu'on récupère est bien ce qu'on a enrégistré
        ReponseEntity actualEntity = reponseComponent.getReponse(savedEntity.getReponseId());

        // on vérifie que que ce qu'on récupère est bien ce qu'on a enrégistré
        assertThat(savedEntity).usingRecursiveComparison()
                .isEqualTo(actualEntity);
    }

    @Test
    void CreateReponse() {
        // Creation de la requete avec laquelle on va creer une reponse
        CreateReponseRequest request = CreateReponseRequest.builder()
                .label("Victor Hugo")
                .estValide(true)
                .build();

        //conversion de la requete en entité
        ReponseEntity reponseEntity=reponseMapper.toEntity(request);

        // creation d'une reponse par le component avec au préalable conversion
        reponseComponent.createReponse(reponseEntity);

        // Then
        List<ReponseEntity> entities = reponseRepository.findAll();
        //on verifie qu'il n'y a qu'une seule réponse dans la base car nous avons enregistré une seule réponse
        assertThat(reponseRepository.count()).isOne();
        assertThat(entities).isNotNull();
        // on vérifie que que ce qu'on récupère est bien ce qu'on a enrégistré
        assertThat(reponseEntity).usingRecursiveComparison()
                .isEqualTo(entities.get(0));
    }

    @Test
    void updateReponse() throws EntityNotFoundException {
        // construction de la réponse initiale
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        //construction d'une nouvelle réponse pour mettre a jour a la précédente
        Reponse updateReponse = Reponse.builder()
                .label("Updated Test Reponse")
                .estValide(false)
                .build();

        // mise a jour de la réponse initial
        reponseComponent.updateReponse(savedEntity.getReponseId(), updateReponse);

        //une fois mis a jour on récupère l'entité dans son état actuel

        ReponseEntity actualEntity=reponseRepository.findByReponseId(savedEntity.getReponseId()).get();

        // verification

        assertThat(reponseMapper.toDto(actualEntity))
                .usingRecursiveComparison()
                .isEqualTo(updateReponse);
    }

    @Transactional
    @Test
    void deleteReponse() throws EntityNotFoundException{
        // Given
        ReponseEntity expectedEntity = ReponseEntity.builder()
                .label("Test Reponse")
                .estValide(true)
                .build();
        ReponseEntity savedEntity = reponseRepository.save(expectedEntity);

        // When
        reponseComponent.deleteReponse(savedEntity.getReponseId());

        // on verifie qu'il n'y a plus rien dans la base vu qu'on y a mis seulement un element qu'on vient de supprimer
        assertThat(reponseRepository.count()).isZero();
        assertThat(reponseRepository.findById(savedEntity.getReponseId())).isEmpty();
    }

}
