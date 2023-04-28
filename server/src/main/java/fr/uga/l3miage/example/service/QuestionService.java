package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionComponent QuestionComponent;
    private final QuestionMapper QuestionMapper;


    public Question getQuestion(final int questionId) {
        try {
            return QuestionMapper.toDto(QuestionComponent.getQuestion(questionId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), questionId, ex);
        }
    }


    public void createQuestion(final CreateQuestionRequest createQuestionRequest) {
        QuestionEntity newQuestionEntity = QuestionMapper.toEntity(createQuestionRequest);
        QuestionComponent.createQuestion(newQuestionEntity);
    }


    public void updateQuestion(final int questionId, final Question Question) {
            try {
                QuestionComponent.updateQuestion(questionId, Question);
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), questionId, ex);
            }
    }


    @Transactional
    public void deleteQuestion(int questionId) {
        try {
            QuestionComponent.deleteQuestion(questionId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }


}
