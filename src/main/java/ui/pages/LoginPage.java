package ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;
import static ui.dict.Elements.*;

@Log4j2
public class LoginPage {

    private final String LOGIN = "[name=email]";
    private final String PASSWORD = "[name=password]";

    @Step("Открыть страницу авторизации")
    public void openPage() {
        log.info("Opening login page");
        open("/login");
    }

    @Step("Авторизация пользователя: {user}")
    public void login(String user, String password) {
        log.info("Login with user: {}", user);
        if ($(shadowCss("#accept", "#usercentrics-cmp-ui")).exists()) {
            $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
            sleep(1000);
        }
        log.info("Filling email field");
        $(LOGIN).shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(user);
        log.info("Filling password field");
        $(PASSWORD).shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(password);
        log.info("Clicking Sign In button");
        $(byText(SIGN_IN)).shouldBe(Condition.visible).click();
        sleep(3000);
    }

    @Step("Проверить ошибку авторизации")
    public void checkLoginError() {
        log.info("Checking login error message");
        $("[role='alert'], small.Kkpqjk")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}