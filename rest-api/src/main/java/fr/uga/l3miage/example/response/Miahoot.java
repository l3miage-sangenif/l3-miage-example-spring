package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Miahoot")
public class Miahoot {

    @Schema(description = "Correspond au label de l'objet", example = "Miahoot affiché")
    String nom;

    @Schema(description = "Liste de questions", example = "[{label:'Q1'}, {label:'Q2'}]")
    List<Question> questions;
}
