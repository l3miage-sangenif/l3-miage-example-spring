package fr.uga.l3miage.example.mapper;


import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.Question;
import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private MiahootMapper miahootMapper;

    @Test
    void toDto() {
        List<QuestionEntity> questions = Arrays.asList(
                QuestionEntity.builder()
                        .label("Qui est l'homme le plus riche du monde")
                        .build(),
                QuestionEntity.builder()
                        .label("Qu'est ce qu'une question")
                        .build()
        );

        MiahootEntity miahootEntity = MiahootEntity.builder()
                .nom("Miahoot1")
                .questions(questions)
                .build();

        Miahoot miahoot = miahootMapper.toDto(miahootEntity);

        assertEquals(miahootEntity.getNom(), miahoot.getNom());
        assertEquals(miahootEntity.getQuestions().size(), miahoot.getQuestions().size());
        assertEquals(miahootEntity.getQuestions().get(0).getLabel(), miahoot.getQuestions().get(0).getLabel());
        assertEquals(miahootEntity.getQuestions().get(1).getLabel(), miahoot.getQuestions().get(1).getLabel());
    }

    @Test
    void toEntity() {
        List<Question> questions = Arrays.asList(
                Question.builder()
                        .label("Question 1")
                        .build(),
                Question.builder()
                        .label("Question 2")
                        .build()
        );

        CreateMiahootRequest request = CreateMiahootRequest.builder()
                .nom("Miahoot")
                .questions(questions)
                .build();

        MiahootEntity miahootEntity = miahootMapper.toEntity(request);

        assertEquals(request.getNom(), miahootEntity.getNom());
        assertEquals(request.getQuestions().size(), miahootEntity.getQuestions().size());
        assertEquals(request.getQuestions().get(0).getLabel(), miahootEntity.getQuestions().get(0).getLabel());
        assertEquals(request.getQuestions().get(1).getLabel(), miahootEntity.getQuestions().get(1).getLabel());
    }

    //mergeMiahootEntity ne passe pas encore a cause de la liste de question
    // qui est une liste non modifiable ce qui entre en confrontation
    // avec l'implementation de mergeMiahootEntity qui applique clear
    // sur la liste ce qui ne devrait pas etre possible avec une liste non modifiable
    @Test
    void mergeMiahootEntity() {
        // Créer une entité Miahoot existante avec deux questions
        List<QuestionEntity> questions = Arrays.asList(
                QuestionEntity.builder()
                        .label("Qui est l'homme le plus riche du monde")
                        .build(),
                QuestionEntity.builder()
                        .label("Qu'est ce qu'une question")
                        .build()
        );
        MiahootEntity miahootEntity = MiahootEntity.builder()
                .nom("Miahoot1")
                .questions(questions)
                .build();
        int miahootId = miahootEntity.getMiahootId(); // Récupérer l'ID de l'entité existante

        // Convertir l'entité en DTO pour la modification
        Miahoot miahoot = miahootMapper.toDto(miahootEntity);

        // Modifier le DTO (par exemple, ajouter une question)
        Question newQuestion = Question.builder()
                .label("Question 3")
                .build();
        miahoot.getQuestions().add(newQuestion);

        // Fusionner les modifications dans l'entité existante
        miahootMapper.mergeMiahootEntity(miahootEntity, miahoot);

        // Vérifier que l'entité a été correctement modifiée
        assertEquals(miahootId, miahootEntity.getMiahootId()); // L'ID ne doit pas avoir changé
        assertEquals(miahoot.getNom(), miahootEntity.getNom()); // Le nom doit être le même
        assertEquals(miahoot.getQuestions().size(), miahootEntity.getQuestions().size()); // Le nombre de questions doit être le même
        assertEquals(miahoot.getQuestions().get(0).getLabel(), miahootEntity.getQuestions().get(0).getLabel()); // La première question doit être la même
        assertEquals(miahoot.getQuestions().get(1).getLabel(), miahootEntity.getQuestions().get(1).getLabel()); // La deuxième question doit être la même
        assertEquals(newQuestion.getLabel(), miahootEntity.getQuestions().get(2).getLabel()); // La nouvelle question doit être présente
    }


}
