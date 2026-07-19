package tests.api;

import api.adapters.CasesAdapter;
import api.adapters.ProjectAdapter;
import api.models.cases.CaseRq;
import api.models.cases.CaseRs;
import api.models.project.ProjectRq;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CaseAPITest {

    private final String PROJECT_CODE = "QA";
    private Integer caseId;

    @BeforeMethod
    @Step("Создание тестового проекта для API теста")
    public void setUp() {
        ProjectRq project = ProjectRq.builder()
                .title("API Project")
                .code(PROJECT_CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();
        try {
            ProjectAdapter.createProject(project);
        }
        catch (Exception ignored) {
        }
    }

    @Test(
            priority = 1,
            description = "Создание тест-кейса через API",
            testName = "Создание тест-кейса API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Test cases")
    @Story("Create test case")
    @Description("Проверка создания нового тест-кейса через API")
    @Severity(SeverityLevel.CRITICAL)
    public void createCaseAPITest() {
        CaseRq rq = getCaseRequest();
        CaseRs rs = CasesAdapter.createCase(PROJECT_CODE, rq);
        caseId = rs.getResult().getId();
        Assert.assertTrue(rs.getStatus());
        Assert.assertNotNull(caseId);
    }

    @Test(
            priority = 2,
            description = "Получение тест-кейса через API",
            testName = "Получение тест-кейса API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Test cases")
    @Story("Get test case")
    @Description("Проверка получения информации о существующем тест-кейсе через API")
    @Severity(SeverityLevel.NORMAL)
    public void getCaseAPITest() {
        CaseRq rq = getCaseRequest();
        caseId = CasesAdapter.createCase(PROJECT_CODE, rq).getResult().getId();
        CaseRs rs = CasesAdapter.getCase(PROJECT_CODE, caseId);
        Assert.assertTrue(rs.getStatus());
        Assert.assertEquals(rs.getResult().getTitle(), rq.getTitle());
        Assert.assertEquals(rs.getResult().getDescription(), rq.getDescription());
    }

    @Test(
            priority = 3,
            description = "Удаление тест-кейса через API",
            testName = "Удаление тест-кейса API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Test cases")
    @Story("Delete test case")
    @Description("Проверка удаления существующего тест-кейса через API")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteCaseAPITest() {
        CaseRq rq = getCaseRequest();
        caseId = CasesAdapter.createCase(PROJECT_CODE, rq).getResult().getId();
        CasesAdapter.deleteCase(PROJECT_CODE, caseId);
        caseId = null;
    }

    @AfterMethod
    @Step("Удаление созданных тестовых данных")
    public void tearDown() {
        if (caseId != null) {
            try {
                CasesAdapter.deleteCase(
                        PROJECT_CODE,
                        caseId);
            }
            catch (Exception ignored) {
            }
        }
        try {ProjectAdapter.deleteProject(
                    PROJECT_CODE);
        }
        catch (Exception ignored) {
        }
    }

    @Step("Подготовка данных тест-кейса")
    private CaseRq getCaseRequest() {
        return CaseRq.builder()
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
    }
}
