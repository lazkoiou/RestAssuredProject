package fakeRestApis.books;

import api.dto.BookDTO;
import helpers.PropertiesManager;
import helpers.SerializationHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class GetBooksByIdTests {

    private final BookDTO createdBookDTO = BookDTO.getDefaultWithRandomId();

    @BeforeClass
    public void setup() {
        PropertiesManager.loadProperties();
        RestAssured.baseURI = PropertiesManager.getProperty("fakeRestApisBaseUrl");
        // Create a new book entry
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(createdBookDTO))
            .when()
                .post("/api/v1/Books")
            .then()
                .statusCode(200);
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        given()
                .when()
                    .delete("/api/v1/Books/" + createdBookDTO.getId())
                .then()
                    .statusCode(200);
    }

    @Test // BUG - fails as new books are do not correctly added in the list
    public void getBooksById_existingId_expect_responseStatusCode200Ok() {
        Response response = given()
                .when()
                    .get("/api/v1/Books/" + createdBookDTO.getId())
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0))
                    .extract()
                    .response();
        String jsonResponse = response.asString();
        BookDTO bookDTO = SerializationHelper.deSerializeJsonToDto(jsonResponse, BookDTO.class);
        assertEquals(createdBookDTO.getTitle(), bookDTO.getTitle());
        assertEquals(createdBookDTO.getDescription(), bookDTO.getDescription());
        assertEquals(createdBookDTO.getPageCount(), bookDTO.getPageCount());
        assertEquals(createdBookDTO.getExcerpt(), bookDTO.getExcerpt());
        assertEquals(createdBookDTO.getDateTime(), bookDTO.getDateTime());
    }

    @Test
    public void getBooksById_nonExistingId_expect_responseStatusCode404NotFound() {
        given()
                .when()
                    .get("/api/v1/Books/" + createdBookDTO.getId() + "1")
                .then()
                    .statusCode(404);
    }

    @Test
    public void getBooksById_invalidId_expect_responseStatusCode400BadRequest() {
        given()
                .when()
                .get("/api/v1/Books/" + "aaa")
                .then()
                .statusCode(400);
    }

}
