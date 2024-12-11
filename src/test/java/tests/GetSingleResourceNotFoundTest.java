package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSingleResourceNotFoundTest {
        @Test
        public void testSingleResourceNotFound() throws Exception {
            String BASE_URL = "https://reqres.in/api/unknown";
            Response response = RestAssured
                    .given()
                    .when()
                    .get(BASE_URL + "/23")
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();
            String responsebody = response.getBody().asString();
            assertEquals("{}", responsebody, "Тело ответа не является пустым");
        }
}