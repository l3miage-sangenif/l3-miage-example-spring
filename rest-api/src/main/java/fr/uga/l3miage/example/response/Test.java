package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Test")
public class Test {
    @Schema(description = "correspond à la description de l'objet",example = "cet objet est un test")
    String description;

    @Schema(description = "correspond au champs qui devra être mappé explicitement par le mapper TestMapper",example = "quelconque")
    String fieldMapping;

    @Schema(description = "Correspond à la somme de test1 et test2 dans la requête CreateTest",example = "12")
    int testInt;

    @Schema(description = "Correspond à un boolean quelconque",example = "true")
    Boolean isTest;
}
