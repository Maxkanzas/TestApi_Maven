package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleUserResponse;
import models.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetSingleUserTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetSingleUser() throws Exception {
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        SingleUserResponse singleUserResponse = objectMapper.readValue(response.asString(), SingleUserResponse.class);

        UserData user = singleUserResponse.getData();

        assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя " + user.getId() + " не оканчивается на @reqres.in");
    }
}
