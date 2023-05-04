package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Miahoot")
public class Miahoot {
    @Schema(description = "Correspond a l'id de l'objet miahoot")
    int miahootId;

    @Schema(description = "Correspond au label de l'objet", example = "Miahoot affiché")
    String nom;

    @Schema(description = "Liste de questions", example = "[{label:'Q1', [{label:'reponse 1', estValide: false}, {label:'reponse 2', estValide:true}]}, {label:'Q2', [{label:'reponse 3', estValide: false}, {label:'reponse 4', estValide:true}]},]")
    List<Question> questions;
}
