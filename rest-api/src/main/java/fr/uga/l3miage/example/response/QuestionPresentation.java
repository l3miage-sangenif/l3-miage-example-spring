package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Question")
public class QuestionPresentation {

    @Schema(description = "Correspond a l'id de l'objet question")
    int questionId;

    @Schema(description = "Correspond au label de l'objet", example = "Quetion affichée")
    String label;

    @Schema(description = "Liste de reponses", example = "[{label:'R1', estValide:true}, {label:'R2', estValide:false}]")
    List<Reponse> reponses;

}
