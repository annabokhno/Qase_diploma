package tests.api;

import api.adapters.BaseAdapter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;

public class LoginAPITest {

    @Test(
            priority = 1,
            description = "Проверка доступа к API с валидным токеном",
            testName = "Авторизация с валидным токеном"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Authentication")
    @Story("Valid token authorization")
    @Description("Проверка успешного выполнения API запроса с корректным токеном авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidToken() {
        given()
                .spec(BaseAdapter.spec)
                .when()
                .get("/project")
                .then()
                .statusCode(200);
    }

    @Test(
            priority = 2,
            description = "Проверка доступа к API без токена",
            testName = "Авторизация без токена"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Authentication")
    @Story("Invalid token authorization")
    @Description("Проверка невозможности выполнения API запроса без токена авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithoutToken() {
        given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri("https://api.qase.io")
                        .setBasePath("/v1")
                        .setContentType(ContentType.JSON)
                        .build())
                .when()
                .get("/project")
                .then()
                .statusCode(401);
    }
}
