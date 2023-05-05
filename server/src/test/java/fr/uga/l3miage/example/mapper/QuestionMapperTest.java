package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.Reponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

   /* @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ReponseMapper reponseMapper;

    @Test
    void toDto() {
        //création des reponseEntity pour la question 2 : Quel est le plus grand pays du monde par sa superficie
        List<ReponseEntity> reponseEntitiesForQ2 = new ArrayList<>(Arrays.asList(
                ReponseEntity.builder()
                        .label("Canada")
                        .estValide(false)
                        .build(),
                ReponseEntity.builder()
                        .label("Russie")
                        .estValide(true)
                        .build(),
                ReponseEntity.builder()
                        .label("Chine")
                        .estValide(false)
                        .build(),
                ReponseEntity.builder()
                        .label("États-Unis")
                        .estValide(false)
                        .build()
        ));
        // création de la questionEntity pour la question Q2 :Quel est le plus grand pays du monde par sa superficie
        QuestionEntity questionEntity = QuestionEntity
                .builder()
                .label("Quel est le plus grand pays du monde par sa superficie ?")
                .reponses(reponseEntitiesForQ2)
                .build();

        //conversion de l'entité vers le DTO
        Question questionGet = questionMapper.toDto(questionEntity);

        // creation de la question(le DTO) qu'on s'attend a avoir
        Question questionExpected = Question.builder()
                .label("Quel est le plus grand pays du monde par sa superficie ?")
                .reponses(new ArrayList<>(Arrays.asList(
                        Reponse.builder()
                                .label("Canada")
                                .estValide(false)
                                .build(),
                        Reponse.builder()
                                .label("Russie")
                                .estValide(true)
                                .build(),
                        Reponse.builder()
                                .label("Chine")
                                .estValide(false)
                                .build(),
                        Reponse.builder()
                                .label("États-Unis")
                                .estValide(false)
                                .build()
                ))).build();


        assertThat(questionGet).usingRecursiveComparison()
                .isEqualTo(questionExpected);

    }

    @Test
    void toEntity() {
        // création des reponse de la question 1 : Qui a écrit "Les Misérables"
        List<CreateReponseRequest> reponseForQ1 = new ArrayList<>(Arrays.asList(
                CreateReponseRequest.builder()
                        .label("Victor Hugo")
                        .estValide(true)
                        .build(),
                CreateReponseRequest.builder()
                        .label("Emile Zola")
                        .estValide(false)
                        .build(),
                CreateReponseRequest.builder()
                        .label("Gustave Flaubert")
                        .estValide(false)
                        .build(),
                CreateReponseRequest.builder()
                        .label("Honoré de Balzac")
                        .estValide(false)
                        .build()
        ));

        // création de la createQuestionRequest(c'est le dto de requete) de la question Q1:Qui a écrit "Les Misérables"
        CreateQuestionRequest createQuestionRequest = CreateQuestionRequest
                .builder()
                .label("Qui a écrit \"Les Misérables\"?")
                .reponses(reponseForQ1)
                .build();

        //conversion du dto vers l'entité
        QuestionEntity questionEntityGet = questionMapper.toEntity(createQuestionRequest);

        //création de l'entité (questionEntity qu'on s'attend a avoir
        QuestionEntity questionEntityExpected = QuestionEntity
                .builder()
                .label("Qui a écrit \"Les Misérables\"?")
                .reponses(new ArrayList<>(Arrays.asList(
                        ReponseEntity.builder()
                                .label("Victor Hugo")
                                .estValide(true)
                                .build(),
                        ReponseEntity.builder()
                                .label("Emile Zola")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("Gustave Flaubert")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("Honoré de Balzac")
                                .estValide(false)
                                .build()
                ))).build();

        assertThat(questionEntityGet).usingRecursiveComparison()
                .isEqualTo(questionEntityExpected);

    }


    @Test
    void mergeTestEntity() {
        QuestionEntity targetEntity = QuestionEntity.builder()
                .label("Quel est le plus grand pays du monde par sa superficie")
                .reponses(new ArrayList<>(Arrays.asList(
                        ReponseEntity.builder()
                                .label("Canada")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("Russie")
                                .estValide(true)
                                .build(),
                        ReponseEntity.builder()
                                .label("Chine")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("États-Unis")
                                .estValide(false)
                                .build()
                ))).build();

        //creation de la question a merger qui est la source des modifications(il s'agit d'un DTO)
        Question questionToMerge = Question.builder()
                .label("Quel est le plus grand pays du monde par sa superficie")
                .reponses(new ArrayList<>(Arrays.asList(
                        Reponse.builder()
                                .label("Canada")
                                .estValide(false)
                                .build(),
                        Reponse.builder()
                                .label("Russie")
                                .estValide(true)
                                .build(),
                        Reponse.builder()
                                .label("Chine")
                                .estValide(false)
                                .build(),
                        Reponse.builder()
                                .label("France")
                                .estValide(false)
                                .build()
                ))).build();

        //merging
        questionMapper.mergeQuestionEntity(targetEntity, questionToMerge);

        //creation du questionEntity qu'on est censé avoir aprè-s le merging
        QuestionEntity questionEntityExpected = QuestionEntity.builder()
                .label("Quel est le plus grand pays du monde par sa superficie")
                .reponses(new ArrayList<>(Arrays.asList(
                        ReponseEntity.builder()
                                .label("Canada")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("Russie")
                                .estValide(true)
                                .build(),
                        ReponseEntity.builder()
                                .label("Chine")
                                .estValide(false)
                                .build(),
                        ReponseEntity.builder()
                                .label("France")
                                .estValide(false)
                                .build()
                ))).build();

        assertThat(targetEntity).usingRecursiveComparison()
                .isEqualTo(questionEntityExpected);
    }
*/
}
