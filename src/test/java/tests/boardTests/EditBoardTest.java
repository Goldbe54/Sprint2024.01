package tests.boardTests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.refresh;
import static java.net.HttpURLConnection.HTTP_OK;

public class EditBoardTest extends TestInit {

    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final String updatedName = "Updated name board";

    @Test(description = "User update board name")
    @Description("PJ2024-13")
    public void userUpdateBoardName() {
        refresh();
        String boardName = boardBody.getName();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        apiBoardClient.updateBoardName(boardId, updatedName, HTTP_OK);

        back();
        refresh();

        List<String> listsBoardsTitles = trelloHomePage.getAllBoardsFragment().getBoardsTitlesElements();

        softAssert.assertTrue(listsBoardsTitles.stream().anyMatch(genre -> genre.equals(updatedName)),
                "No matches any board name");
        softAssert.assertAll();
    }

    @Test(description = "Check the view closed board")
    @Description("PJ2024-56")
    public void changeStatusBoard() {
        String boardName = boardBody.getName();
        boolean createdBoardOnWorkSpace = trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).isDisplayed();

        apiBoardClient.doOpenOrCloseExistBoard(boardId, true, HTTP_OK);
        trelloHomePage.getAllBoardsFragment().clickAllClosedBoards();

        boolean createdBoardOnClosedBoardsAfterClosed = trelloHomePage.getAllClosedBoardsFragment().specialBoardTitle(boardName).isDisplayed();

        trelloHomePage.getAllClosedBoardsFragment().clickCloseViewAllClosedBoardButton();

        softAssert.assertTrue(createdBoardOnWorkSpace, "The board " + boardName + "doesn't open");
        softAssert.assertTrue(createdBoardOnClosedBoardsAfterClosed, "The board " + boardName + "is open");
    }
}