package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectsPage {

    @Step("Открыть страницу проектов")
    public void openPage() {
        log.info("Opening projects page");
        open("/projects");
    }

    @Step("Нажать кнопку создания нового проекта")
    public CreateProjectPage clickCreateProject() {
        log.info("Clicking Create new project button");
        $(byText("Create new project")).shouldBe(Condition.visible).click();
        return new CreateProjectPage();
    }

    @Step("Проверить, что проект отображается: {projectName}")
    public void checkProjectDisplayed(String projectName) {
        log.info("Checking project '{}' is displayed", projectName);
        $$(PROJECT_CARDS)
                .findBy(Condition.text(projectName))
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Проверить, что проект отсутствует: {projectName}")
    public void checkProjectNotDisplayed(String projectName) {
        log.info("Checking project '{}' is not displayed", projectName);
        $$(PROJECT_CARDS).findBy(Condition.text(projectName)).shouldNot(Condition.exist);
    }

    @Step("Открыть проект: {name}")
    public void openProject(String name) {
        log.info("Opening project '{}'", name);
        $(byText(name)).shouldBe(Condition.visible).click();
    }

    @Step("Выполнить поиск проекта: {name}")
    public void searchProject(String name) {
        log.info("Searching project '{}'", name);
        $(SEARCH_PROJECT).shouldBe(Condition.visible).setValue(name);
    }

    @Step("Проверить результат поиска проекта: {name}")
    public void checkSearchResult(String name) {
        log.info("Checking search result for project '{}'", name);
        $x(String.format(PROJECT_BY_NAME, name)).shouldBe(Condition.visible);
    }

    @Step("Открыть меню проекта: {projectName}")
    public ProjectSettingsPage openProjectMenu(String projectName) {
        log.info("Opening project menu for '{}'", projectName);
        $x(String.format(PROJECT_BY_NAME, projectName)).shouldBe(Condition.visible);
        $x(String.format(PROJECT_MENU_BUTTON, projectName)).click();
        return new ProjectSettingsPage();
    }
}