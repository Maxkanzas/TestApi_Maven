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

public class PutUpdateUserTest {
    private final ObjectMapper myObject = new ObjectMapper();

    @Test
    public void testUpdatedUserName() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters("morpheus");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.asString());
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getName(), "Name не должен быть null");
        assertNotNull(userResponse.getUpdatedAt(), "updateAt не должно быть пустым");

        assertEquals(user.getName(), userResponse.getName(), "Имя пользователя не совпадает");
    }
    @Test
    public void testUpdatedUserNameAndJob() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters("morpheus", "zion resident");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.asString());
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getName(), "Name не должен быть null");
        assertNotNull(userResponse.getUpdatedAt(), "updateAt не должно быть пустым");
        assertNotNull(userResponse.getJob(), "updateAt не должно быть пустым");

        assertEquals(user.getName(), userResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userResponse.getJob(), "Статус жителя города не совпадает");
    }
    @Test
    public void testUpdatedUserJob() throws JsonProcessingException{
        UserRequestParameters user = new UserRequestParameters(null, "zion resident");
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.asString());
        UserResponseParameters userResponse = myObject.readValue(response.asString(), UserResponseParameters.class);

        assertNotNull(userResponse.getJob(), "Job не должен быть null");
        assertNotNull(userResponse.getUpdatedAt(), "updateAt не должно быть пустым");

        assertEquals(user.getJob(), userResponse.getJob(), "Статус жителя города не совпадает");
    }
}
