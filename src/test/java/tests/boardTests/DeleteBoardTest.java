package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.fragments.AllBoardsFragment;
import java.util.List;

public class DeleteBoardTest extends TestInit {
    private String boardId;
    private String boardNew;
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final SoftAssert softAssert = new SoftAssert();
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();

    @BeforeMethod
    public void setUp() {

        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
    }

    @Test(description = "PJ2024-39")
    @Description("Delete Board")
    public void deleteBoardTest() {
        boardNew = apiBoardClient.createNewBoard(boardBody, 200).getId();

        List<String> allBoardsList;
        allBoardsList = allBoardsFragment.getAllYourBoardsTitles();
        int allBoardsCount = allBoardsList.size();

        softAssert.assertTrue(allBoardsCount > 0, "List is empty");

        apiBoardClient.deleteExistingBoard(boardNew, 200);
        int allBoardsCountAfterDeleting = allBoardsList.size();

        softAssert.assertEquals(allBoardsCount - allBoardsCountAfterDeleting, 1);
    }

    @AfterMethod
    private void shoutDown() {

        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}

