package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;

public class TrelloHomePage extends BasePage {

    @Getter
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
}
