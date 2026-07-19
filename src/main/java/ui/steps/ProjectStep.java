package ui.steps;

import io.qameta.allure.Step;
import ui.pages.ProjectPage;
import ui.pages.ProjectSettingsPage;
import ui.pages.ProjectsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectStep {

    private static final Logger log = LoggerFactory.getLogger(ProjectStep.class);
    private final ProjectsPage projectsPage;
    private final ProjectPage projectPage;

    public ProjectStep() {
        this.projectsPage = new ProjectsPage();
        this.projectPage = new ProjectPage();
    }

    @Step("Создать проект {name}")
    public void createProject(String name, String code) {
        log.info("Создание проекта: {}", name);
        projectsPage.openPage();
        projectsPage.clickCreateProject().create(name, code);
        log.info("Проект создан: {}", name);
    }

    @Step("Открыть проект")
    public void openProject(String name) {
        log.info("Открытие проекта: {}", name);
        projectsPage.openPage();
        projectsPage.openProject(name);
    }

    @Step("Проверить открытие проекта")
    public void checkOpened(String code) {
        log.info("Проверка открытия проекта с кодом: {}", code);
        new ProjectPage().checkOpened(code);
    }

    @Step("Редактировать проект")
    public ProjectStep editProject(String oldName, String newName) {
        log.info("Редактирование проекта: {} -> {}", oldName, newName);
        openProject(oldName);
        projectPage
                .openSettings()
                .changeName(newName);
        new ProjectSettingsPage()
                .save();
        log.info("Название проекта изменено на: {}", newName);
        return this;
    }

    @Step("Проверить изменение проекта")
    public void checkUpdated(String name) {
        log.info("Проверка изменения проекта: {}", name);
        projectsPage.openPage();
        projectsPage.checkProjectDisplayed(name);
    }

    @Step("Удалить проект")
    public void deleteProject(String name) {
        log.info("Удаление проекта: {}", name);
        openProject(name);
        projectPage.openSettings().delete();
        new ProjectSettingsPage().deleteConfirm();
        log.info("Проект удален: {}", name);
    }

    @Step("Проверить удаление")
    public void checkDeleted(String name) {
        log.info("Проверка удаления проекта: {}", name);
        projectsPage.openPage();
        projectsPage.checkProjectNotDisplayed(name);
    }

    @Step("Поиск проекта")
    public void search(String name) {
        log.info("Поиск проекта: {}", name);
        projectsPage.openPage();
        projectsPage.searchProject(name);
    }

    @Step("Проверить результат поиска")
    public void checkSearch(String name) {
        log.info("Проверка результата поиска проекта: {}", name);
        projectsPage.checkSearchResult(name);
    }
}