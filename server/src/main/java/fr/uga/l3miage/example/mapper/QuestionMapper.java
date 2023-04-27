package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.response.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Question
     * @param questionEntity l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Reponse
     */
    Question toDTO(QuestionEntity questionEntity);

    /**
     * Cette fonction fait le mapping d'un DTO  vers un <b color="yellow">QuestionEntity</b>
     * @param question le DTO à mapper en <b color="yellow">Entity</b>
     * @return la QuestionEntity correspondante
     */
    QuestionEntity toEntity(Question question);
}
