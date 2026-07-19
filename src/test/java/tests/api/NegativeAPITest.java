package tests.api;

import api.adapters.BaseAdapter;
import api.models.project.ProjectRq;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;

public class NegativeAPITest {

    @Test(
            priority = 1,
            description = "Создание проекта без обязательного поля",
            testName = "Создание проекта без названия"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Negative create project")
    @Description("Проверка невозможности создания проекта без обязательного поля title")
    @Severity(SeverityLevel.NORMAL)
    public void createProjectWithoutTitle() {
        ProjectRq rq = ProjectRq.builder()
                .code("QA")
                .description("test")
                .access("all")
                .group("test")
                .build();
        given()
                .spec(BaseAdapter.spec)
                .body(rq)
                .when()
                .post("/project")
                .then()
                .statusCode(400);
    }

    @Test(
            priority = 2,
            description = "Получение проекта с несуществующим кодом",
            testName = "Поиск проекта с неверным кодом"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Negative get project")
    @Description("Проверка получения ошибки при запросе проекта с некорректным кодом")
    @Severity(SeverityLevel.NORMAL)
    public void getProjectWithIncorrectCode() {
        given()
                .spec(BaseAdapter.spec)
                .pathParam("code", "UNKNOWN123")
                .when()
                .get("/project/{code}")
                .then()
                .statusCode(404);
    }
}
