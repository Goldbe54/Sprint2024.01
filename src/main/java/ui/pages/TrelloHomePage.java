package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;
import utils.ElementUtil;

import java.util.List;

public class TrelloHomePage extends BasePage {
    AllBoardsFragment allBoardsFragment = new AllBoardsFragment();

    public List<String> getAllYourBoardsTitles() {
        ElementsCollection collection = allBoardsFragment.GeneralYourBoardsTitles();

        return ElementUtil.getListOfStrings(collection);
    }
}
