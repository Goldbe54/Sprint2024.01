package ui.steps;

import io.qameta.allure.Step;
import ui.fragments.LoginFragment;
import ui.fragments.TrelloHeaderFragment;

public class PreLoginSteps {
    LoginFragment loginFragment = new LoginFragment();
    TrelloHeaderFragment trelloHeaderPage = new TrelloHeaderFragment();

    @Step("Login via email by {0} and password: {1}")
    public void loginViaEmail(String email, String password) {
        trelloHeaderPage.loginButton().click();
        loginFragment.loginViaEmail(email,password);
    }
}
