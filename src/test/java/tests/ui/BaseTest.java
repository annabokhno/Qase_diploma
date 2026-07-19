package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import ui.pages.LoginPage;
import ui.pages.ProjectPage;
import ui.pages.ProjectsPage;
import ui.steps.LoginStep;
import ui.steps.ProjectStep;
import ui.steps.TestRunStep;
import utils.PropertyReader;

import java.util.HashMap;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected static final String EMAIL = System.getProperty("QASE_EMAIL", PropertyReader.getProperty("QASE_EMAIL"));
    protected static final String PASSWORD = System.getProperty("QASE_PASSWORD", PropertyReader.getProperty("QASE_PASSWORD"));

    LoginPage loginPage;
    protected String projectName;
    ProjectsPage projectsPage;
    ProjectPage projectPage;
    LoginStep loginStep;
    ProjectStep projectStep;
    TestRunStep testRunStep;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        projectName = null;
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
        testRunStep = new TestRunStep();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}