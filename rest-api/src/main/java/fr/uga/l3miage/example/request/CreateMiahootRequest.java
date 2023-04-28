package fr.uga.l3miage.example.request;

import fr.uga.l3miage.example.response.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité Miahoot")
public class CreateMiahootRequest {

    @Schema(description = "Label de l'objet Miahoot", example = "Miahoot 1")
    private String nom;

    @Schema(description = "Liste de questions", example = "[{label:'Q1'}, {label:'Q2'}]")
    private List<Question> questions;
}
