package ui.pages;

import lombok.Getter;
import ui.common.BasePage;
import ui.fragments.BoardWorkSpaceFragment;

public class BoardPage extends BasePage {
    @Getter
    private final BoardWorkSpaceFragment boardWorkSpaceFragment = new BoardWorkSpaceFragment();
}
