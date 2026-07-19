package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.pages.ProjectPage;
import utils.QaseApiClient;

public class TestCaseTest extends BaseTest {

    private String projectCode;
    private String generateProjectName() {
        return "Automation Project " + System.currentTimeMillis();
    }
    private String generateCode() {
        return "A" + (System.currentTimeMillis() % 100000);
    }

    @Test(
            priority = 1,
            description = "Создание нового тест-кейса",
            testName = "Создание тест-кейса",
            groups = {"smoke"}
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Test cases")
    @Story("Create test case")
    @Description("Проверка создания нового тест-кейса")
    @Severity(SeverityLevel.CRITICAL)
    public void createTestCase() {
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectStep.openProject(project);
        projectName = project;
        projectCode = code;
        new ProjectPage()
                .openTestCases()
                .clickCreateCase();
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