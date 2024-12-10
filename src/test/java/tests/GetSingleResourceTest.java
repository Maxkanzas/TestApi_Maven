package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleResourceData;
import models.SingleResourceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetSingleResourceTest {
    private final ObjectMapper myObject = new ObjectMapper();

    @Test
    public void testGetSingleUser() throws Exception {
        String BASE_URL = "https://reqres.in/api/unknown";
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        SingleResourceResponse singleResourceResponse = myObject.readValue(response.asString(), SingleResourceResponse.class);

        SingleResourceData singleData = singleResourceResponse.getData();

        assertTrue(singleData.getColor().startsWith("#"), "Номер заливки " + singleData.getId() + " не оканчивается на #");
        assertNotNull(singleData.getName(), "Значение не должно быть null");
        assertFalse(singleData.getPantone_value().indexOf('!') != -1, "pantone_value не должно содержать символ '!'");
    }
}
