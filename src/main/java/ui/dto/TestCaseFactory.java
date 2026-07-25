package ui.dto;

public class TestCaseFactory {

    public static TestCase getTestCase() {

        return TestCase.builder()
                .title("Authorization test")
                .build();
    }
}