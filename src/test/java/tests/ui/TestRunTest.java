package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.QaseApiClient;

public class TestRunTest extends BaseTest {

    private String projectCode;
    private String generateProjectName() {
        return "Automation Project " + System.currentTimeMillis();
    }
    private String generateCode() {
        return "A" + (System.currentTimeMillis() % 100000);
    }

    @Test(
            priority = 1,
            description = "Создание нового тестового запуска",
            testName = "Создание Test Run",
            groups = {"smoke"}
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Test Runs")
    @Story("Create test run")
    @Description("Проверка создания нового Test Run")
    @Severity(SeverityLevel.CRITICAL)
    public void createTestRun() {
        String project = generateProjectName();
        String code = generateCode();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project, code);
        projectStep.openProject(project);
        projectName = project;
        projectCode = code;
        testRunStep.openTestRuns();
        testRunStep.createRun("Regression run " + System.currentTimeMillis());
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