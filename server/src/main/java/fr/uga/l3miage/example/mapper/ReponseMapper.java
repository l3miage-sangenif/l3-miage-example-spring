package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.mapper.utils.ReponseMapperUtils;
import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.Reponse;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(uses = ReponseMapperUtils.class)
public interface ReponseMapper {

    Reponse toDto(ReponseEntity reponseEntity);


    ReponseEntity toEntity(CreateReponseRequest request);



    void mergeReponseEntity(@MappingTarget @NonNull ReponseEntity baseEntity, Reponse reponse);

}
