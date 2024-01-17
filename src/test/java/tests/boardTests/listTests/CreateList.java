package tests.boardTests.listTests;

import api.clients.ApiListClient;
import api.pojo.requests.ListBuilder;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class CreateList extends TestInit {

    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private static final ListBuilder listBuilder = ListBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();

    @Test(description = "3.1  Add a new list to the board.")
    @Description("PJ2024-11")
    public void testListCreationOnBoard() {
        String listName = apiListClient.createNewList(listBuilder, boardId, 200).getName();
        String boardBodyName = boardBody.getName();

        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBodyName).click();

        List<String> listsNames = boardPage.getBoardWorkSpaceFragment().getListTitles();

        softAssert.assertTrue(listsNames.stream().anyMatch(genre -> genre.equals(listName)),
                "No such list with name: " + listName);
    }
}