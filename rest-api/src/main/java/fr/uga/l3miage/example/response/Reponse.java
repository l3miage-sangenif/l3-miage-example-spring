package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Reponse")
public class Reponse {

    @Schema(description = "Correspond a l'id de l'objet reponse")
    int reponseId;

    @Schema(description = "Correspond au label de l'objet", example = "Reponse 1 affichée")
    String label;

    @Schema(description = "Correspond à la validité de la Reponse", example = "true")
    Boolean estValide;

}
