package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class CreateProjectPage {

    @Step("Заполнить название проекта: {value}")
    public void fillName(String value) {
        log.info("Filling project name: {}", value);
        $(PROJECT_NAME).setValue(value);
    }

    @Step("Заполнить код проекта: {value}")
    public void fillCode(String value) {
        log.info("Filling project code: {}", value);
        $(PROJECT_CODE).setValue(value);
    }

    @Step("Заполнить описание проекта")
    public CreateProjectPage fillDescription(String value) {
        log.info("Filling project description");
        $(DESCRIPTION_AREA).setValue(value);
        return this;
    }

    @Step("Нажать кнопку Create project")
    public void clickCreate() {
        log.info("Clicking Create Project button");
        $(byText(CREATE_PROJECT))
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Создать проект: {projectName}")
    public void create(String projectName, String projectCode) {
        log.info("Creating project '{}' with code '{}'", projectName, projectCode);
        fillName(projectName);
        fillCode(projectCode);
        clickCreate();
    }
}