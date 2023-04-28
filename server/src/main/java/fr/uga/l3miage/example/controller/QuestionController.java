package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.QuestionEndpoint;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionEndpoint {
    private final QuestionService questionService;

    @Override
    public Question getQuestionEntity(final int questionId) {
        return questionService.getQuestion(questionId);
    }

    @Override
    public void createQuestionEntity(final CreateQuestionRequest request) {
        questionService.createQuestion(request);
    }

    @Override
    public void updateQuestionEntity(final int QuestionId, final Question question) {
        questionService.updateQuestion(QuestionId, question);
    }

    @Override
    public void deleteQuestionEntity(final int questionId) {
        questionService.deleteQuestion(questionId);
    }
}
