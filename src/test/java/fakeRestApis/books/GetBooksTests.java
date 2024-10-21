package fakeRestApis.books;

import api.dto.BookDTO;
import helpers.PropertiesManager;
import helpers.SerializationHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class GetBooksTests {

    @BeforeClass
    public void setup() {
        PropertiesManager.loadProperties();
        RestAssured.baseURI = PropertiesManager.getProperty("fakeRestApisBaseUrl");
    }

    @Test
    public void getBooks_expect_responseStatusCode200() {
        Response response = given()
                .when()
                    .get("/api/v1/Books")
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0))
                    .extract()
                    .response();
        }

    @Test
    public void getBook_expect_responseContainsBookDtoArray() {
        Response response = given()
                .when()
                    .get("/api/v1/Books")
                .then()
                    .extract()
                    .response();

        String jsonResponse = response.asString();
        BookDTO[] bookDTOs = SerializationHelper.deSerializeJsonToDto(jsonResponse, BookDTO[].class);
        assertTrue(bookDTOs.length > 0);
    }

}
