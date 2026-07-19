package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class TestRunsPage {

    @Step("Открыть страницу тест-ранов")
    public TestRunsPage openTestRuns() {
        log.info("Opening Test Runs page");
        $(TEST_RUNS).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажать кнопку создания нового тест-рана")
    public TestRunCreatePage clickStartNewRun() {
        log.info("Clicking Start New Test Run button");
        $(byText(START_NEW_TEST_RUN)).shouldBe(Condition.visible).click();
        return new TestRunCreatePage();
    }
}