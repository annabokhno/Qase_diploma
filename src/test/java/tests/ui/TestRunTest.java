package tests.ui;

import api.adapters.ProjectAdapter;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.dto.Project;
import ui.dto.ProjectFactory;
import ui.dto.TestRun;
import ui.dto.TestRunFactory;

public class TestRunTest extends BaseTest {

    private String projectCode;

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
        Project project = ProjectFactory.getProject();
        TestRun testRun = TestRunFactory.getTestRun();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectStep.openProject(project.getName());
        projectName = project.getName();
        projectCode = project.getCode();
        testRunStep.openTestRuns();
        testRunStep.createRun(testRun.getName());
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