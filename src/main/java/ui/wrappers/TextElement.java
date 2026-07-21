package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class TextElement {

    private final SelenideElement element;

    public TextElement(SelenideElement element) {
        this.element = element;
    }

    public void shouldHaveText(String text) {
        element.shouldHave(Condition.text(text));
    }

    public void shouldBeVisible() {
        element.shouldBe(Condition.visible);
    }
}