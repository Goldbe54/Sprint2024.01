package tests.boardTests.listTests;

import api.clients.ApiBoardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class MoveListToBoard extends TestInit {

    private static final BoardPage boardPage = new BoardPage();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private static final BoardBuilder secondBoardBody = BoardBuilder.builder().build();
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static String secondBoardId, listId;

    @BeforeMethod
    public void createList() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        secondBoardId = apiBoardClient.createNewBoard(secondBoardBody, 200).getId();
    }


    @Test(description = "Move list to board")
    @Description("PJ2024-45")
    public void moveListToBoard() {

        String boardName = boardBody.getName();
        String secondBoardName = secondBoardBody.getName();
        String listName = listBody.getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        apiListClient.moveListInBoard(listId, secondBoardId, 200);
        refresh();

        List<String> allListTitles = boardPage.getBoardWorkSpaceFragment().getListTitles();

        softAssert.assertTrue(allListTitles.stream().noneMatch(genre -> genre.equals(listName)), "List is not moved");
        Selenide.back();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(secondBoardName).click();

        allListTitles = boardPage.getBoardWorkSpaceFragment().getListTitles();

        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(listName)), "List is not moved");

    }

    @AfterMethod
    public void closeBrowser() {
        apiBoardClient.deleteExistingBoard(secondBoardId, 200);
    }
}
