package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootPresentationEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootPresentationRequest;
import fr.uga.l3miage.example.response.MiahootPresentation;
import fr.uga.l3miage.example.response.QuestionPresentation;
import fr.uga.l3miage.example.service.MiahootPresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MiahootPresentationController implements MiahootPresentationEndpoint {
    private final MiahootPresentationService miahootService;

    @Override
    public MiahootPresentation getMiahootPresentationEntity(final int miahootId) {
        return miahootService.getMiahoot(miahootId);
    }

    @Override
    public List<MiahootPresentation> getAllMiahootByUserUid(final String uid) {
        return miahootService.getAllMiahootByUser(uid);
    }
    @Override
    public void createMiahootPresentationEntity(final CreateMiahootPresentationRequest request) {
        miahootService.createMiahoot(request);
    }

    @Override
    public void updateMiahootEntity(final int MiahootId, final MiahootPresentation miahoot) {
        miahootService.updateMiahoot(MiahootId, miahoot);
    }

    @Override
    public void deleteMiahootEntity(final int miahootId) {
        miahootService.deleteMiahoot(miahootId);
    }

    @Override
    public void closePresentation(final int miahootId) {
        miahootService.closeMiahoot(miahootId);
    }

    @Override
    public QuestionPresentation getQuestionPresentationEntity(final int miahootId) {
        return miahootService.getQuestionCourante(miahootId);
    }
    @Override
    public void postQuestionPresentationEntity(final int miahootId) {
        miahootService.questionSuivante(miahootId);
    }


}
