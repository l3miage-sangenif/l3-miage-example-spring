package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Reponse;
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
        ReponseEntity reponseEntity = ReponseEntity.builder()
                .reponseId(1)
                .label("Victor Hugo")
                .estValide(true)
                .build();

        //conversion de l'entité vers le DTO
        fr.uga.l3miage.example.response.Reponse reponseGet = reponseMapper.toDto(reponseEntity);

        // création du reponse(le DTO) qu'on est censé obtenir
        Reponse reponseExcepted = Reponse.builder()
                .label("Victor Hugo")
                .estValide(true).
                build();

        assertThat(reponseGet).usingRecursiveComparison()
                .isEqualTo(reponseExcepted);
    }

    @Test
    void toEntity() {
        //creation de la createReponseRequest pour la question Q1 : Qui a écrit "Les Misérables"
        CreateReponseRequest createReponseRequest = CreateReponseRequest
                .builder()
                .label("Victor Hugo")
                .estValide(true)
                .build();

        //conversion de dto vers l'entité
        ReponseEntity reponseEntityGet = reponseMapper.toEntity(createReponseRequest);

        // création du reponseEntity qu'on est censé obtenir
        ReponseEntity reponseEntityExpected = ReponseEntity
                .builder()
                .label("Victor Hugo")
                .estValide(true)
                .build();

        assertThat(reponseEntityGet).usingRecursiveComparison()
                .isEqualTo(reponseEntityExpected);
    }

    @Test
    void mergeReponseEntity(){
        //creation d'une reponseEntity qui est le target pour la question Q1 : Qui a écrit "Les Misérables"
        ReponseEntity targetEntity = ReponseEntity.builder()
                .label("Victor Hugo")
                .estValide(true)
                .build();

        //creation de la reponse a merger qui est la source des modifications(il s'agit d'un DTO)
        Reponse reponseToMerge= Reponse.builder()
                .label("V Hugo")
                .estValide(false)
                .build();

        //merging
        reponseMapper.mergeReponseEntity(targetEntity,reponseToMerge);

        //creation du reponseEntity qu'on est censé avoir aprè-s le merging
        ReponseEntity reponseEntityExpected = ReponseEntity.builder()
                .label("V Hugo")
                .estValide(false)
                .build();

        assertThat(targetEntity).usingRecursiveComparison()
                .isEqualTo(reponseEntityExpected);

    }
}
