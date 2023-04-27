package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.response.Reponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReponseMapper {
    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Reponse
     * @param reponseEntity l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Reponse
     */
    Reponse toDTO(ReponseEntity reponseEntity);

    /**
     * Cette fonction fait le mapping d'un DTO  vers un <b color="yellow">ReponseEntity</b>
     * @param reponse le DTO à mapper en <b color="yellow">Entity</b>
     * @return la ReponseEntity correspondante
     */
    ReponseEntity toEntity(Reponse reponse);
}
