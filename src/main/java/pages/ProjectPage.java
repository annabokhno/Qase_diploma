package pages;


import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;


public class ProjectPage {

    private final String header = "h1";
    private final String settings = "a[href*='/settings/general']";

    public void checkOpened() {
        $(header).shouldBe(Condition.visible);
    }

    public ProjectSettingsPage openSettings() {
        $(settings).click();
        return new ProjectSettingsPage();
    }

    public TestCasePage openTestCases() {
        $("a[href*='/case']").click();
        return new TestCasePage();
    }
}