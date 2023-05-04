package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateUserRequest;
import fr.uga.l3miage.example.response.Question;
import fr.uga.l3miage.example.response.User;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = MiahootMapper.class)
public interface UserMapper {

    User toDTO(UserEntity userEntity);

    UserEntity toEntity(CreateUserRequest request);

    void mergeUserEntity(@MappingTarget @NonNull UserEntity baseEntity, User user);
}