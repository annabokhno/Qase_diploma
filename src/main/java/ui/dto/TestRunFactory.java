package ui.dto;

public class TestRunFactory {

    public static TestRun getTestRun() {

        return TestRun.builder()
                .name("Regression run " + System.currentTimeMillis())
                .build();
    }
}