package tests.api;

import api.adapters.BaseAdapter;
import api.adapters.CasesAdapter;
import api.adapters.ProjectAdapter;
import api.models.cases.CaseRq;
import api.models.project.ProjectRq;
import api.models.result.ResultRq;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResultAPITest {

    private final String PROJECT = "QA";
    private Integer caseId;

    @BeforeMethod
    @Step("Создание тестового проекта и тест-кейса для проверки результатов")
    public void setUp() {
        ProjectRq project = ProjectRq.builder()
                .title("API Project")
                .code(PROJECT)
                .description("test")
                .access("all")
                .group("test")
                .build();
        try {
            ProjectAdapter.createProject(project);
        } catch (Exception ignored) {
        }
        CaseRq caseRq = CaseRq.builder()
                .title("API Case")
                .description("Description")
                .preconditions("Preconditions")
                .postconditions("Postconditions")
                .steps_type("classic")
                .severity(1)
                .priority(1)
                .behavior(1)
                .type(1)
                .layer(1)
                .is_flaky(0)
                .build();
        caseId = CasesAdapter.createCase(PROJECT, caseRq).getResult().getId();
    }

    @Test(
            priority = 1,
            description = "Создание результата тест-кейса через API",
            testName = "Создание результата тест-кейса API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Results")
    @Story("Create result")
    @Description("Проверка отправки результата выполнения тест-кейса через API")
    @Severity(SeverityLevel.NORMAL)
    public void createResultAPITest() {
        ResultRq rq = ResultRq.builder()
                .case_id(1)
                .status("passed")
                .build();
        given()
                .spec(BaseAdapter.spec)
                .pathParam("code", PROJECT)
                .body(rq)
                .when()
                .post("/result/{code}")
                .then()
                .statusCode(405);
    }

    @AfterMethod
    @Step("Удаление созданных тестовых данных")
    public void tearDown() {
        if (caseId != null) {
            try {
                CasesAdapter.deleteCase(PROJECT, caseId);
            } catch (Exception ignored) {
            }
        }
        try {
            ProjectAdapter.deleteProject(PROJECT);
        } catch (Exception ignored) {
        }
    }
}