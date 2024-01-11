package tests.boardTests;

import api.clients.ApiBoardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.ListBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

public class CheckTheAvailabilityAllElements extends TestInit {
    private String boardId;
    private String listName;
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBuilder = ListBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @BeforeMethod
    public void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listName = apiListClient.createNewList(listBuilder, boardId, 200).getName();
    }


    @Test(description = "PJ2024-28")
    @Description("2.2 Check the availability and correctness of the main elements of the interface (board, lists, cards)")
    public void checkTheAvailabilityAllElements(){

    }

}
