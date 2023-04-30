package fr.uga.l3miage.example.request;

import fr.uga.l3miage.example.response.Reponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité Question")
public class CreateQuestionRequest {

    @Schema(description = "Label de l'objet Quetion", example = "Question : question ?")
    private String label;

    @Schema(description = "Liste de reponses", example = "[{label:'R1', estValide:true}, {label:'R2', estValide:false}]")
    private List<CreateReponseRequest> reponses;
}
