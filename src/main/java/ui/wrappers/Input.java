package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Input {

    private final SelenideElement element;

    public Input(SelenideElement element) {
        this.element = element;
    }

    public Input setValue(String value) {
        element.shouldBe(Condition.visible).setValue(value);
        return this;
    }

    public Input clear() {
        element.shouldBe(Condition.visible).clear();
        return this;
    }
}