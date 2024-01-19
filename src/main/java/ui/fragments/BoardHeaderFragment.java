package ui.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$x;

public class BoardHeaderFragment {

    private String BOARD_MENU_BUTTON = ".//span[@data-testid='OverflowMenuHorizontalIcon']";
    
    private SelenideElement rootElement() {
        return $x("//div[contains(@Class,'js-board-header')]");
    }

    public SelenideElement getBoardMenuFragment() {
        return  rootElement().$x(BOARD_MENU_BUTTON).shouldBe(interactable);
    }
}
