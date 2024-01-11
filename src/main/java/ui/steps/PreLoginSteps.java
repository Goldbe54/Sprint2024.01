package ui.steps;

import io.qameta.allure.Step;
import ui.pages.LoginPage;
import ui.pages.TrelloWelcomePage;

public class PreLoginSteps {

    TrelloWelcomePage trelloPage = new TrelloWelcomePage();
    LoginPage loginPage = new LoginPage();

    @Step("Login via email by {0} and password: {1}")
    public void loginViaEmail(String email, String password) {
        trelloPage.getTrelloHeaderFragment().loginButton().click();
        loginPage.getLoginFragment().loginViaEmail(email,password);
    }
}