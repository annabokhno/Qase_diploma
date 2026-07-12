package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage {

    private final String name = "#project-name";
    private final String code = "#project-code";
    private final String description = "#description-area";

    public CreateProjectPage fillName(String value) {
        $(name).setValue(value);
        return this;
    }

    public CreateProjectPage fillCode(String value) {
        $(code).setValue(value);
        return this;
    }

    public CreateProjectPage fillDescription(String value) {
        $(description).setValue(value);
        return this;
    }

    public void clickCreate() {
        $(byText("Create project")).shouldBe(Condition.visible)
                .click();
    }

    public void create(String projectName, String projectCode) {
        fillName(projectName);
        fillCode(projectCode);
        clickCreate();
    }
}