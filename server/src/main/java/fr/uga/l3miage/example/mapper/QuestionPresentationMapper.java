package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.QuestionPresentationEntity;
import fr.uga.l3miage.example.request.CreateQuestionPresentationRequest;
import fr.uga.l3miage.example.response.QuestionPresentation;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(uses = ReponsePresentationMapper.class)
public interface QuestionPresentationMapper {
    QuestionPresentation toDto(QuestionPresentationEntity questionEntity);

    QuestionPresentationEntity toEntity(CreateQuestionPresentationRequest request);

    void mergeQuestionPresentationEntity(@MappingTarget @NonNull QuestionPresentationEntity baseEntity, QuestionPresentation question);

}
