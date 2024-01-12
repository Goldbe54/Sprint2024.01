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

import static com.codeborne.selenide.Selenide.refresh;

public class DeleteBoardTest extends TestInit {
    private String boardId;
    private String boardNew;
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final SoftAssert softAssert = new SoftAssert();
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private final BoardBuilder firstBoardBody = BoardBuilder.builder().build();
    private final BoardBuilder secondBoardBody = BoardBuilder.builder().build();

    @BeforeMethod
    public void setUp() {
        boardId = apiBoardClient.createNewBoard(firstBoardBody, 200).getId();

    }

    @Test(description = "PJ2024-39")
    @Description("Delete Board")
    public void deleteBoardTest() {
        boardNew = apiBoardClient.createNewBoard(secondBoardBody, 200).getId();

        List<String> allBoardsList;
        List<String> allBoardsListAfterDeleting;
        allBoardsList = allBoardsFragment.getAllYourBoardsTitles();
        refresh();

        int allBoardsCount = allBoardsList.size();
        System.out.println("Size after creation: " + allBoardsCount);

        softAssert.assertTrue(allBoardsCount > 0, "List is empty");

        apiBoardClient.deleteExistingBoard(boardNew, 200);
        refresh();

        allBoardsListAfterDeleting = allBoardsFragment.getAllYourBoardsTitles();

        int allBoardsCountAfterDeleting = allBoardsListAfterDeleting.size();
        System.out.println("Size after deleting: " + allBoardsCountAfterDeleting);

        softAssert.assertEquals(allBoardsCount - allBoardsCountAfterDeleting, 1);
        softAssert.assertAll();
    }

    @AfterMethod
    private void shoutDown() {

        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}

