package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Représente le <b color="yellow">DTO</b> de l'entité Miahoot
 * <br>
 * Les annotations :
 * <ul>
 *     <li>{@link Data} est une annotation raccourci pour plusieurs annotations de lombok<br>Aller voir la doc sur <a href="https://projectlombok.org/features/Data">projetlombok.org/features/data</a></a></li></li>
 *     <li>{@link Builder} permet de créer un builder(<a href="https://refactoring.guru/fr/design-patterns/builder">patron builder</a>) pour la classe.<br>Aller voir la doc sur <a href="https://projectlombok.org/features/Builder">projetlombok.org/features/Builder</a></a></li></li></li>
 * </ul>
 */
@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Miahoot")
public class Miahoot {
    @Schema(description = "correspond a une chaine de caractère quelconque qui est le nopm associé a un miahoot",example = "uml")
    private String nom;

    @Schema(description = "correspond a une liste d'objet question contenu dans un miahoot")
    private List<Question> qcms;
}

