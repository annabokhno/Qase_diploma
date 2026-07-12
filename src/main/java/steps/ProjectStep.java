package steps;


import io.qameta.allure.Step;
import pages.ProjectPage;
import pages.ProjectSettingsPage;
import pages.ProjectsPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ProjectStep {

    ProjectsPage projectsPage =
            new ProjectsPage();

    @Step("Создать проект {name}")
    public void createProject(String name, String code) {
        projectsPage.openPage();
        projectsPage.clickCreateProject()
                .create(name, code);
    }

    @Step("Проверить создание проекта")
    public void checkProjectCreated(String name) {
        projectsPage.openPage();
        assertTrue(projectsPage.isProjectDisplayed(name), "Проект отсутствует");
    }

    @Step("Открыть проект")
    public void openProject(String name) {
        projectsPage.openPage();
        projectsPage.openProject(name);
    }

    @Step("Проверить открытие проекта")
    public void checkOpened() {
        new ProjectPage().checkOpened();
    }

    @Step("Редактировать проект")
    public void editProject(String oldName, String newName) {
        openProject(oldName);
        new ProjectPage()
                .openSettings()
                .changeName(newName);
        new ProjectSettingsPage()
                .save();
    }

    @Step("Проверить изменение проекта")
    public void checkUpdated(String name) {
        projectsPage.openPage();
        assertTrue(projectsPage.isProjectDisplayed(name));
    }

    @Step("Удалить проект")
    public void deleteProject(String name) {
        openProject(name);
        new ProjectPage()
                .openSettings()
                .delete();
        new ProjectSettingsPage()
                .confirmDelete();
    }

    @Step("Проверить удаление")
    public void checkDeleted(String name) {
        projectsPage.openPage();
        assertFalse(
                projectsPage.isProjectDisplayed(name));
    }

    @Step("Поиск проекта")
    public void search(String name) {
        projectsPage.openPage();
        projectsPage.searchProject(name);
    }

    @Step("Проверить результат поиска")
    public void checkSearch(String name) {
        projectsPage.checkSearchResult(name);
    }
}