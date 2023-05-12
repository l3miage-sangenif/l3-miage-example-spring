package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.EntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateMiahootPresentationRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.MiahootPresentation;
import fr.uga.l3miage.example.response.QuestionPresentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "Miahoot presentation tag")
@CrossOrigin
@RestController
@RequestMapping("api/presentation/")
public interface MiahootPresentationEndpoint {

    @Operation(description = "Récupérer tous les DTO de toutes les entité Miahoot qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste de DTO d'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = Miahoot.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{uid}/miahoots")
    List<MiahootPresentation> getAllMiahootByUserUid(@PathVariable String uid);

    @Operation(description = "Récupérer le DTO de l'entité Miahoot Presentation qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = MiahootPresentation.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("miahoots/{miahootId}")
    MiahootPresentation getMiahootPresentationEntity(@PathVariable int miahootId);


    @Operation(description = "Création d'une entité Miahoot")
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("miahoots")
    void createMiahootPresentationEntity(@Valid @RequestBody CreateMiahootPresentationRequest request);


    @Operation(description = "Mise à jour d'une entité Miahoot")
    @ApiResponse(responseCode = "202", description = "L'entité à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("miahoots/{miahootId}")
    void updateMiahootEntity(@PathVariable final int miahootId, @RequestBody final MiahootPresentation miahoot);



    @Operation(description = "Suppression d'une entité Miahoot en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors 'Hello word' est renvoyé")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("miahoots/{miahootId}")
    void deleteMiahootEntity(@PathVariable int miahootId);

    @Operation(description = "Met fin a la présentation Presentation qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = MiahootPresentation.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("miahoots/{miahootId}/over")
    void closePresentation(@PathVariable int miahootId);

    @Operation(description = "Récupérer le DTO de l'entité Miahoot Presentation qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = QuestionPresentation.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée ou que toute les question sont passe",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{miahootId}/question")
    QuestionPresentation getQuestionPresentationEntity(@PathVariable int miahootId);

    @Operation(description = "Récupérer le DTO de l'entité Miahoot Presentation qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = QuestionPresentation.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée ou que toute les question sont passe",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("{miahootId}/question")
    void postQuestionPresentationEntity(@PathVariable int miahootId);

}
