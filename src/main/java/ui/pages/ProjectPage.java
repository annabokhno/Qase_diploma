package ui.pages;


import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectPage {

    private final SelenideElement TITLE = $("h1");

    @Step("Проверить, что проект открыт с кодом: {code}")
    public void checkOpened(String code) {
        log.info("Checking project opened with code: {}", code);
        TITLE.shouldHave(text(code));
    }

    @Step("Открыть настройки проекта")
    public ProjectSettingsPage openSettings() {
        log.info("Opening project settings");
        $(PROJECT_SETTINGS).click();
        return new ProjectSettingsPage();
    }

    @Step("Открыть раздел Test Cases")
    public TestCasePage openTestCases() {
        log.info("Opening Test Cases section");
        $(TEST_CASES).click();
        return new TestCasePage();
    }

    @Step("Открыть раздел Test Runs")
    public TestRunsPage openTestRuns() {
        log.info("Opening Test Runs section");
        $(TEST_RUNS)
                .shouldBe(Condition.visible)
                .click();
        return new TestRunsPage();
    }
}