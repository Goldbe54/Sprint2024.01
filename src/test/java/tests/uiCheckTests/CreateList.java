package tests.uiCheckTests;

import api.clients.ApiBoardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.fragments.AllBoardsFragment;
import ui.fragments.BoardWorkSpace;
import utils.ElementUtil;

import java.util.List;

public class CreateList extends TestInit {

    private SoftAssert softAssert = new SoftAssert();
    private BoardBuilder boardBody = BoardBuilder.builder().build();
    private ListBuilder listBuilder = ListBuilder.builder().build();
    private ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private BoardWorkSpace boardWorkSpace = new BoardWorkSpace();
    private String boardId;


    @BeforeMethod
    public void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
    }

    @Test(description = "Verification that a new list is created and appears on the board")
    public void testListCreationOnBoard() {
        String listName = apiListClient.createNewList(listBuilder, boardId, 200).getName();
        String boardBodyName = boardBody.getName();

        Selenide.refresh();
        allBoardsFragment.specialBoardTitle(boardBodyName).click();
        ElementsCollection listTitlesElements = boardWorkSpace.allListTitles();
        List<String> listsNames = ElementUtil.getListOfStrings(listTitlesElements);

        softAssert.assertTrue(listsNames.stream().anyMatch(genre -> genre.equals(listName)));
        softAssert.assertAll();
    }

   @AfterMethod
   private void shoutDown() {
       apiBoardClient.deleteExistingBoard(boardId, 200);}
}
