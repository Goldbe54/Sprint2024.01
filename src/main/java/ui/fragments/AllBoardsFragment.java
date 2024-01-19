package ui.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class AllBoardsFragment {

    private static final String GENERAL_YOUR_BOARDS_TITLES = ".//div[@class='board-tile-details-name']";
    private static final String SPECIAL_BOARD_TITLE = ".//div[@class='board-tile-details-name']/div[contains(text(),'%s')]";
    private static final String YOUR_WORKSPACE = ".//h3[text()='YOUR WORKSPACES']/..";
    private static final String ALL_BOARDS = ".//div[@class='board-tile-details-name']";
    private static final String VIEW_ALL_CLOSED_BOARDS = ".//button[contains(@class,'view-all-closed-boards-button')]";

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

    public SelenideElement specialBoardTitle(String boardName) {
        return rootElement().$x(format(SPECIAL_BOARD_TITLE, boardName)).shouldBe(visible);
    }

    public SelenideElement getYourWorkspace() {
        return rootElement().$x(YOUR_WORKSPACE).shouldBe(exist);
    }

    public ElementsCollection getAllBoardsInWorkspace() {
        return getYourWorkspace().$$x(ALL_BOARDS).shouldBe(sizeGreaterThan(0));
    }

    public List<String> getBoardsTitlesElements() {
        ElementsCollection allBoardsList = getAllBoardsInWorkspace();
        return ElementUtil.getListOfStrings(allBoardsList);
    }

    public SelenideElement getViewAllClosedBoards() {
        return rootElement().$x(VIEW_ALL_CLOSED_BOARDS).shouldBe(visible);
    }
}