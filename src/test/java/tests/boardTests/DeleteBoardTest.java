package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.fragments.AllBoardsFragment;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class DeleteBoardTest extends TestInit {
    private String newBoardId;
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final SoftAssert softAssert = new SoftAssert();
    private final AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private final BoardBuilder secondBoardBody = BoardBuilder.builder().build();

    @Test(description = "PJ2024-39")
    @Description("Delete Board")
    public void deleteBoardTest() {
        newBoardId= apiBoardClient.createNewBoard(secondBoardBody, 200).getId();

        refresh();
        List<String> allBoardsList;
        List<String> allBoardsListAfterDeleting;
        allBoardsList = allBoardsFragment.getAllYourBoardsTitles();

        int allBoardsCount = allBoardsList.size();
        System.out.println("Size after creation: " + allBoardsCount);

        softAssert.assertTrue(allBoardsCount > 0, "List is empty");

        apiBoardClient.deleteExistingBoard(newBoardId, 200);
        refresh();

        allBoardsListAfterDeleting = allBoardsFragment.getAllYourBoardsTitles();

        int allBoardsCountAfterDeleting = allBoardsListAfterDeleting.size();
        System.out.println("Size after deleting: " + allBoardsCountAfterDeleting);

        softAssert.assertEquals(allBoardsCount - allBoardsCountAfterDeleting, 1);
        softAssert.assertAll();
    }
}

