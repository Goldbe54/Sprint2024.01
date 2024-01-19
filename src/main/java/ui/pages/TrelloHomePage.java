package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;
import ui.fragments.AllClosedBoardsFragment;
import ui.fragments.TrelloHomeHeaderFragment;

public class TrelloHomePage extends BasePage {

    @Getter
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    @Getter
    private final AllClosedBoardsFragment allClosedBoardsFragment = new AllClosedBoardsFragment();
    @Getter
    private final TrelloHomeHeaderFragment trelloHomeHeaderFragment = new TrelloHomeHeaderFragment();
}