package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class TestRunsPage {

    private final Button testRunsButton = new Button($(TEST_RUNS));
    private final Button startNewTestRunButton = new Button($(byText(START_NEW_TEST_RUN)));

    @Step("Открыть страницу тест-ранов")
    public TestRunsPage openTestRuns() {
        log.info("Opening Test Runs page");
        testRunsButton.click();
        return this;
    }

    @Step("Нажать кнопку создания нового тест-рана")
    public TestRunCreatePage clickStartNewRun() {
        log.info("Clicking Start New Test Run button");
        startNewTestRunButton.click();
        return new TestRunCreatePage();
    }
}