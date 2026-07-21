package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class TestCasePage {

    private final Input testCaseTitleInput = new Input($(TEST_CASE_TITLE));
    private final Button createCaseButton = new Button($(byText(CREATE_CASE)));
    private final Button saveButton = new Button($(byText(SAVE)));

    @Step("Нажать кнопку создания тест-кейса")
    public TestCasePage clickCreateCase() {
        log.info("Clicking Create Case button");
        createCaseButton.click();
        return this;
    }

    @Step("Заполнить название тест-кейса: {value}")
    public TestCasePage fillTitle(String value) {
        log.info("Filling test case title '{}'", value);
        testCaseTitleInput.setValue(value);
        return this;
    }

    @Step("Сохранить тест-кейс")
    public void save() {
        log.info("Saving test case");
        saveButton.click();
    }
}