package fr.uga.l3miage.example.config.handler;

import fr.uga.l3miage.example.config.handler.testHandler.TestNotFoundExceptionHandler;
import fr.uga.l3miage.example.error.ErrorResponse;
import fr.uga.l3miage.example.error.testError.TestNotFoundErrorResponse;
import fr.uga.l3miage.example.exception.rest.restTestException.TestEntityNotFoundRestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Permet de tester le handler de l'exception d'API {@link TestEntityNotFoundRestException}
 */
class TestNotFoundExceptionHandlerTest {
    @Test
    void testHandle() {
        TestNotFoundExceptionHandler handler = new TestNotFoundExceptionHandler();
        TestEntityNotFoundRestException exception = new TestEntityNotFoundRestException("TestConfigWithProperties not found","description");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("TestConfigWithProperties");
        ResponseEntity<TestNotFoundErrorResponse> expected = ResponseEntity.status(exception.getHttpStatus())
                .body(TestNotFoundErrorResponse.builder()
                        .errorMessage(exception.getMessage())
                        .uri(request.getRequestURI())
                        .errorCode(exception.getErrorCode())
                        .httpStatus(exception.getHttpStatus())
                        .description("description")
                        .build());

        ResponseEntity<ErrorResponse> response = handler.handle(request, exception);
        assertThat(response).usingRecursiveComparison().isEqualTo(expected);
    }
}
