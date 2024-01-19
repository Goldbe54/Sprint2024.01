package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.BoardHeaderFragment;
import ui.fragments.BoardMenuFragment;
import ui.fragments.BoardWorkSpaceFragment;
import ui.fragments.CardWorkSpaceFragment;
import utils.ElementUtil;

import java.util.List;

public class BoardPage extends BasePage {

    @Getter
    private final BoardWorkSpaceFragment boardWorkSpaceFragment = new BoardWorkSpaceFragment();
    @Getter
    private final CardWorkSpaceFragment cardFragment = new CardWorkSpaceFragment();
    @Getter
    private final BoardMenuFragment boardMenuFragment = new BoardMenuFragment();
    @Getter
    private final BoardHeaderFragment boardHeaderFragment = new BoardHeaderFragment();

    public List<String> getListOfAllArchivedTitles() {
        boardHeaderFragment.getBoardMenuFragment().click();
        boardMenuFragment.getBackButton().click();
        boardMenuFragment.getArchiveButton().click();
        ElementsCollection collection = boardMenuFragment.getAllArchivedTitles();
        return ElementUtil.getListOfStrings(collection);
    }

}