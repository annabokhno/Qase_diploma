package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class TestRunCreatePage {

    @Step("Установить название тест-рана: {title}")
    public TestRunCreatePage setTitle(String title){
        log.info("Setting test run title '{}'", title);
        $(TEST_RUN_TITLE).shouldBe(Condition.visible).setValue(title);
        return this;
    }

    @Step("Выбрать тест-кейсы для запуска")
    public TestRunCreatePage selectCases(){
        log.info("Selecting test cases");
        $(byText(SELECT_CASES)).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Запустить тест-ран")
    public void startRun(){
        log.info("Starting test run");
        $(byText(START_RUN)).shouldBe(Condition.visible).click();
    }
}
