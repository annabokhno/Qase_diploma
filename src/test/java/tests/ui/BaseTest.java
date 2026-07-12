package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;
import steps.LoginStep;
import steps.ProjectStep;

import java.util.HashMap;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected static final String EMAIL =
            System.getenv("QASE_EMAIL");

    protected static final String PASSWORD =
            System.getenv("QASE_PASSWORD");

    LoginPage loginPage;
    protected String projectName;
    ProjectsPage projectsPage;
    ProjectPage projectPage;
    LoginStep loginStep;
    ProjectStep projectStep;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {

        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = Boolean.parseBoolean(
                System.getenv().getOrDefault("HEADLESS", "false"));
        if ("firefox".equalsIgnoreCase(browser)) {
            Configuration.browser = "firefox";
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            Configuration.browserCapabilities = options;
        } else {
            Configuration.browser = "chrome";

            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            Configuration.browserCapabilities = options;
        }

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        projectPage = new ProjectPage();
        loginStep = new LoginStep();
        projectStep = new ProjectStep();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod
    public void tearDown() {
        if (projectName != null) {
            $(byText(projectName))
                    .ancestor("tr")
                    .find("button[aria-label='Open action menu']")
                    .click();
            $("[data-testid=remove]").click();
            $x("//span[text()='Delete project']").click();
        }
        getWebDriver().quit();
    }
}