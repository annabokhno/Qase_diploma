package tests.ui;

import api.adapters.ProjectAdapter;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ui.dto.Project;
import ui.dto.ProjectFactory;
import ui.dto.TestCase;
import ui.dto.TestCaseFactory;
import ui.pages.ProjectPage;

public class TestCaseTest extends BaseTest {

    private String projectCode;

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
        Project project = ProjectFactory.getProject();
        TestCase testCase = TestCaseFactory.getTestCase();
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.createProject(project.getName(), project.getCode());
        projectStep.openProject(project.getName());
        projectName = project.getName();
        projectCode = project.getCode();
        new ProjectPage()
                .openTestCases()
                .clickCreateCase()
                .fillTitle(testCase.getTitle())
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