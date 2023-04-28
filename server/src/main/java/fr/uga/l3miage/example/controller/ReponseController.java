package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ReponseEndpoint;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Reponse;
import fr.uga.l3miage.example.service.ReponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ReponseController implements ReponseEndpoint {
    private final ReponseService ReponseService;

    @Override
    public Reponse getReponseEntity(final int reponseId) {
        return ReponseService.getReponse(reponseId);
    }

    @Override
    public void createReponseEntity(final CreateReponseRequest request) {
        ReponseService.createReponse(request);
    }

    @Override
    public void updateReponseEntity(final int reponseId, final Reponse reponse) {
        ReponseService.updateReponse(reponseId, reponse);
    }

    @Override
    public void deleteReponseEntity(final int reponseId) {
        ReponseService.deleteReponse(reponseId);
    }
}
