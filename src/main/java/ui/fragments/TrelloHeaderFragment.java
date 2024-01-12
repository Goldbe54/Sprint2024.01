package ui.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TrelloHeaderFragment {

    private static final String LOGIN_BUTTON = ".//a[contains(@href,'login')]";

    private SelenideElement rootElement() {
        return $x("//div[contains(@Class,'BigNavstyles__InnerHeader')]");
    }

    public SelenideElement loginButton() {
        return rootElement().$x(LOGIN_BUTTON).shouldBe(visible);
    }
}
