package tests.ui;


import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.ProjectPage;

public class ProjectTest extends BaseTest {

    private final String PROJECT = "Automation Project";
    private final String CODE = "AUTO";

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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(PROJECT, CODE);
        projectStep.checkProjectCreated(PROJECT);
    }

    @Test(
            priority = 2,
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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(PROJECT, CODE);
        projectStep.editProject(PROJECT, "Updated Project");
        projectStep.checkUpdated("Updated Project");
    }

    @Test(
            priority = 3,
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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(PROJECT, CODE);
        projectStep.deleteProject(PROJECT);
        projectStep.checkDeleted(PROJECT);
    }

    @Test(
            priority = 4,
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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.search(PROJECT);
        projectStep.checkSearch(PROJECT);
    }

    @Test(
            priority = 5,
            description = "Открытие существующего проекта",
            testName = "Открытие проекта"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Projects")
    @Story("Open project")
    @Description("Проверка открытия существующего проекта")
    @Severity(SeverityLevel.NORMAL)
    public void openProject() {
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.openProject(PROJECT);
        projectStep.checkOpened();
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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.openProject(PROJECT);
        new ProjectPage()
                .openTestCases()
                .clickCreateCase();
    }
}