package ui.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;

public class LoginFragment {

    private final String EMAIL_FIELD = ".//input[@id='username']";
    private final String CONTINUE_LOGIN_BUTTON = ".//button[@id='login-submit']/span";
    private final String PASSWORD_FIELD = ".//input[@id='password']";
    private final String LOGIN_BUTTON = ".//button[@id='login-submit']/span";

    private SelenideElement rootElement() {
        return $x("//div[@id='root']");
    }

    public SelenideElement emailField() {
        return rootElement().$x(EMAIL_FIELD).shouldBe(interactable);
    }

    public SelenideElement continueButton() {
        return rootElement().$x(CONTINUE_LOGIN_BUTTON).shouldBe(interactable);
    }

    public SelenideElement passwordField() {
        return rootElement().$x(PASSWORD_FIELD).shouldBe(interactable);
    }

    public SelenideElement loginButton() {
        return rootElement().$x(LOGIN_BUTTON).shouldBe(interactable);
    }

    public void loginViaEmail(String email, String password) {
        emailField().sendKeys(email);
        continueButton().click();
        passwordField().sendKeys(password);
        loginButton().click();
    }
}