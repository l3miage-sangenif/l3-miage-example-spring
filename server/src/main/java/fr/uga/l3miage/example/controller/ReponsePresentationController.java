package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ReponsePresentationEndpoint;
import fr.uga.l3miage.example.request.CreateReponsePresentationRequest;
import fr.uga.l3miage.example.response.ReponsePresentation;
import fr.uga.l3miage.example.service.ReponsePresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ReponsePresentationController implements ReponsePresentationEndpoint {
    private final ReponsePresentationService ReponseService;

    @Override
    public ReponsePresentation getReponseEntity(final int reponseId) {
        return ReponseService.getReponse(reponseId);
    }

    @Override
    public int getNbReponseEntity(final int reponseId) {
        return ReponseService.getNbReponse(reponseId);
    }
/*
    @Override
    public void createReponseEntity(final CreateReponsePresentationRequest request) {
        ReponseService.createReponse(request);
    }*/

    @Override
    public void updateReponseEntity(final int reponseId, final ReponsePresentation reponse) {
        ReponseService.updateReponse(reponseId, reponse);
    }

    @Override
    public void deleteReponseEntity(final int reponseId) {
        ReponseService.deleteReponse(reponseId);
    }
}
