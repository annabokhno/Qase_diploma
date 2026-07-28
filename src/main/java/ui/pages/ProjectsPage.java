package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import ui.wrappers.ProjectCard;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class ProjectsPage {

    private final Input searchProjectInput = new Input($(SEARCH_PROJECT));

    @Step("Открыть страницу проектов")
    public void openPage() {
        log.info("Opening projects page");
        open("/projects");
        $(CREATE_NEW_PROJECT).shouldBe(Condition.visible);
    }

    @Step("Нажать кнопку создания нового проекта")
    public CreateProjectPage clickCreateProject() {
        log.info("Clicking Create new project button");
        Button createProjectButton = new Button($(CREATE_NEW_PROJECT));
        createProjectButton.click();
        return new CreateProjectPage();
    }

    @Step("Проверить, что проект отображается: {projectName}")
    public void checkProjectDisplayed(String projectName) {
        log.info("Checking project '{}' is displayed", projectName);
        ProjectCard projectCard = new ProjectCard($$(PROJECT_CARDS).findBy(Condition.text(projectName)));
        projectCard.shouldBeVisible();
    }

    @Step("Проверить, что проект отсутствует: {projectName}")
    public void checkProjectNotDisplayed(String projectName) {
        log.info("Checking project '{}' is not displayed", projectName);
        $$(PROJECT_CARDS).findBy(Condition.text(projectName)).shouldNot(Condition.exist);
    }

    @Step("Открыть проект: {name}")
    public void openProject(String name) {
        log.info("Opening project '{}'", name);
        ProjectCard projectCard = new ProjectCard($(byText(name)));
        projectCard.click();
    }

    @Step("Выполнить поиск проекта: {name}")
    public void searchProject(String name) {
        log.info("Searching project '{}'", name);
        searchProjectInput.setValue(name);
    }

    @Step("Проверить результат поиска проекта: {name}")
    public void checkSearchResult(String name) {
        log.info("Checking search result for project '{}'", name);
        ProjectCard projectCard = new ProjectCard($x(String.format(PROJECT_BY_NAME, name)));
        projectCard.shouldBeVisible();
    }

    @Step("Открыть меню проекта: {projectName}")
    public ProjectSettingsPage openProjectMenu(String projectName) {
        log.info("Opening project menu for '{}'", projectName);
        ProjectCard projectCard = new ProjectCard($x(String.format(PROJECT_BY_NAME, projectName)));
        projectCard.shouldBeVisible();
        Button menuButton = new Button($x(String.format(PROJECT_MENU_BUTTON, projectName)));
        menuButton.click();
        return new ProjectSettingsPage();
    }
}