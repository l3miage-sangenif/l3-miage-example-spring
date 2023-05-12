package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.UserEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateUserRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.User;
import fr.uga.l3miage.example.service.MiahootService;
import fr.uga.l3miage.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserEndpoint {
    private final UserService userService;

    @Override
    public void createUser(final CreateUserRequest request) {
        userService.createMiahoot(request);
    }

    @Override
    public void updateUser(final String uid, final User user) {
        userService.updateUser(uid, user);
    }

    @Override
    public void deleteUser(final String uid ) {
        userService.deleteUser(uid);
    }

    @Override
    public void addParticipant(final String userId,final int miahootId) {
        userService.addParticipant(userId,miahootId);
    }

}
