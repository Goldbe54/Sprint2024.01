package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.refresh;

public class EditBoardTest extends TestInit {

    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private TrelloHomePage trelloHomePage = new TrelloHomePage();
    private String boardId;
    private SoftAssert softAssert = new SoftAssert();
    private String updatedName = "Updated name board";

    @BeforeMethod
    private void setUp() {
        boardBody = BoardBuilder.builder().build();
    }

    @Test(description = "User update board name")
    @Description("PJ2024-13")
    public void userUpdateBoardName() {

        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();

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
