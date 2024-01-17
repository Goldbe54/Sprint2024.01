package tests.boardTests;

import api.pojo.requests.BoardBuilder;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;
import static java.net.HttpURLConnection.HTTP_OK;

public class DeleteBoardTest extends TestInit {
    private String newBoardId;
    private static final BoardBuilder secondBoardBody = BoardBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @Test(description = "Delete Board")
    @Description("PJ2024-39")
    public void deleteBoardTest() {
        newBoardId = apiBoardClient.createNewBoard(secondBoardBody, HTTP_OK).getId();

        refresh();

        List<String> allBoardsList;
        List<String> allBoardsListAfterDeleting;
        allBoardsList = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();
        int allBoardsCount = allBoardsList.size();

        softAssert.assertTrue(allBoardsCount > 0, "List is empty");

        apiBoardClient.deleteExistingBoard(newBoardId, HTTP_OK);
        refresh();

        allBoardsListAfterDeleting = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();
        int allBoardsCountAfterDeleting = allBoardsListAfterDeleting.size();

        softAssert.assertEquals(allBoardsCount - allBoardsCountAfterDeleting, 1);
        softAssert.assertAll();
    }
}

