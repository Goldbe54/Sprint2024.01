package ui.elements;

import com.codeborne.selenide.SelenideElement;
import ui.common.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class BoardElements extends BasePage {

    private final static String PRIVATE_VISIBILITY_ICON = "//span[@data-testid='PrivateIcon']";

    public SelenideElement getPrivateVisibilityIcon() {
        return $x(PRIVATE_VISIBILITY_ICON);
    }
}
