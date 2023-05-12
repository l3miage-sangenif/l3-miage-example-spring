package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.MiahootPresentationEntity;
import fr.uga.l3miage.example.request.CreateMiahootPresentationRequest;
import fr.uga.l3miage.example.response.MiahootPresentation;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(uses = QuestionPresentationMapper.class)
public interface MiahootPresentationMapper {
    MiahootPresentation toDto(MiahootPresentationEntity miahootPresentationEntity);

    MiahootPresentationEntity toEntity(CreateMiahootPresentationRequest request);

    void mergeMiahootPresentationEntity(@MappingTarget @NonNull MiahootPresentationEntity baseEntity, MiahootPresentation Miahoot);



}
