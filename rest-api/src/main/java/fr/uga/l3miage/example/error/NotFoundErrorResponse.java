package fr.uga.l3miage.example.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;


@JsonTypeName(NotFoundErrorResponse.TYPE_NAME)
@Getter
@ToString(callSuper = true, exclude = "errorCodeSwaggerDocumentation")
@EqualsAndHashCode(callSuper = true)
public class NotFoundErrorResponse extends ErrorResponse{
    /**
     * Match {@link ErrorCode#IS_NOT_FOUND}
     */
    protected static final String TYPE_NAME = "IS_NOT_FOUND";


    @SuppressWarnings({ "java:S115", "java:S1170" }) // Use only to generate documentation
    @Schema(name = "errorCode", description = "Ce code d'erreur est aussi le discriminant pour le polymorphisme", allowableValues = TYPE_NAME,
            implementation = String.class, accessMode = Schema.AccessMode.READ_WRITE)
    @JsonProperty(access = WRITE_ONLY)
    private final String errorCodeSwaggerDocumentation = "Field used only to generate documentation, don't use it";

    @Schema(description = "id utilisé pour la recherche",example = "123121212")
    private final int reponseId;



    @Builder
    @Jacksonized
    public NotFoundErrorResponse(String uri, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage, int reponseId) {
        super(uri, httpStatus, errorCode, errorMessage);
        this.reponseId = reponseId;
    }
}
