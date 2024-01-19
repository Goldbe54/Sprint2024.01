package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;
import ui.fragments.AllClosedBoardsFragment;

public class TrelloHomePage extends BasePage {

    @Getter
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    @Getter
    private final AllClosedBoardsFragment allClosedBoardsFragment = new AllClosedBoardsFragment();
}