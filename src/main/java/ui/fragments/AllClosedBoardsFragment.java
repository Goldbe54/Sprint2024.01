package ui.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class AllClosedBoardsFragment {

    private static final String SPECIAL_BOARD_TITLE = ".//a[contains(text(),'%s')]";
    private static final String HEADER_CLOSE_ICON = ".//ancestor::header//span[@data-testid='CloseIcon']";

    private SelenideElement rootElement() {
        return $x("//span[@data-testid='ArchiveIcon']//ancestor::div[@tabindex='-1']");
    }

    public SelenideElement specialBoardTitle(String boardName) {
        return rootElement().$x(format(SPECIAL_BOARD_TITLE, boardName)).shouldBe(visible);
    }

    public void clickCloseViewAllClosedBoardButton() {
        rootElement().$x(HEADER_CLOSE_ICON).click();
    }
}