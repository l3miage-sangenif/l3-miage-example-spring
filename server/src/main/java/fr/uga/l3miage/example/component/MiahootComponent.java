package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.response.Miahoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final MiahootMapper miahootMapper;


    public MiahootEntity getMiahoot(final Integer miahootId) throws EntityNotFoundException {
        return miahootRepository.findByMiahootId(miahootId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la Miahoot [%s]", miahootId), miahootId));
    }

    public void createMiahoot(final MiahootEntity entity) {
            miahootRepository.save(entity);
    }


    public void updateMiahoot(final int miahootId, final Miahoot miahoot) throws EntityNotFoundException {
            MiahootEntity actualEntity = miahootRepository.findByMiahootId(miahootId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Aucune entité n'a été trouvé pour miahootId [%s]", miahootId), miahootId));
            miahootMapper.mergeMiahootEntity(actualEntity, miahoot);
            miahootRepository.save(actualEntity);
    }



    public void deleteMiahoot(final Integer miahootId) throws EntityNotFoundException {
        int deleted = miahootRepository.deleteByMiahootId(miahootId);
        if (deleted == 0){
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée", (Integer)miahootId);
        }


    }
}
