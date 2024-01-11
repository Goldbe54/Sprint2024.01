package tests.boardTests.listTests;

import api.clients.ApiBoardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.ListBuilder;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

public class MovingCardsBetweenLists extends TestInit {

    private final SoftAssert softAssert = new SoftAssert();
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBuilder = ListBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();
    private String boardId;
    private String listId;


    @BeforeMethod
    public void setup() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listId = apiListClient.createNewList(listBuilder, boardId, 200).getName();

    }

    @Test (description = "PJ2024-38")
    @Description("Moving cards between Lists")
    public void movingCardsBetweenLists(){

    }
}
