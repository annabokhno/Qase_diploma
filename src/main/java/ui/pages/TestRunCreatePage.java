package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class TestRunCreatePage {

    private final Input testRunTitleInput = new Input($(TEST_RUN_TITLE));
    private final Button selectCasesButton = new Button($(byText(SELECT_CASES)));
    private final Button startRunButton = new Button($(byText(START_RUN)));

    @Step("Установить название тест-рана: {title}")
    public TestRunCreatePage setTitle(String title) {
        log.info("Setting test run title '{}'", title);
        testRunTitleInput.setValue(title);
        return this;
    }

    @Step("Выбрать тест-кейсы для запуска")
    public TestRunCreatePage selectCases() {
        log.info("Selecting test cases");
        selectCasesButton.click();
        return this;
    }

    @Step("Запустить тест-ран")
    public void startRun() {
        log.info("Starting test run");
        startRunButton.click();
    }
}