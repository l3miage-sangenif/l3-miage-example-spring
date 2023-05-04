package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.EntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.Miahoot;
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


@Tag(name = "Miahoot tag")
@CrossOrigin
@RestController
@RequestMapping("api/")
public interface MiahootEndpoint {


    @Operation(description = "Récupérer tous les DTO de toutes les entité Miahoot qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoieune liste de DTO d'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = Miahoot.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{uid}/miahoots")
    List<Miahoot> getAllMiahootByUserUid(@PathVariable String uid);


    @Operation(description = "Récupérer le DTO de l'entité Miahoot qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité Miahoot demandée",
            content = @Content(schema = @Schema(implementation = Miahoot.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("miahoots/{miahootId}")
    Miahoot getMiahootEntity(@PathVariable int miahootId);



    @Operation(description = "Création d'une entité Miahoot")
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("miahoots")
    void createMiahootEntity(@Valid @RequestBody CreateMiahootRequest request);


    @Operation(description = "Mise à jour d'une entité Miahoot")
    @ApiResponse(responseCode = "202", description = "L'entité à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("miahoots/{miahootId}")
    void updateMiahootEntity(@PathVariable final int miahootId, @RequestBody final Miahoot miahoot);



    @Operation(description = "Suppression d'une entité Miahoot en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors 'Hello word' est renvoyé")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("miahoots/{miahootId}")
    void deleteMiahootEntity(@PathVariable int miahootId);
}
