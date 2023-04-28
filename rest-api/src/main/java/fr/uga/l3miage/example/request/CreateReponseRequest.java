package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité Reponse")
public class CreateReponseRequest {

    @Schema(description = "Label de l'objet Reponse", example = "Reponse 1")
    private String label;

    @Schema(description = "Determine si la Reponse est valide ou non", example = "true")
    private Boolean estValide;
}
