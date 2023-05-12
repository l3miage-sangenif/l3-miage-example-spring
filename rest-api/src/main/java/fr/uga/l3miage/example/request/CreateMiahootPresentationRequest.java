package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité Miahoot")
public class CreateMiahootPresentationRequest {

    @Schema(description = "Uid de l'utilisateur", example = "user1")
    private String uid;

    @Schema(description = "Label de l'objet Miahoot", example = "Miahoot 1")
    private String nom;

    @Schema(description = "Liste de questions", example = "[{\"label\":\"Q1\", \"reponses\": [{\"label\":\"reponse 1\", \"estValide\": false}, {\"label\":\"reponse 2\", \"estValide\":true}]}, {\"label\":\"Q2\", \"reponses\": [{\"label\":\"reponse 3\", \"estValide\": false}, {\"label\":\"reponse 4\", \"estValide\":true}]}]")
    private List<CreateQuestionPresentationRequest> questions;
}
