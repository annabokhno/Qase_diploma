package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import ui.wrappers.TextArea;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class CreateProjectPage {

    private final Input projectNameInput = new Input($(PROJECT_NAME));
    private final Input projectCodeInput = new Input($(PROJECT_CODE));
    private final TextArea descriptionArea = new TextArea($(DESCRIPTION_AREA));

    @Step("Заполнить название проекта: {value}")
    public CreateProjectPage fillName(String value) {
        log.info("Filling project name: {}", value);
        projectNameInput.setValue(value);
        return this;
    }

    @Step("Заполнить код проекта: {value}")
    public CreateProjectPage fillCode(String value) {
        log.info("Filling project code: {}", value);
        projectCodeInput.setValue(value);
        return this;
    }

    @Step("Заполнить описание проекта")
    public CreateProjectPage fillDescription(String value) {
        log.info("Filling project description");
        descriptionArea.setValue(value);
        return this;
    }

    @Step("Нажать кнопку Create project")
    public void clickCreate() {
        log.info("Clicking Create Project button");
        Button createButton =
                new Button($(byText(CREATE_PROJECT)));
        createButton.click();
    }

    @Step("Создать проект: {projectName}")
    public void create(String projectName, String projectCode) {
        log.info("Creating project '{}' with code '{}'", projectName, projectCode);
        fillName(projectName);
        fillCode(projectCode);
        clickCreate();
    }
}