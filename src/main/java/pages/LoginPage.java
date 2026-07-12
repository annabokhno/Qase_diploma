package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static dict.Elements.SIGN_IN;

public class LoginPage {

    private final String LOGIN = "[name=email]";
    private final String PASSWORD = "[name=password]";
    private final String PROJECTS_HEADER = "h1";
    private final String ERROR_MESSAGE = "[role='alert'], small.Kkpqjk";

    public void openPage() {
        open("/login");
    }

    public void login(String user, String password) {
        SelenideElement acceptButton = $(shadowCss("#accept", "#usercentrics-cmp-ui"));
        try {
            acceptButton.shouldBe(Condition.visible, Duration.ofSeconds(5))
                    .click();
        } catch (Exception ignored) {
        }
        $(LOGIN).setValue(user);
        $(PASSWORD).setValue(password);
        $(byText(SIGN_IN)).click();
    }

    public void checkLoginError() {
        $(ERROR_MESSAGE).shouldBe(Condition.visible);
    }

    public void checkProjectsPageOpened() {
        $(PROJECTS_HEADER).shouldHave(Condition.text("Projects"));
    }
}