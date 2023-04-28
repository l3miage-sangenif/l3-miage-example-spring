package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.response.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDTO(UserEntity userEntity);

    UserEntity toEntity(User user);
}