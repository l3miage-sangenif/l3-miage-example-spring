package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionPresentationMapper;
import fr.uga.l3miage.example.models.QuestionPresentationEntity;
import fr.uga.l3miage.example.repository.QuestionPresentationRepository;
import fr.uga.l3miage.example.response.QuestionPresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QuestionPresentationComponent {
    private final QuestionPresentationRepository QuestionPresentationRepository;
    private final QuestionPresentationMapper QuestionPresentationMapper;


    public QuestionPresentationEntity getQuestionPresentation(final int questionId) throws EntityNotFoundException {
        return QuestionPresentationRepository.findByQuestionId(questionId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la Question [%s]", questionId), questionId));
    }

    public void createQuestionPresentation(final QuestionPresentationEntity entity) {
            QuestionPresentationRepository.save(entity);
    }


    public void updateQuestionPresentation(final int questionId, final QuestionPresentation Question) throws EntityNotFoundException {
            QuestionPresentationEntity actualEntity = QuestionPresentationRepository.findByQuestionId(questionId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour reponseId [%s]", questionId), questionId));
            QuestionPresentationMapper.mergeQuestionPresentationEntity(actualEntity, Question);
            QuestionPresentationRepository.save(actualEntity);
    }



    public void deleteQuestionPresentation(final int questionId) throws EntityNotFoundException {
        int deleted = QuestionPresentationRepository.deleteByQuestionId(questionId);
        if (deleted == 0){
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", questionId);
        }


    }
}
