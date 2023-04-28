package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité User")
public class User {
    @Schema(description = "User id")
    private Long uid;

    @Schema(description = "nom du user",example = "john")
    private String nom;

}