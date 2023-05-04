package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.UserComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.UserMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateUserRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserComponent userComponent;
    private final UserMapper userMapper;


    public void createMiahoot(final CreateUserRequest createUserRequest) {
        UserEntity newUserEntity = userMapper.toEntity(createUserRequest);
        userComponent.createUser(newUserEntity);
    }


    public void updateUser(final String uid, final User user) {
        try {
            userComponent.updateUser(uid, user);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), 404);
        }
    }

    @Transactional
    public void deleteUser(String uid) {
        try {
            userComponent.deleteUser(uid);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }
}
