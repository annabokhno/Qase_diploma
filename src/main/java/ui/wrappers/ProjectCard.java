package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProjectCard {

    private final SelenideElement element;

    public ProjectCard(SelenideElement element) {
        this.element = element;
    }

    public void shouldBeVisible() {
        element.shouldBe(Condition.visible);
    }

    public void click() {
        element.shouldBe(Condition.visible).click();
    }
}