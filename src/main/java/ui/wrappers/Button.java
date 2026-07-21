package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Button {

    private final SelenideElement element;

    public Button(SelenideElement element) {
        this.element = element;
    }

    public void click() {
        element.shouldBe(Condition.visible).click();
    }
}