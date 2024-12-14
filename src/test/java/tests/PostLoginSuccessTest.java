package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.RegisterRequest;
import models.RegisterResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PostLoginSuccessTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest
    @CsvSource({
            "'eve.holt@reqres.in', 'cityslicka', 'JAPAN', true",
            "'lindsay.ferguson@reqres.in', 'cityslicka', 'USA', true",
            "'lindsay.ferguson@reqres.in', 'cityslicka', 'RUSSIA', true",
            "'tobias.funke@reqres.in', 'cityslicka', 'Canada', true",
            "'george.edwards@reqres.in', '', 'China', false",
            "'george.edwards@reqres.in', '', '', false",
    })
    public void testRegisterSuccess(String email, String password, String state, boolean isSuccess) throws JsonProcessingException {
        String BASE_URL = "https://reqres.in/api/login";

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        registerRequest.setState(state);

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(registerRequest)
                .when()
                .post(BASE_URL)
                .then()
                .extract()
                .response();

        if (isSuccess) {
            assertEquals(200, response.getStatusCode(), "Статус код не соответствует 200");
            RegisterResponse registerResponse = objectMapper.readValue(response.asString(), RegisterResponse.class);
            assertNotNull(registerResponse.getToken(), "Token не должен быть null");
        } else {
            assertEquals(400, response.getStatusCode(), "Статус код не соответствует 400");
            RegisterResponse registerResponse = objectMapper.readValue(response.asString(), RegisterResponse.class);
            String errorMessage = registerResponse.getError();
            assertNotNull(errorMessage, "Сообщение об ошибке не должно быть null");
            assertEquals("Missing password", errorMessage, "Сообщение об ошибке не совпадает");
        }
    }
}
