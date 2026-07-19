package ui.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class TextArea {

    private final SelenideElement element;

    public TextArea(SelenideElement element) {
        this.element = element;
    }

    public TextArea setValue(String value) {
        element.shouldBe(Condition.visible).setValue(value);
        return this;
    }
}