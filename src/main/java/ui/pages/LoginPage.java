package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.wrappers.Button;
import ui.wrappers.Input;
import ui.wrappers.TextElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.SIGN_IN;

@Log4j2
public class LoginPage {

    private final String LOGIN = "[name=email]";
    private final String PASSWORD = "[name=password]";
    private final Input loginInput = new Input($(LOGIN));
    private final Input passwordInput = new Input($(PASSWORD));
    private final Button signInButton = new Button($(byText(SIGN_IN)));
    private final TextElement loginErrorMessage = new TextElement($("[role='alert'], small.Kkpqjk"));

    @Step("Открыть страницу авторизации")
    public void openPage() {
        log.info("Opening login page");
        open("/login");
    }

    @Step("Авторизация пользователя: {user}")
    public void login(String user, String password) {
        log.info("Login with user: {}", user);
        if ($(shadowCss("#accept", "#usercentrics-cmp-ui")).exists()) {
            $(shadowCss("#accept", "#usercentrics-cmp-ui"))
                    .click();
            sleep(1000);
        }
        log.info("Filling email field");
        loginInput.setValue(user);
        log.info("Filling password field");
        passwordInput.setValue(password);
        log.info("Clicking Sign In button");
        signInButton.click();
        sleep(3000);
    }

    @Step("Проверить ошибку авторизации")
    public void checkLoginError() {
        log.info("Checking login error message");
        loginErrorMessage.shouldBeVisible();
    }
}