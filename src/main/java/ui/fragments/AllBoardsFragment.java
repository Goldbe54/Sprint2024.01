package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AllBoardsFragment {

    private final String GENERAL_YOUR_BOARDS_TITLES = ".//div[@class='board-tile-details-name']";

    private SelenideElement rootElement() {
        return $x("//div[@class='all-boards']");
    }

    public ElementsCollection GeneralYourBoardsTitles() {
        return rootElement().$$x(GENERAL_YOUR_BOARDS_TITLES);
    }
}
