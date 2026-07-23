package tests.ui;

import api.adapters.ProjectAdapter;
import ui.dto.Project;
import ui.dto.ProjectFactory;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.pages.ProjectPage;

public class ProjectTest extends BaseTest {

    private String projectCode;

    @Test(
            priority = 1,
            description = "Создание нового проекта",
            testName = "Создание проекта",
            groups = {"smoke"}
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Create project")
    @Description("Проверка создания нового проекта")
    @Severity(SeverityLevel.CRITICAL)
    public void createProject() {
        Project project = ProjectFactory.getProject();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
    }

    @Test(
            priority = 2,
            description = "Открытие существующего проекта",
            testName = "Открытие проекта"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Open project")
    @Description("Проверка открытия проекта")
    @Severity(SeverityLevel.NORMAL)
    public void openProject() {
        Project project = ProjectFactory.getProject();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
        projectStep.openProject(project.getName());
        projectStep.checkOpened(project.getCode());
    }

    @Test(
            priority = 3,
            description = "Редактирование информации о проекте",
            testName = "Редактирование проекта"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Edit project")
    @Description("Проверка редактирования информации о существующем проекте")
    @Severity(SeverityLevel.NORMAL)
    public void editProject() {
        Project project = ProjectFactory.getProject();
        String updatedName = "Updated " + ProjectFactory.getProject().getName();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
        projectStep.editProject(project.getName(), updatedName);
        projectStep.checkUpdated(updatedName);
        projectName = updatedName;
    }

    @Test(
            priority = 4,
            description = "Удаление проекта",
            testName = "Удаление проекта"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Delete project")
    @Description("Проверка удаления существующего проекта")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteProject() {
        Project project = ProjectFactory.getProject();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
        projectStep.deleteProject(project.getName());
        projectStep.checkDeleted(project.getName());
        projectName = null;
        projectCode = null;
    }

    @Test(
            priority = 5,
            description = "Поиск проекта",
            testName = "Поиск проекта"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Search project")
    @Description("Проверка поиска проекта по названию")
    @Severity(SeverityLevel.NORMAL)
    public void searchProject() {
        Project project = ProjectFactory.getProject();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
        projectStep.search(project.getName());
        projectStep.checkSearch(project.getName());
    }

    @Test(
            priority = 6,
            description = "Создание тест-кейса",
            testName = "Создание тест-кейса"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Test cases")
    @Story("Create test case")
    @Description("Проверка перехода к созданию нового тест-кейса")
    @Severity(SeverityLevel.CRITICAL)
    public void createTestCase() {
        Project project = ProjectFactory.getProject();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectName = project.getName();
        projectCode = project.getCode();
        projectStep.openProject(project.getName());
        new ProjectPage()
                .openTestCases()
                .clickCreateCase()
                .fillTitle("Authorization test")
                .save();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteCreatedProject() {
        if (projectCode != null) {
            try {
                ProjectAdapter.deleteProject(projectCode);
            } catch (Exception ignored) {
            } finally {
                projectCode = null;
                projectName = null;
            }
        }
    }
}