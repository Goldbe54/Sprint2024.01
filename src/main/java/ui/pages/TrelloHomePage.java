package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.AllBoardsFragment;
import utils.ElementUtil;

import java.util.List;

public class TrelloHomePage extends BasePage {
    @Getter
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
}
