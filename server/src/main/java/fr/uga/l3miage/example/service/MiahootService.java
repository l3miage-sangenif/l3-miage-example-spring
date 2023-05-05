package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.Miahoot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MiahootService {
    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;
    private final QuestionMapper questionMapper;
    private final QuestionService questionService;
    private  final UserService userService;


    public Miahoot getMiahoot(final int miahootId) {
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(miahootId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

    public List<Miahoot> getAllMiahootByUser(final String uid) {
        try {
            //List<MiahootEntity> miahootEntities=miahootComponent.getAllMiahootByUserUid(uid);
            List<MiahootEntity> miahootEntities=userService.getAllMiahootByUserUid(uid);
            List<Miahoot> miahoots = new ArrayList<>();
            for(MiahootEntity miahootEntity: miahootEntities){
                miahoots.add(miahootMapper.toDto(miahootEntity));
            }
            return miahoots;
        } catch (Exception ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), 0, ex);
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
