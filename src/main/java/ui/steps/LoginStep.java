package ui.steps;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pages.LoginPage;


public class LoginStep {

    private static final Logger log = LoggerFactory.getLogger(LoginStep.class);
    private final LoginPage loginPage;

    public LoginStep() {
        this.loginPage = new LoginPage();
    }

    public LoginStep auth(String user, String password) {
        log.info("Авторизация пользователя: {}", user);
        loginPage.openPage();
        loginPage.login(user, password);
        log.info("Авторизация выполнена");
        return this;
    }

    @Step("Проверить успешный вход")
    public void checkLoginSuccess() {
    }

    @Step("Проверить неуспешный вход")
    public LoginStep checkLoginError() {
        log.info("Проверка ошибки авторизации");
        loginPage.checkLoginError();
        return this;
    }
}