package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class DeleteBoardTest extends TestInit {
    private String newBoardId;
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final SoftAssert softAssert = new SoftAssert();
    private final BoardBuilder secondBoardBody = BoardBuilder.builder().build();
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @Test(description = "Delete Board")
    @Description("PJ2024-39")
    public void deleteBoardTest() {
        newBoardId = apiBoardClient.createNewBoard(secondBoardBody, 200).getId();

        refresh();

        List<String> allBoardsList;
        List<String> allBoardsListAfterDeleting;
        allBoardsList = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();
        int allBoardsCount = allBoardsList.size();

        softAssert.assertTrue(allBoardsCount > 0, "List is empty");

        apiBoardClient.deleteExistingBoard(newBoardId, 200);
        refresh();

        allBoardsListAfterDeleting = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();
        int allBoardsCountAfterDeleting = allBoardsListAfterDeleting.size();

        softAssert.assertEquals(allBoardsCount - allBoardsCountAfterDeleting, 1);
        softAssert.assertAll();
    }
}

