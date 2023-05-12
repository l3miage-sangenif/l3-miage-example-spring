package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.EntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateReponsePresentationRequest;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponsePresentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(name = "Reponse tag")
@CrossOrigin
@RestController
@RequestMapping("api/presentation/reponses/")
public interface ReponsePresentationEndpoint {

    @Operation(description = "Récupérer le DTO de l'entité Reponse qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Reponse demandée",
            content = @Content(schema = @Schema(implementation = ReponsePresentation.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{reponseId}")
    ReponsePresentation getReponseEntity(@PathVariable int reponseId);

    @Operation(description = "Récupérer le DTO de l'entité Reponse qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Reponse demandée",
            content = @Content(schema = @Schema(implementation = Integer.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("nb/{reponseId}")
    int getNbReponseEntity(@PathVariable int reponseId);

/*
    @Operation(description = "Création d'une entité Reponse")
    @ApiResponse(responseCode = "201", description = "L'entité Reponse a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createReponseEntity(@Valid @RequestBody CreateReponseRequest request);
*/
    @Operation(description = "Mise à jour d'une entité Reponse")
    @ApiResponse(responseCode = "202", description = "L'entité à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("{reponseId}")
    void updateReponseEntity(@PathVariable final int reponseId, @RequestBody final ReponsePresentation reponse);


    @Operation(description = "Suppression d'une entité Reponse en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors 'Hello word' est renvoyé")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{reponseId}")
    void deleteReponseEntity(@PathVariable int reponseId);
}
