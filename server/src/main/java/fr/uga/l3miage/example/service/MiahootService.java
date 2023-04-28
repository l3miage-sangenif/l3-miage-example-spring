package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.Miahoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class MiahootService {
    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;


    public Miahoot getMiahoot(final int miahootId) {
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(miahootId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }


    public void createMiahoot(final CreateMiahootRequest createMiahootRequest) {
        MiahootEntity newMiahootEntity = miahootMapper.toEntity(createMiahootRequest);
        miahootComponent.createMiahoot(newMiahootEntity);
    }


    public void updateMiahoot(final int miahootId, final Miahoot miahoot) {
            try {
                miahootComponent.updateMiahoot(miahootId, miahoot);
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
            }
    }


    @Transactional
    public void deleteMiahoot(int miahootId) {
        try {
            miahootComponent.deleteMiahoot(miahootId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }


}
