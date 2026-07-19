package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class TestCasePage {

    @Step("Нажать кнопку создания тест-кейса")
    public TestCasePage clickCreateCase() {
        log.info("Clicking Create Case button");
        $(byText(CREATE_CASE)).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Заполнить название тест-кейса: {value}")
    public TestCasePage fillTitle(String value) {
        log.info("Filling test case title '{}'", value);
        $(TEST_CASE_TITLE).shouldBe(Condition.visible).setValue(value);
        return this;
    }

    @Step("Сохранить тест-кейс")
    public void save() {
        log.info("Saving test case");
        $(byText(SAVE)).shouldBe(Condition.visible).click();
    }
}