package tests.api;

import api.adapters.BaseAdapter;
import api.adapters.ProjectAdapter;
import api.models.project.ProjectRq;
import api.models.project.ProjectRs;
import api.models.project.ProjectsRs;
import org.testng.Assert;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProjectAPITest {

    private final String CODE = "QA";

    @Test(
            priority = 1,
            description = "Создание проекта через API",
            testName = "Создание проекта API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Create project")
    @Description("Проверка создания нового проекта через API")
    @Severity(SeverityLevel.CRITICAL)
    public void createProjectAPITest() {
        ProjectRq rq = ProjectRq.builder()
                .title("QA34")
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();
        ProjectRs rs = ProjectAdapter.createProject(rq);
        Assert.assertTrue(rs.getStatus());
        Assert.assertEquals(rs.getResult().getCode(), CODE);
    }

    @Test(
            priority = 2,
            description = "Получение списка проектов через API",
            testName = "Получение списка проектов API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Get projects")
    @Description("Проверка получения списка существующих проектов через API")
    @Severity(SeverityLevel.NORMAL)
    public void getProjectsAPITest() {
        ProjectRq rq = ProjectRq.builder()
                .title("QA34")
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();
        ProjectAdapter.createProject(rq);
        ProjectsRs rs = ProjectAdapter.getProjects();
        Assert.assertTrue(rs.getStatus());
        Assert.assertFalse(rs.getResult().getEntities().isEmpty());
    }

    @Test(
            priority = 3,
            description = "Получение проекта по коду через API",
            testName = "Получение проекта по коду API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Get project by code")
    @Description("Проверка получения информации о проекте по уникальному коду")
    @Severity(SeverityLevel.NORMAL)
    public void getProjectByCodeAPITest() {
        ProjectRq rq = ProjectRq.builder()
                .title("QA34")
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();
        ProjectAdapter.createProject(rq);
        ProjectRs rs = ProjectAdapter.getProject(CODE);
        Assert.assertTrue(rs.getStatus());
        Assert.assertEquals(rs.getResult().getCode(), CODE);
    }

    @Test(
            priority = 4,
            description = "Удаление проекта через API",
            testName = "Удаление проекта API"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase API")
    @Feature("Projects")
    @Story("Delete project")
    @Description("Проверка удаления существующего проекта через API")
    public void deleteProjectAPITest() {
        ProjectRq rq = ProjectRq.builder()
                .title("QA34")
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();
        ProjectAdapter.createProject(rq);
        given()
                .spec(BaseAdapter.spec)
                .pathParam("code", CODE)
                .delete("/project/{code}")
                .then()
                .statusCode(200);
    }

    @AfterMethod
    @Step("Удаление созданного проекта")
    public void tearDown() {
        try {
            ProjectAdapter.deleteProject(CODE);
        } catch (Throwable ignored) {
        }
    }
}
