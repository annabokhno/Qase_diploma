package pages;


import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;


public class ProjectSettingsPage {

    private final String name = "#project-name";
    private final String description = "#description-area";
    private final String update = "button:has-text('Update settings')";
    private final String delete = "button:has-text('Delete project')";

    public void changeName(String value) {
        $(name).clear();
        $(name).setValue(value);
    }

    public void changeDescription(String value) {
        $(description).setValue(value);
    }

    public void save() {
        $(update).shouldBe(Condition.visible).click();
    }

    public void delete() {
        $(delete).click();
    }

    public void confirmDelete() {
        $("button:has-text('Delete')").click();
    }


}