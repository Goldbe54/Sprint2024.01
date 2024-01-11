package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.BoardWorkSpaceFragment;
import ui.fragments.CardWorkSpaceFragment;

public class BoardPage extends BasePage {

    @Getter
    private final BoardWorkSpaceFragment boardWorkSpaceFragment = new BoardWorkSpaceFragment();
    @Getter
    private final CardWorkSpaceFragment cardFragment = new CardWorkSpaceFragment();
}