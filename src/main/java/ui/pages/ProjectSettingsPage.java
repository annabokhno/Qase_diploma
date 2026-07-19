package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectSettingsPage {

    @Step("Изменить название проекта на: {value}")
    public void changeName(String value) {
        log.info("Changing project name to: {}", value);
        $(PROJECT_NAME).clear();
        $(PROJECT_NAME).setValue(value);
    }

    @Step("Сохранить настройки проекта")
    public void save() {
        log.info("Saving project settings");
        $(byText(UPDATE_SETTINGS)).shouldBe(Condition.visible).click();
        log.info("Checking project updated message");
        $(byText("Project settings were successfully updated!")).shouldBe(Condition.visible);
    }

    @Step("Нажать удаление проекта")
    public void delete() {
        log.info("Clicking delete project button");
        $(byText("Delete project")).shouldBe(Condition.visible).click();
    }

    @Step("Подтвердить удаление проекта")
    public void deleteConfirm() {
        log.info("Confirming project deletion");
        $("dialog")
                .shouldBe(Condition.visible)
                .$(byText("Delete project"))
                .shouldBe(Condition.visible)
                .click();
    }
}