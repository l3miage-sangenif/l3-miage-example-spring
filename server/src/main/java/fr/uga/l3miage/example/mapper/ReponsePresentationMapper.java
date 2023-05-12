package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.mapper.utils.ReponseMapperUtils;
import fr.uga.l3miage.example.models.ReponsePresentationEntity;
import fr.uga.l3miage.example.request.CreateReponsePresentationRequest;
import fr.uga.l3miage.example.response.ReponsePresentation;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(uses = ReponseMapperUtils.class)
public interface ReponsePresentationMapper {

    ReponsePresentation toDto(ReponsePresentationEntity reponseEntity);


    ReponsePresentationEntity toEntity(CreateReponsePresentationRequest request);



    void mergeReponsePresentationEntity(@MappingTarget @NonNull ReponsePresentationEntity baseEntity, ReponsePresentation reponse);

}
