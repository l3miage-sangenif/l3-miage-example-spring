package fr.uga.l3miage.example.mapper;


import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.Reponse;
import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Permet de tester le Mapper {@link MiahootMapper}<br>
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
class MiahootMapperTest {
/*
    @Autowired
    private MiahootMapper miahootMapper;

    @Test
    void toDto() {
        // création des reponseEntity de la question 1 : Qui a écrit "Les Misérables"
        List<ReponseEntity> reponseEntitiesForQ1 = new ArrayList<>(Arrays.asList(
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
        ));
        //création des reponseEntity pour la question 2 :Quel est le plus grand pays du monde par sa superficie
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
        //creation de la liste de questionEntity
        List<QuestionEntity> questionsEntities = new ArrayList<>(Arrays.asList(
                QuestionEntity.builder()
                        .label("Qui a écrit \"Les Misérables\"")
                        .reponses(reponseEntitiesForQ1)
                        .build(),
                QuestionEntity.builder()
                        .label("Quel est le plus grand pays du monde par sa superficie")
                        .reponses(reponseEntitiesForQ2)
                        .build()
        ));
        MiahootEntity miahootEntity = MiahootEntity.builder()
                .nom("Miahoot1")
                .questions(questionsEntities)
                .build();

        Miahoot miahootGet = miahootMapper.toDto(miahootEntity);


        // création du miahoot(le DTO) qu'on est censé obtenir
        Miahoot miahootExpected = Miahoot.builder()
                .nom("Miahoot1")
                .questions(new ArrayList<>(Arrays.asList(
                        Question.builder()
                                .label("Qui a écrit \"Les Misérables\"")
                                .reponses(new ArrayList<>(Arrays.asList(
                                        Reponse.builder()
                                                .label("Victor Hugo")
                                                .estValide(true)
                                                .build(),
                                        Reponse.builder()
                                                .label("Emile Zola")
                                                .estValide(false)
                                                .build(),
                                        Reponse.builder()
                                                .label("Gustave Flaubert")
                                                .estValide(false)
                                                .build(),
                                        Reponse.builder()
                                                .label("Honoré de Balzac")
                                                .estValide(false)
                                                .build()
                                )))
                                .build(),
                        Question.builder()
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
                                                .label("États-Unis")
                                                .estValide(false)
                                                .build()
                                )))
                                .build()
                )))
                .build();

        assertThat(miahootGet).usingRecursiveComparison()
                .isEqualTo(miahootExpected);

    }

/*
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
        //création des reponse pour la question 2 :Quel est le plus grand pays du monde par sa superficie
        List<CreateReponseRequest> reponseForQ2 = new ArrayList<>(Arrays.asList(
                CreateReponseRequest.builder()
                        .label("Canada")
                        .estValide(false)
                        .build(),
                CreateReponseRequest.builder()
                        .label("Russie")
                        .estValide(true)
                        .build(),
                CreateReponseRequest.builder()
                        .label("Chine")
                        .estValide(false)
                        .build(),
                CreateReponseRequest.builder()
                        .label("États-Unis")
                        .estValide(false)
                        .build()
        ));
        //creation de la liste de Question
        List<CreateQuestionRequest> questions = new ArrayList<>(Arrays.asList(
                CreateQuestionRequest.builder()
                        .label("Qui a écrit \"Les Misérables\"")
                        .reponses(reponseForQ1)
                        .build(),
                CreateQuestionRequest.builder()
                        .label("Quel est le plus grand pays du monde par sa superficie")
                        .reponses(reponseForQ2)
                        .build()
        ));
        CreateMiahootRequest createMiahootRequest = CreateMiahootRequest.builder()
                .nom("Miahoot1")
                .questions(questions)
                .build();

        MiahootEntity miahootEntityGet = miahootMapper.toEntity(createMiahootRequest);


        // création du miahootEntity qu'on est censé obtenir
        MiahootEntity miahootEntityExpected = MiahootEntity.builder()
                .nom("Miahoot1")
                .questions(new ArrayList<>(Arrays.asList(
                        QuestionEntity.builder()
                                .label("Qui a écrit \"Les Misérables\"")
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
                                )))
                                .build(),
                        QuestionEntity.builder()
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
                                )))
                                .build()
                )))
                .build();

        assertThat(miahootEntityGet).usingRecursiveComparison()
                .isEqualTo(miahootEntityExpected);

    }

    //mergeMiahootEntity ne passe pas encore a cause de la liste de question
    // qui est une liste non modifiable ce qui entre en confrontation
    // avec l'implementation de mergeMiahootEntity qui applique clear
    // sur la liste ce qui ne devrait pas etre possible avec une liste non modifiable
    @Test
    void mergeMiahootEntity() {
        // Créer une entité Miahoot existante avec deux questions
        MiahootEntity targetEntity = MiahootEntity.builder()
                .nom("Miahoot1")
                .questions(new ArrayList<>(Arrays.asList(
                        QuestionEntity.builder()
                                .label("Qui a écrit \"Les Misérables\"")
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
                                )))
                                .build(),
                        QuestionEntity.builder()
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
                                )))
                                .build()
                )))
                .build();

        //
        Miahoot miahootToMerge= Miahoot.builder()
                .nom("Miahoot1")
                .questions(new ArrayList<>(Arrays.asList(
                        Question.builder()
                                .label("Quel est le plus grand océan du monde")
                                .reponses(new ArrayList<>(Arrays.asList(
                                        Reponse.builder()
                                                .label("L'océan Pacifique")
                                                .estValide(true)
                                                .build(),
                                        Reponse.builder()
                                                .label("L'océan Atlantique")
                                                .estValide(false)
                                                .build(),
                                        Reponse.builder()
                                                .label("L'océan Indien")
                                                .estValide(false)
                                                .build(),
                                        Reponse.builder()
                                                .label("L'océan Arctique")
                                                .estValide(false)
                                                .build()
                                )))
                                .build())))
                .build();

        // Fusionner les modifications dans l'entité existante
        miahootMapper.mergeMiahootEntity(targetEntity, miahootToMerge);

        //il manque la fin du test pour le moment
    }
*/

}
