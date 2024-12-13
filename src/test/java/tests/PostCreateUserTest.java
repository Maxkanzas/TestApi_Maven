package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserRequestParameters;
import models.UserResponseParameters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostCreateUserTest {
    private final ObjectMapper myObject = new ObjectMapper();

    @Test
    public void testCreateUserName() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters("morpheus");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getId(), "ID не должен быть null");
        assertNotNull(userResponse.getCreatedAt(), "поле createdAt не должно быть пустым");

        assertEquals(user.getName(), userResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userResponse.getJob(), "Имя пользователя не совпадает");
    }

    @Test
    public void testCreateUserNameAndJob() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters("morpheus", "leader");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getId(), "ID не должен быть null");
        assertNotNull(userResponse.getCreatedAt(), "поле createdAt не должно быть пустым");
        assertNotNull(userResponse.getJob(), "поле job не должно быть пустым");

        assertEquals(user.getName(), userResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userResponse.getJob(), "Имя пользователя не совпадает");
    }
    @Test
    public void testCreateUserJob() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters(null, "leader");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getId(), "ID не должен быть null");
        assertNotNull(userResponse.getCreatedAt(), "поле createdAt не должно быть пустым");
        assertNotNull(userResponse.getJob(), "поле job не должно быть пустым");

        assertEquals(user.getName(), userResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userResponse.getJob(), "Имя пользователя не совпадает");
    }
}
