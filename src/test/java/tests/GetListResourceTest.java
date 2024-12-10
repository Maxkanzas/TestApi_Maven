package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetListResourceTest {
    private final ObjectMapper myObject = new ObjectMapper();

    @Test
    public void testGetListResource() throws Exception {
        String BASE_URL = "https://reqres.in/api/unknown";
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();
        ResourceResponse resourceResponse = myObject.readValue(response.asString(), ResourceResponse.class);

        assertEquals(6, resourceResponse.getData().size(), "Количество пользователей не совпадает");
        assertEquals(1, resourceResponse.getPage(), "Неверная страница");
        assertEquals(2, resourceResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(12, resourceResponse.getTotal(), "Неверное количество страниц");
        assertEquals(6, resourceResponse.getPer_page(), "Неверная per-страница");

        for (ResourceData resource : resourceResponse.getData()) {
            assertTrue(resource.getColor().startsWith("#"), "Код заливки " + resource.getId() + " начинается не с #");
            assertNotNull(resource, "Список не должен быть null"); // Проверка на null
            assertFalse(resource.getName().isEmpty(), "Цвет заливки " + resource.getId() + " не должен быть пустым"); // Проверка на пустоту поля name
            assertTrue(resource.getId() > 0, "Идентификатор заливки " + resource.getId() + " должен быть положительным значением");
            assertTrue(resource.getPantone_value().length() <= 7, "Pantone_value " + resource.getId() + " не подходит по формату");
        }
    }
}
