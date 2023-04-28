package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.IsNotMiahootException;
import fr.uga.l3miage.example.exception.technical.DescriptionAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MiahootComponent {
    private final MiahootRepository miahootRepository;
    private final UserRepository userRepository;
    private final MiahootMapper miahootMapper;

    public MiahootEntity getMiahoot(final String nom, final Long uid) throws TestEntityNotFoundException {
        if(userRepository.findByUid(uid).isPresent()) {
            Optional<UserEntity> user = userRepository.findByUid(uid);
            List<MiahootEntity> miahoots = user.get().getMiahoots();
            for (MiahootEntity m: miahoots) {
                if(m.getNom().equals(nom)){
                    return m;
                }
            }
        }
        throw new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la description [%s]", nom), nom);
    }

    public List<MiahootEntity> getAllMiahoot(final String nom, final Long uid) throws TestEntityNotFoundException {
        if(userRepository.findByUid(uid).isPresent()) {
        return userRepository.findByUid(uid).get().getMiahoots();
        }
        throw new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour la description [%s]", nom), nom);
    }

    public void createMiahoot(final MiahootEntity entity, final Long uid) throws IsNotMiahootException, DescriptionAlreadyExistException {
        if(userRepository.findByUid(uid).isPresent()) {
            List<MiahootEntity> miahoots = userRepository.findByUid(uid).get().getMiahoots();
            for (MiahootEntity m: miahoots
                 ) {
                if (m.getNom().equals(entity.getNom())) {
                    throw new DescriptionAlreadyExistException(String.format("La description %s existe déjà en BD.", entity.getNom()), entity.getNom());
                }
            }
            miahootRepository.save(entity);
        } else throw new IsNotMiahootException("Le champs isTest n'est pas à true, donc erreur technique levée", entity);
    }

    public void deleteMiahoot(final String nom, final Long uid) throws TestEntityNotFoundException {
        if(userRepository.findByUid(uid).isPresent()) {
            Long id = null;
            Optional<UserEntity> user = userRepository.findByUid(uid);
            List<MiahootEntity> miahoots = user.get().getMiahoots();
            for (MiahootEntity m: miahoots) {
                if(m.getNom().equals(nom)){
                    id = m.getIdMiahoot();
                }
            }
            if(id==null) {
                throw new TestEntityNotFoundException("L'entité à supprimer n'a pas été trouvée", nom);
            }
            miahootRepository.deleteByIdMiahoot(id);
        }




    }
}
