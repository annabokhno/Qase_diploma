package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import ui.pages.ProjectPage;
import org.testng.annotations.AfterMethod;
import utils.QaseApiClient;


public class ProjectTest extends BaseTest {

    private String projectCode;
    private String generateProjectName() {
        return "Automation Project " + System.currentTimeMillis();
    }
    private String generateCode() {
        return "A" + (System.currentTimeMillis() % 100000);
    }

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
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
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
        String project = generateProjectName();
        String code = generateCode();
        String updatedName = "Updated Project " + System.currentTimeMillis();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
        projectStep.editProject(project, updatedName);
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
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
        projectStep.deleteProject(project);
        projectStep.checkDeleted(project);
        projectCode =null;
        projectName = null;
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
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
        projectStep.search(project);
        projectStep.checkSearch(project);
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
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
        projectStep.openProject(project);
        projectStep.checkOpened(code);
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
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectName = project;
        projectCode = code;
        projectStep.openProject(project);
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
                QaseApiClient.deleteProject(projectCode);
            } catch (Exception e) {
                System.out.println("Не удалось удалить проект через API: " + projectCode);
                e.printStackTrace();
            } finally {
                projectCode = null;
                projectName = null;
            }
        }
    }
}