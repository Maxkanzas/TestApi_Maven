package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundTest {
    @Test
    public void testNotFoundUser() throws Exception {
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/9999")
                .then()
                .statusCode(404)
                .extract()
                .response();
        String responsebody = response.getBody().asString();
        assertEquals("{}", responsebody, "Тело ответа не является пустым");
    }
}
