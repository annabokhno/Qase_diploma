package tests.api;

import api.adapters.DefectsAdapter;
import api.adapters.ProjectAdapter;
import api.models.defects.DefectRq;
import api.models.defects.DefectRs;
import api.models.project.ProjectRq;
import org.testng.Assert;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DefectsAPITest {

    private final String PROJECT = "QA";
    private Integer defectId;

    @BeforeMethod
    @Step("Создание тестового проекта для API теста")
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
        }
        catch (Exception ignored) {
        }
    }

    @Test(
            priority = 1,
            description = "Создание дефекта через API",
            testName = "Создание дефекта API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Defects")
    @Story("Create defect")
    @Description("Проверка создания нового дефекта через API")
    @Severity(SeverityLevel.CRITICAL)
    public void createDefectAPITest() {
        DefectRq rq = defectRequest();
        DefectRs rs = DefectsAdapter.createDefect(PROJECT, rq);
        defectId = rs.getResult().getId();
        Assert.assertTrue(rs.getStatus());
        Assert.assertNotNull(defectId);
    }

    @Test(
            priority = 2,
            description = "Получение дефекта через API",
            testName = "Получение дефекта API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Defects")
    @Story("Get defect")
    @Description("Проверка получения информации о существующем дефекте через API")
    @Severity(SeverityLevel.NORMAL)
    public void getDefectAPITest() {
        defectId = DefectsAdapter.createDefect(PROJECT, defectRequest()).getResult().getId();
        DefectRs rs = DefectsAdapter.getDefect(PROJECT, defectId);
        Assert.assertTrue(rs.getStatus());
        Assert.assertEquals(rs.getResult().getTitle(), "API defect");
        Assert.assertEquals(rs.getResult().getActual_result(), "Actual result");
    }

    @Test(
            priority = 3,
            description = "Удаление дефекта через API",
            testName = "Удаление дефекта API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Defects")
    @Story("Delete defect")
    @Description("Проверка удаления существующего дефекта через API")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteDefectAPITest() {
        defectId = DefectsAdapter.createDefect(PROJECT, defectRequest()).getResult().getId();
        DefectsAdapter.deleteDefect(PROJECT, defectId);
        defectId = null;
    }

    @AfterMethod
    @Step("Удаление созданных тестовых данных")
    public void tearDown() {
        if (defectId != null) {
            try {
                DefectsAdapter.deleteDefect(PROJECT, defectId);
            }
            catch (Exception ignored) {
            }
        }
        try {
            ProjectAdapter.deleteProject(
                    PROJECT
            );
        }
        catch (Exception ignored) {
        }
    }

    @Step("Подготовка данных дефекта")
    private DefectRq defectRequest() {
        return DefectRq.builder()
                .title("API defect")
                .actual_result("Actual result")
                .severity(1)
                .build();
    }
}
