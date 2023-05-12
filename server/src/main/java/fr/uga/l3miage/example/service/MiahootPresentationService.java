package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootPresentationComponent;
import fr.uga.l3miage.example.exception.rest.EntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.EntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.EntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootPresentationMapper;
import fr.uga.l3miage.example.mapper.QuestionPresentationMapper;
import fr.uga.l3miage.example.models.MiahootPresentationEntity;
import fr.uga.l3miage.example.models.QuestionEntity;
import fr.uga.l3miage.example.models.QuestionPresentationEntity;
import fr.uga.l3miage.example.models.UserEntity;
import fr.uga.l3miage.example.repository.QuestionPresentationRepository;
import fr.uga.l3miage.example.repository.UserRepository;
import fr.uga.l3miage.example.request.CreateMiahootPresentationRequest;
import fr.uga.l3miage.example.response.MiahootPresentation;
import fr.uga.l3miage.example.response.QuestionPresentation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MiahootPresentationService {
    private final MiahootPresentationComponent miahootComponent;
    private final MiahootPresentationMapper miahootMapper;
    private final QuestionPresentationMapper questionMapper;
    private final QuestionPresentationService questionService;
    private  final UserService userService;
    private final UserRepository userRepository;
    private final QuestionPresentationRepository questionPresentationRepository;


    public MiahootPresentation getMiahoot(final int miahootId) {
        try {
            return miahootMapper.toDto(miahootComponent.getMiahoot(miahootId));
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

    public List<MiahootPresentation> getAllMiahootByUser(final String uid) {
        try {
            //List<MiahootEntity> miahootEntities=miahootComponent.getAllMiahootByUserUid(uid);
            List<MiahootPresentationEntity> miahootEntities=userService.getAllMiahootPresentationByUserUid(uid).stream().collect(Collectors.toList());
            List<MiahootPresentation> miahoots = new ArrayList<>();
            for(MiahootPresentationEntity miahootEntity: miahootEntities){
                miahoots.add(miahootMapper.toDto(miahootEntity));
            }
            return miahoots;
        } catch (Exception ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), 0, ex);
        }
    }

    public void createMiahoot(final CreateMiahootPresentationRequest createMiahootRequest) {
        UserEntity user = userRepository.findById(createMiahootRequest.getUid())
                .orElseThrow(() -> new EntityNotFoundRestException("User not found with id: " + createMiahootRequest.getUid().toString(),404));
        MiahootPresentationEntity newMiahootEntity = miahootMapper.toEntity(createMiahootRequest);
        newMiahootEntity.setUser(user);
        newMiahootEntity.setEnCours(true);
        miahootComponent.createMiahootPresentation(newMiahootEntity);

    }


    public void updateMiahoot(final int miahootId, final MiahootPresentation miahoot) {
            try {
                miahootComponent.updateMiahootPresentation(miahootId, miahoot);
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
            }
    }


    @Transactional
    public void deleteMiahoot(int miahootId) {
        try {
            miahootComponent.deleteMiahootPresentation(miahootId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }

    public void closeMiahoot(int miahootId) {
        try {
            miahootComponent.closeMiahoot(miahootId);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotDeletedRestException(ex.getMessage());
        }
    }

    public QuestionPresentation getQuestionCourante(final int miahootId) {
        try {
            List<QuestionPresentationEntity> listQ = miahootComponent.getMiahoot(miahootId).getQuestions();
            for (QuestionPresentationEntity q:listQ
                 ) {
                if (q.isQuestionEnCours())
                    return questionMapper.toDto(q);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

    public void questionSuivante(final int miahootId) {
        try {
            List<QuestionPresentationEntity> listQ = miahootComponent.getMiahoot(miahootId).getQuestions();
            int i=0;
            while (i<listQ.size()-1){
                if (listQ.get(i).isQuestionEnCours()){
                    listQ.get(i).setQuestionEnCours(false);
                    questionPresentationRepository.save(listQ.get(i));
                    listQ.get(i+1).setQuestionEnCours(true);
                    questionPresentationRepository.save(listQ.get(i+1));
                    return;
                }
            }
            if (listQ.get(i).isQuestionEnCours()){
                listQ.get(i).setQuestionEnCours(false);
                questionPresentationRepository.save(listQ.get(i));
                return;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", ex.getMessage()), miahootId, ex);
        }
    }

}
