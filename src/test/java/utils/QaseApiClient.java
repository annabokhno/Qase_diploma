package utils;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;

public class QaseApiClient {

    private static final String BASE_URL = "https://api.qase.io/v1";
    private static final String TOKEN = PropertyReader.getProperty("QASE_API_TOKEN");
    public static void deleteProject(String projectCode) {
        RestAssured
                .given()
                .header("Token", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL + "/project/" + projectCode)
                .then()
                .statusCode(200);
    }

}

