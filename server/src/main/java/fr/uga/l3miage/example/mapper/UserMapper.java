package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.response.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Cette fonction va faire le mapping d'une entité vers le <b color="yellow">DTO</b> de Reponse
     * @param userEntity l'entité à mapper en <b color="yellow">DTO</b>
     * @return le <b color="yellow">DTO</b> d'une entité Reponse
     */
    User toDTO(UserEntity userEntity);

    /**
     * Cette fonction fait le mapping d'un DTO  vers un <b color="yellow">UserEntity</b>
     * @param user le DTO à mapper en <b color="yellow">Entity</b>
     * @return la UserEntity correspondante
     */
    UserEntity toEntity(User user);
}
