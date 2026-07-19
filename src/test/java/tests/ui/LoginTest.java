package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(
            priority = 1,
            description = "Проверка логина с позитивным логином и паролем",
            testName = "Позитивный логин",
            groups = {"smoke"}
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Log in")
    @Story("Log in with positive credential")
    @Description("Проверка логина с позитивным логином и паролем")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithPositiveCredentials() {
        loginStep.auth(EMAIL, PASSWORD);
        loginStep.checkLoginSuccess();
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginData() {
        return new Object[][]{
                {"", ""},
                {"anna@mail.", "123456"},
        };
    }

    @Test(
            priority = 2,
            description = "Проверка логина с негативным логином и паролем",
            testName = "Негативный логин",
            groups = {"smoke"},
            dataProvider = "negativeLoginData"
    )
    @Owner("Bokhno A.M.")
    @Epic("Qase")
    @Feature("Log in")
    @Story("Log in with negative credential")
    @Description("Проверка логина с негативным логином и паролем")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginWithNegativeCredentials(String user, String password) {
        loginStep.auth(user, password);
        loginStep.checkLoginError();
    }
}

