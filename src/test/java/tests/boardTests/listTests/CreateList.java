package tests.boardTests.listTests;

import api.clients.ApiListClient;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.ElementsCollection;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class CreateList extends TestInit {

    private final SoftAssert softAssert = new SoftAssert();
    private final ListBuilder listBuilder = ListBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();

    @Test(description = "3.1  Add a new list to the board.")
    @Description("PJ2024-11")
    public void testListCreationOnBoard() {
        String listName = apiListClient.createNewList(listBuilder, boardId, 200).getName();
        String boardBodyName = boardBody.getName();

        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBodyName).click();
        ElementsCollection listTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllListTitles();
        List<String> listsNames = ElementUtil.getListOfStrings(listTitlesElements);

        softAssert.assertTrue(listsNames.stream().anyMatch(genre -> genre.equals(listName)), "Lists aren't created");
        softAssert.assertAll();
    }
}