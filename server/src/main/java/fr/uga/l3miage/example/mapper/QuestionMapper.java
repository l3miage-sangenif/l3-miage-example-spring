package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.Question;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(uses = ReponseMapper.class)
public interface QuestionMapper {
    Question toDto(QuestionEntity questionEntity);

    QuestionEntity toEntity(CreateQuestionRequest request);

    void mergeQuestionEntity(@MappingTarget @NonNull QuestionEntity baseEntity, Question question);

}
