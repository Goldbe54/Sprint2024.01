package ui.steps;

import io.qameta.allure.Step;
import ui.pages.LoginPage;
import ui.pages.TrelloPage;

public class PreLoginSteps {
    TrelloPage trelloPage = new TrelloPage();
    LoginPage loginPage = new LoginPage();

    @Step("Login via email by {0} and password: {1}")
    public void loginViaEmail(String email, String password) {
        trelloPage.getTrelloHeaderFragment().loginButton().click();
        loginPage.getLoginFragment().loginViaEmail(email,password);
    }
}
