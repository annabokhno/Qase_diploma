package ui.steps;

import io.qameta.allure.Step;
import ui.pages.TestRunsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRunStep {

    private static final Logger log = LoggerFactory.getLogger(TestRunStep.class);
    private final TestRunsPage testRunsPage = new TestRunsPage();

    @Step("Открыть Test Runs")
    public void openTestRuns() {
        log.info("Открытие раздела Test Runs");
        testRunsPage.openTestRuns();
    }

    @Step("Создать новый Test Run")
    public void createRun(String name) {
        log.info("Создание нового Test Run: {}", name);
        testRunsPage
                .clickStartNewRun()
                .setTitle(name)
                .selectCases()
                .startRun();
        log.info("Test Run создан: {}", name);
    }
}