package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.Reponse;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ici on teste le composant Question<br>
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

class QuestionComponentTest {

    @Autowired
    private QuestionComponent questionComponent;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void getQuestion() throws EntityNotFoundException {
        // creation de la questionEntity qu'on va enregistrer puis recuperer
        QuestionEntity questionToSave = QuestionEntity.builder()
                .label("Quel est le plus grand pays du monde par sa superficie ?")
                .reponses(Arrays.asList(
                        /*ReponseEntity.builder()
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
                                .build()*/
                )).build();
        //enregistrement de la question
        questionComponent.createQuestion(questionToSave);

        // recuperation de la question
        QuestionEntity questionEntityResult = questionComponent.getQuestion(questionToSave.getQuestionId());

        // Pour le moment get question permet juste de recuperer une le label d'une question et une liste vide des réponses, on ne récupère pas les label et estValide des réponse de cette question
        // on verifie si ce qu'on a recupérer est bien ce que l'on voulait
        assertThat(questionEntityResult).usingRecursiveComparison()
                .isEqualTo(questionToSave);
    }

    @Test
    void createQuestion() {
        // Arrange
        QuestionEntity questionToCreate = QuestionEntity.builder()
                .label("Question : test ?")
                .reponses(Arrays.asList(
                        ReponseEntity.builder().label("R1").estValide(true).build(),
                        ReponseEntity.builder().label("R2").estValide(false).build()
                ))
                .build();

        // Act
        questionComponent.createQuestion(questionToCreate);

        // Assert
        assertThat(questionRepository.count()).isOne();
        //on recupere la question de la base
        QuestionEntity questionFromDB=questionRepository.findById(questionToCreate.getQuestionId()).get();
        assertThat(questionFromDB).usingRecursiveComparison().isEqualTo(questionToCreate);
    }

    @Test
    void updateQuestion() throws EntityNotFoundException {
        // Arrange
        QuestionEntity questionEntity = QuestionEntity.builder().label("Question : test ?")
                .reponses(Arrays.asList(
                        ReponseEntity.builder().label("R1").estValide(true).build(),
                        ReponseEntity.builder().label("R2").estValide(false).build()
                )).build();
        QuestionEntity savedEntity=questionRepository.save(questionEntity);

        Question request = Question.builder()
                .label("Question : test mise à jour ?")
                .reponses(new ArrayList<>(Arrays.asList(
                        Reponse.builder().label("R1").estValide(true).build(),
                        Reponse.builder().label("R2").estValide(true).build()
                )))
                .build();

        // Act
        questionComponent.updateQuestion(savedEntity.getQuestionId(), request);

        // Assert
        assertThat(questionRepository.findById(questionEntity.getQuestionId())).isNotEmpty().get().satisfies(entity -> {
            assertThat(entity.getLabel()).isEqualTo(request.getLabel());
            assertEquals(request.getReponses().size(), entity.getReponses().size());
        });
    }

    @Transactional
    @Test
    void deleteQuestion() throws EntityNotFoundException {
        // Arrange
        QuestionEntity questionEntity = QuestionEntity.builder().label("Question : test ?")
                .reponses(Arrays.asList(
                        ReponseEntity.builder().label("R1").estValide(true).build(),
                        ReponseEntity.builder().label("R2").estValide(false).build()
                )).build();
        QuestionEntity savedEntity = questionRepository.save(questionEntity);

        // Act
        questionComponent.deleteQuestion(questionEntity.getQuestionId());

        // Assert
        // on verifie qu'il n'y a plus rien dans la base vu qu'on y a mis seulement un element qu'on vient de supprimer
        assertThat(questionRepository.count()).isZero();
        assertThat(questionRepository.findById(savedEntity.getQuestionId())).isEmpty();
    }

}

