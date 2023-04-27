package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.response.Miahoot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MiahootMapper {

    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Reponse
     * @param miahootEntity l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Reponse
     */
    Miahoot toDTO(MiahootEntity miahootEntity);

    /**
     * Cette fonction fait le mapping d'un DTO  vers un <b color="yellow">MiahootEntity</b>
     * @param miahoot le DTO à mapper en <b color="yellow">Entity</b>
     * @return la MiahootEntity correspondante
     */
    MiahootEntity toEntity(Miahoot miahoot);
}
