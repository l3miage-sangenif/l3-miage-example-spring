package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité User")
public class CreateUserRequest {
    @Schema(description = "User id")
    private String uid;

    @Schema(description = "nom du user", example = "john")
    private String nom;

}
