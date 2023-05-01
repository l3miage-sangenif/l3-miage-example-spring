package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.TestMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.TestRepository;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.Reponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ici on teste le composant Miahoot<br>
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
class MiahootComponentTest {

    @Autowired
    private MiahootRepository miahootRepository;
    @Autowired
    private MiahootComponent miahootComponent;
    @Autowired
    private MiahootMapper miahootMapper;

    @AfterEach
    void clear() {
        miahootRepository.deleteAll();
    }

    @Test
    void getMiahoot() throws EntityNotFoundException {
       //construction du miahoot
        MiahootEntity entity = MiahootEntity.builder()
                .nom("Miahoot 1")
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

        //creation dans la bd du miahoot(précédemment construit)
        miahootComponent.createMiahoot(entity);

        //recuperation du Miahoot créé
        MiahootEntity actualEntity = miahootComponent.getMiahoot(entity.getMiahootId());

        //comparaison
        assertThat(actualEntity).usingRecursiveComparison().isEqualTo(entity);
    }

    @Test
    void CreateMiahoot() throws Exception {
        CreateMiahootRequest request = CreateMiahootRequest.builder()
                .nom("Miahoot 1")
                .questions(new ArrayList<>(Arrays.asList(
                        CreateQuestionRequest.builder()
                                .label("Qui a écrit \"Les Misérables\"")
                                .reponses(new ArrayList<>(Arrays.asList(
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
                                )))
                                .build(),
                        CreateQuestionRequest.builder()
                                .label("Quel est le plus grand pays du monde par sa superficie")
                                .reponses(new ArrayList<>(Arrays.asList(
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
                                )))
                                .build()
                )))
                .build();

        MiahootEntity expected = miahootMapper.toEntity(request);

        miahootComponent.createMiahoot(expected);

        MiahootEntity entityGet = miahootComponent.getMiahoot(expected.getMiahootId());
        assertThat(entityGet).usingRecursiveComparison()
                .isEqualTo(expected);
    }


    @Test
    void updateMiahoot() throws Exception {
        //construction du miahoot
        MiahootEntity miahootEntity = MiahootEntity.builder()
                .nom("Miahoot 1")
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
        miahootRepository.save(miahootEntity);

        Miahoot updatedMiahoot = Miahoot.builder()
                .nom("Miahoot 1 - modifié")
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

        miahootComponent.updateMiahoot(miahootEntity.getMiahootId(), updatedMiahoot);


        assertThat(miahootMapper.toDto(miahootComponent.getMiahoot(miahootEntity.getMiahootId())))
                .usingRecursiveComparison()
                .isEqualTo(updatedMiahoot);
    }

    @Transactional
    @Test
    void DeleteMiahoot() throws EntityNotFoundException {
        MiahootEntity miahootEntity = MiahootEntity.builder()
                .nom("Miahoot 1")
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
        miahootRepository.save(miahootEntity);

        miahootComponent.deleteMiahoot(miahootEntity.getMiahootId());

        assertThat(miahootRepository.count()).isZero();
    }

}
