package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.Reponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Permet de tester le Mapper {@link QuestionMapper}<br>
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
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ReponseMapper reponseMapper;
    @Test
    void toDto() {
        List<ReponseEntity> listResponses = Arrays.asList(
                ReponseEntity.builder()
                        .label("C'est une plateforme de quiz en ligne.")
                        .estValide(true)
                        .build(),
                ReponseEntity.builder()
                        .label("C'est un langage de programmation.")
                        .estValide(false)
                        .build()
        );

        QuestionEntity questionEntity = QuestionEntity
                .builder()
                .label("Qu'est ce qu'un Miahoot ?")
                .reponses(listResponses)
                .build();


        fr.uga.l3miage.example.response.Question question = questionMapper.toDto(questionEntity);

        assertEquals(questionEntity.getLabel(), question.getLabel());
        assertEquals(questionEntity.getReponses().size(), question.getReponses().size());
        assertEquals(questionEntity.getReponses().get(0).getLabel(), question.getReponses().get(0).getLabel());
        assertEquals(questionEntity.getReponses().get(0).getEstValide(), question.getReponses().get(0).getEstValide());
        assertEquals(questionEntity.getReponses().get(1).getLabel(), question.getReponses().get(1).getLabel());
        assertEquals(questionEntity.getReponses().get(1).getEstValide(), question.getReponses().get(1).getEstValide());

    }

    @Test
    void toEntity() {
        List<Reponse> reponses = Arrays.asList(
                Reponse.builder()
                        .label("C'est une plateforme de quiz en ligne.")
                        .estValide(true)
                        .build(),
                Reponse.builder()
                        .label("C'est un langage de programmation.")
                        .estValide(false)
                        .build()
        );

        CreateQuestionRequest request = CreateQuestionRequest
                .builder()
                .label("Qu'est ce qu'un Miahoot ?")
                .reponses(reponses)
                .build();

        QuestionEntity questionEntity = questionMapper.toEntity(request);

        assertEquals(request.getLabel(), questionEntity.getLabel());
        assertEquals(request.getReponses().size(), questionEntity.getReponses().size());
        assertEquals(request.getReponses().get(0).getLabel(), questionEntity.getReponses().get(0).getLabel());
        assertEquals(request.getReponses().get(0).getEstValide(), questionEntity.getReponses().get(0).getEstValide());
        assertEquals(request.getReponses().get(1).getLabel(), questionEntity.getReponses().get(1).getLabel());
        assertEquals(request.getReponses().get(1).getEstValide(), questionEntity.getReponses().get(1).getEstValide());
    }

}
