package pages;


import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class TestCasePage {

    private final String create = "button:has-text('Create case')";
    private final String title = "#title";

    public void clickCreateCase() {
        $(create).click();
    }

    public void fillTitle(String value) {
        $(title).setValue(value);
    }

    public void save() {
        $("button:has-text('Save')").click();
    }

    public void checkCreated(String name) {
        $("body").shouldHave(Condition.text(name));
    }
}
