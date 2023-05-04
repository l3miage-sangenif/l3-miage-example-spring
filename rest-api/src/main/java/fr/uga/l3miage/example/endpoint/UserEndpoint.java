package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.NotFoundErrorResponse;
import fr.uga.l3miage.example.error.testError.DescriptionAlreadyUseErrorResponse;
import fr.uga.l3miage.example.error.testError.IsNotTestErrorResponse;
import fr.uga.l3miage.example.error.testError.TestEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.testError.TestIntIsZeroErrorResponse;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateUserRequest;
import fr.uga.l3miage.example.response.Miahoot;
import fr.uga.l3miage.example.response.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


/**
 * <p>Cette interface correspond à la définition du controller <b>REST</b>, elle vous permet de vous assurer
 * que votre controller respecte ce contrat d'interface. Mais aussi si un jour vous voulez partager votre API,
 * alors vous n'avez qu'à partager cette interface.</p>
 * <u>Les annotations :</u>
 * <ul>
 *  <li>{@link Tag} correspond au nom de la section de tous les endpoints dans le <b>swagger</b><br>
 *  Exemple :<br>
 *  <img src="../doc/pictures/tagSwagger.png" alt="Swagger tag"/><br>
 *  La <a href="https://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/tags/Tag.html">doc</a> !</li></li>
 *  <li>{@link CrossOrigin} Permet d'avoir plusieurs points d'accès différents. <br>
 *  La <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CrossOrigin.html">doc</a> !</li>
 *  <li>{@link RestController} permet d'indiquer que votre controller est de type <b>REST</b>.<br>
 *  La <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html">doc</a> !</li>
 *  <li>{@link RequestMapping} permet de dire que tous les endpoints de la classe commencent par <b>'exemple/'</b><br>
 *  La <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">doc</a> !</li>
 * </ul>
 */
@Tag(name = "user tag")
@CrossOrigin
@RestController
@RequestMapping("api/users")
public interface UserEndpoint {

    /**
     * Ici on définit un endpoint en mode <b>POST</b> pour créer une entité Test<br>
     *
     * @param request correspond à la requête à effectuer avec les informations utiles pour la création d'une entité Test
     * @return En cas <b color="red">d'erreur</b>:<br>
     * <ul>
     *     <li>{@link IsNotTestErrorResponse} si le champ isTest est égal  à false</li>
     *     <li>{@link TestIntIsZeroErrorResponse} si la somme des 2 entiers de la requête est égale à 0</li>
     *     <li>{@link DescriptionAlreadyUseErrorResponse} si la description existe déjà en BD</li>
     * </ul>
     *
     * Les annotations :
     * <ul>
     *     <li>{@link Operation} permet de créer une description de l'opération de ce endpoint dans le swagger<br>
     *     La <a href="https://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Operation.html">doc</a> !</li>
     *     <li>{@link ApiResponse} permet de documenter les réponses prévues par l'API</li>
     *     <li>{@link Error400Custom} Annotation custom créée afin de faire un raccourci d'annotations</li>
     *     <li>{@link ResponseStatus} permet de renvoyer le statut http donné si l'appel s'est bien passé<br>Ici on renverra un statut 201 si tout s'est bien passé !<br>
     *     La <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html">doc</a> !
     *     <li>{@link PostMapping} permet de spécifier dans quelle méthode http ce endpoint doit être utilisé<br>Ici ce endpoint est en mode <b>POST</b><br>
     *     La <a href="">doc</a> !</li>
     *     </li>
     *     <li>{@link RequestBody} Permet de dire à spring de trouver le paramètre request dans les corps de la requête REST <br>
     *     La <a href="">doc</a> !</li>
     *     <li>{@link Valid} si vous rajoutez un validateur elle permet de vérifier que tous les champs sont conformes à vos attentes (NonNull, NotBlank, etc...)</li>
     * </ul>
     *
     */

    @Operation(description = "Création d'une entité User")
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createUser(@Valid @RequestBody CreateUserRequest request);


    /**
     * @param uid la description de l'entité en BD à mettre à jour
     * @param user le <b color="yellow"> DTO</b> de l'entité qui va permettre la modification
     * @throws EntityNotFoundException si l'entité avec une lastDescription n'est pas trouvée en BD
     */
    @Operation(description = "Mise à jour d'une entité User")
    @ApiResponse(responseCode = "202", description = "L'entité à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{uid}")
    void updateUser(@PathVariable final String uid, @RequestBody final User user) throws EntityNotFoundException;


    /**
     * Ici on définit un endpoint en mode <b>DELETE</b> pour supprimer une entité User<br>
     *
     * @param uid correspond à l'uid(l'indentificateur unique) de l'entité User à supprimer
     * @return En cas <b color="red">d'erreur</b>:<br>
     * <ul>
     *     <li>{@link TestEntityNotDeletedErrorResponse} peut arriver pour 2 raisons :
     *     <ul>
     *         <li>si aucune entité Test n'est trouvé en fonction de la description</li>
     *         <li>si plusieurs entités ont la même description, ce n'est pas censé arriver !</li>
     *     </ul>
     *     </li>
     * </ul>
     * Les annotations :
     * <ul>
     *     <li>{@link Operation} permet de créer une description de l'opération de ce endpoint dans le swagger<br>
     *     La <a href="https://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/Operation.html">doc</a> !</li>
     *     <li>{@link ApiResponse} permet de documenter les réponses prévues par l'API<br>
     *     Les annotations internes :
     *     <ul>
     *         <li>{@link Content} permet de préciser le contenu d'une {@link ApiResponse}<br>La <a href="https://docs.swagger.io/swagger-core/v2.0.8/apidocs/io/swagger/v3/oas/annotations/media/Content.html">doc</a> !</li>
     *         <li>{@link Schema} permet de préciser la classe qui sera renvoyée dans  le content de l'{@link ApiResponse}<br>La <a href="https://docs.swagger.io/swagger-core/v2.0.0-RC3/apidocs/io/swagger/v3/oas/annotations/media/Schema.html">doc</a> !</li>
     *     </ul>
     *     </li>
     *     <li>{@link ResponseStatus} permet de renvoyer le statut http donné si l'appel s'est bien passé<br>Ici on renverra un statut 202 si tout s'est bien passé !<br>
     *     La <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html">doc</a> !
     *     <li>{@link DeleteMapping} permet de spécifier dans quelle méthode http ce endpoint doit être utilisé<br>Ici ce endpoint est en mode <b>DELETE</b><br>
     *     La <a href="">doc</a> !</li>
     *     <li>{@link PathVariable} permet de dire à spring où trouver le paramètre de la fonction<br>Ici dans le path de l'endpoint, donc "exemple/<b color="blue">{description}</b>"</li>
     *      La <a href="">doc</a> !</li>
     * </ul>
     *
     */
    @Operation(description = "Suppression d'une entité User en bd")
    @ApiResponse(responseCode = "200", description = "Renvoie 200 si l'utilisateur et tous ses miahoot sont supprimé")
    @ApiResponse(responseCode = "418", description = "Renvoie une erreur 418 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = TestEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uid}")
    void deleteUser(@PathVariable String uid) ;

}
