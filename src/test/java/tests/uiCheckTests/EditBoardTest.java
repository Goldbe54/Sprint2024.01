package tests.uiCheckTests;

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
import ui.fragments.AllBoardsFragment;

public class EditBoardTest extends TestInit {

    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private SoftAssert softAssert = new SoftAssert();
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

        apiBoardClient.updateBoard("prefs/permissionLevel", "private",
                boardId, 200);

    }

    @AfterMethod
    private void deleteBoard() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}
