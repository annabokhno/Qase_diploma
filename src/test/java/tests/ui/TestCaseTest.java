package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.ProjectPage;


public class TestCaseTest extends BaseTest {

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
        loginStep.auth(EMAIL, PASSWORD);
        projectStep.openProject("Automation Project");
        new ProjectPage()
                .openTestCases()
                .clickCreateCase();
    }
}
