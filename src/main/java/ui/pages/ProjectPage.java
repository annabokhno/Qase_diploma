package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.TextElement;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectPage {

    private final TextElement title = new TextElement($("h1"));
    private final Button settingsButton = new Button($(PROJECT_SETTINGS));
    private final Button testCasesButton = new Button($(TEST_CASES));
    private final Button testRunsButton = new Button($(TEST_RUNS));

    @Step("Проверить, что проект открыт с кодом: {code}")
    public void checkOpened(String code) {
        log.info("Checking project opened with code: {}", code);
        title.shouldHaveText(code);
    }

    @Step("Открыть настройки проекта")
    public ProjectSettingsPage openSettings() {
        log.info("Opening project settings");
        settingsButton.click();
        return new ProjectSettingsPage();
    }

    @Step("Открыть раздел Test Cases")
    public TestCasePage openTestCases() {
        log.info("Opening Test Cases section");
        testCasesButton.click();
        return new TestCasePage();
    }

    @Step("Открыть раздел Test Runs")
    public TestRunsPage openTestRuns() {
        log.info("Opening Test Runs section");
        testRunsButton.click();
        return new TestRunsPage();
    }
}