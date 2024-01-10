package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.LoginFragment;

public class LoginPage extends BasePage {
    @Getter
    private final LoginFragment loginFragment = new LoginFragment();


}
