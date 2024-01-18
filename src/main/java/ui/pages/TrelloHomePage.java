package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;
import ui.fragments.AllClosedBoardsFragment;

@Getter
public class TrelloHomePage extends BasePage {

    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private final AllClosedBoardsFragment allClosedBoardsFragment = new AllClosedBoardsFragment();
}
