package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.elements.BoardElements;
import ui.fragments.AllBoardsFragment;
import ui.pages.TrelloHomePage;

import static org.testng.AssertJUnit.assertTrue;

public class EditBoardTest extends TestInit {

    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private TrelloHomePage trelloHomePage = new TrelloHomePage();
    private SoftAssert softAssert = new SoftAssert();
    private BoardElements boardElements = new BoardElements();
    private BoardResponse response;
    private String boardId;

    @BeforeMethod
    private void setUp() {
        boardBody = BoardBuilder.builder().build();

    }

    @Test(description = "PJ2024-13")
    @Description("Edit board")
    public void userChangeBoardPermissionLevel() {

        response = apiBoardClient.createNewBoard(boardBody, 200);
        boardId = response.getId();

        Selenide.refresh();

        String boardBodyName = boardBody.getName();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBodyName).click();

        apiBoardClient.updateBoard("prefs/permissionLevel", "private",
                boardId, 200);

        assertTrue(boardElements.getPrivateVisibilityIcon().isDisplayed());

    }

    @AfterMethod
    private void deleteBoard() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}
