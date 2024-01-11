package ui.fragments;

import com.codeborne.selenide.SelenideElement;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TrelloHeaderFragment {

    private static final String LOGIN_BUTTON = ".//a[contains(@href,'login')]";

    private SelenideElement rootElement() {
        return $x("//div[contains(@Class,'BigNavstyles__InnerHeader')]");
    }

    public SelenideElement loginButton() {
        return rootElement().$x(LOGIN_BUTTON).shouldBe(visible);
    }
}
