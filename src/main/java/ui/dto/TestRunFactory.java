package ui.dto;

public class TestRunFactory {

    public static TestRun getTestRun() {
        return new TestRun(
                "Regression run " + System.currentTimeMillis()
        );
    }
}