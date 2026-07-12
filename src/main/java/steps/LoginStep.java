package steps;

import io.qameta.allure.Step;
import pages.LoginPage;
import pages.ProjectsPage;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep() {
        this.loginPage = new LoginPage();
    }

    public LoginStep auth() {
        loginPage.openPage();
        loginPage.login(System.getenv("QASE_EMAIL"), System.getenv("QASE_PASSWORD"));
        return this;
    }

    @Step("Авторизация")
    public LoginStep auth(String user, String password) {
        loginPage.openPage();
        loginPage.login(user, password);
        return this;
    }

    @Step("Проверить успешный вход")
    public void checkLoginSuccess() {
        new ProjectsPage()
                .openPage();
    }

    @Step("Проверить неуспешный вход")
    public LoginStep checkLoginError() {
        loginPage.checkLoginError();
        return this;
    }
}