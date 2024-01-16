package tests.boardTests;

import api.clients.ApiBoardClient;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.refresh;

public class EditBoardTest extends TestInit {

    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final String updatedName = "Updated name board";

    @Test(description = "User update board name")
    @Description("PJ2024-13")
    public void userUpdateBoardName() {
        refresh();
        String boardName = boardBody.getName();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        apiBoardClient.updateBoardName(boardId, updatedName, 200).getName();

        back();
        refresh();

        List<String> listsBoardsTitles = trelloHomePage.getAllBoardsFragment().getBoardsTitlesElements();

        softAssert.assertTrue(listsBoardsTitles.stream().anyMatch(genre -> genre.equals(updatedName)),
                "No matches any board name");
        softAssert.assertAll();
    }
}
