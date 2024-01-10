package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.TrelloHeaderFragment;

public class TrelloPage extends BasePage {
    @Getter
    TrelloHeaderFragment trelloHeaderFragment = new TrelloHeaderFragment();
}
