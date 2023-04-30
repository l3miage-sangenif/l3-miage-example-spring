package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Les annotations :
 * <ul>
 *     <li>{@link Repository} permet de dire à spring que cette interface peut être candidate à l'injection</li>
 * </ul>
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    /**
     * En JPA le nom de la méthode est parsé afin de créer la requête adéquate en fonction de votre besoin
     * ici pour chercher une entité
     *
     * @param questionId de l'entité recherchée
     * @return {@link Optional}<{@link QuestionEntity}>
     */
    Optional<QuestionEntity> findByQuestionId(final Integer questionId);

    /**
     * En JPA le nom de la méthode est parsé afin de créer la requête adéquate en fonction de votre besoin
     * Ici pour delete une entité
     *
     * @param quetionId de l'entité à supprimer
     * @return le nombre d'éléments supprimés
     */
    int deleteByQuestionId(final Integer quetionId);
}
