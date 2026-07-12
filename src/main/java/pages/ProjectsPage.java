package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage {

    private final String createProjectButton = "button:has-text('Create new project')";
    private final String projectCards = "h2";

    public void openPage() {
        open("/projects");
    }

    public CreateProjectPage clickCreateProject() {
        $(createProjectButton).shouldBe(Condition.visible).click();
        return new CreateProjectPage();
    }

    public boolean isProjectDisplayed(String projectName) {
        return $$(projectCards)
                .findBy(Condition.text(projectName))
                .exists();
    }

    public void openProject(String projectName) {
        $$(projectCards).findBy(Condition.text(projectName)).click();
    }

    public void searchProject(String name) {
        $("input[placeholder*='Search']").setValue(name);
    }

    public void checkSearchResult(String name) {
        $$(projectCards).findBy(Condition.text(name))
                .shouldBe(Condition.visible);
    }

    public ProjectSettingsPage openProjectMenu(String projectName) {
        $x("//h2[contains(text(),'" + projectName + "')]").shouldBe(Condition.visible);
        $x("//h2[contains(text(),'" + projectName + "')]/ancestor::*[contains(@class,'project')]//button").click();
        return new ProjectSettingsPage();
    }
}