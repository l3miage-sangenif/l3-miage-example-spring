package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.service.MiahootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MiahootController implements MiahootEndpoint {
    private final MiahootService miahootService;

    @Override
    public Miahoot getMiahootEntity(final int miahootId) {
        return miahootService.getMiahoot(miahootId);
    }

    @Override
    public void createMiahootEntity(final CreateMiahootRequest request) {
        miahootService.createMiahoot(request);
    }

    @Override
    public void updateMiahootEntity(final int MiahootId, final Miahoot miahoot) {
        miahootService.updateMiahoot(MiahootId, miahoot);
    }

    @Override
    public void deleteMiahootEntity(final int miahootId) {
        miahootService.deleteMiahoot(miahootId);
    }
}
