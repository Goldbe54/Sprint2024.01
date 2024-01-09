package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class AllBoardsFragment {

    private final String GENERAL_YOUR_BOARDS_TITLES = ".//div[@class='board-tile-details-name']";
    private final String SPECIAL_BOARD_TITLE = ".//div[@class='board-tile-details-name']/div[contains(text(),'%s')]";

    private SelenideElement rootElement() {
        return $x("//div[@class='all-boards']");
    }

    public ElementsCollection GeneralYourBoardsTitles() {
        return rootElement().$$x(GENERAL_YOUR_BOARDS_TITLES);
    }

    public List<String> getAllYourBoardsTitles() {
        ElementsCollection collection = GeneralYourBoardsTitles();

        return ElementUtil.getListOfStrings(collection);
    }

    public SelenideElement getSpecialBoardTitle (String boardId){
        return rootElement().$x(format(SPECIAL_BOARD_TITLE,boardId));
    }
}
