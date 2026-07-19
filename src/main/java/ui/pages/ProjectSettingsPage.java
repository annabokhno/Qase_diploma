package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import ui.wrappers.TextElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectSettingsPage {

    private final Input projectNameInput = new Input($(PROJECT_NAME));
    private final Button updateSettingsButton = new Button($(byText(UPDATE_SETTINGS)));
    private final TextElement successMessage = new TextElement($(byText("Project settings were successfully updated!")));
    private final Button deleteProjectButton = new Button($(byText("Delete project")));

    @Step("Изменить название проекта на: {value}")
    public void changeName(String value) {
        log.info("Changing project name to: {}", value);
        projectNameInput
                .clear()
                .setValue(value);
    }

    @Step("Сохранить настройки проекта")
    public void save() {
        log.info("Saving project settings");
        updateSettingsButton.click();
        log.info("Checking project updated message");
        successMessage.shouldBeVisible();
    }

    @Step("Нажать удаление проекта")
    public void delete() {
        log.info("Clicking delete project button");
        deleteProjectButton.click();
    }

    @Step("Подтвердить удаление проекта")
    public void deleteConfirm() {
        log.info("Confirming project deletion");
        Button confirmDeleteButton = new Button($("dialog").$(byText("Delete project")));
        confirmDeleteButton.click();
    }
}