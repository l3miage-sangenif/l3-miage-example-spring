package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.response.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QuestionComponent {
    private final QuestionRepository QuestionRepository;
    private final QuestionMapper QuestionMapper;


    public QuestionEntity getQuestion(final int questionId) throws EntityNotFoundException {
        return QuestionRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la Question [%s]", questionId), questionId));
    }

    public void createQuestion(final QuestionEntity entity) {
            QuestionRepository.save(entity);
    }


    public void updateQuestion(final int questionId, final Question Question) throws EntityNotFoundException {
            QuestionEntity actualEntity = QuestionRepository.findByQuestionId(questionId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour reponseId [%s]", questionId), questionId));
            QuestionMapper.mergeQuestionEntity(actualEntity, Question);
            QuestionRepository.save(actualEntity);
    }



    public void deleteQuestion(final int questionId) throws EntityNotFoundException {
        int deleted = QuestionRepository.deleteByQuestionId(questionId);
        if (deleted == 0){
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", questionId);
        }


    }
}
