package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserTest {
    @Test
    public void testUpdatedUserName() throws JsonProcessingException{
        String BASE_URL = "https://reqres.in/api/users";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL + "/2")
                .then()
                .statusCode(204)
                .extract()
                .response();
        System.out.println(response.asString());
        assertEquals("", response.asString(), "Ответ не должен содержать тело");
    }
}
