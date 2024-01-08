package ui.steps;

import ui.fragments.LoginFragment;
import ui.fragments.TrelloHeaderFragment;

public class PreLoginSteps {
    LoginFragment loginFragment = new LoginFragment();
    TrelloHeaderFragment trelloHeaderPage = new TrelloHeaderFragment();

    public void loginViaEmail(String email, String password) {
        trelloHeaderPage.goToLoginPage();
        loginFragment.loginViaEmail(email,password);
    }
}
