package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.models.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Permet de tester le Mapper {@link ReponseMapper}<br>
 <br>
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
 * </ul>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ReponseMapperTest {
    @Autowired
    private ReponseMapper reponseMapper;

    @Test
    void toDto() {
        ReponseEntity reponseEntity = ReponseEntity
                .builder()
                .label("label")
                .estValide(true)
                .build();
        fr.uga.l3miage.example.response.Reponse reponseGet = reponseMapper.toDto(reponseEntity);

        fr.uga.l3miage.example.response.Reponse reponseExcepted = fr.uga.l3miage.example.response.Reponse
                .builder()
                .label("label")
                .estValide(true).
                build();

        assertThat(reponseGet).usingRecursiveComparison()
                .isEqualTo(reponseExcepted);
    }

    @Test
    void toEntity() {
        fr.uga.l3miage.example.request.CreateReponseRequest reponseDTO = fr.uga.l3miage.example.request.CreateReponseRequest
                .builder()
                .label("label")
                .estValide(true)
                .build();

        ReponseEntity reponseEntity = reponseMapper.toEntity(reponseDTO);

        ReponseEntity reponseExpected = ReponseEntity
                .builder()
                .label("label")
                .estValide(true)
                .build();

        assertThat(reponseEntity).usingRecursiveComparison()
                .ignoringFields("idReponse")
                .isEqualTo(reponseExpected);
    }
}
