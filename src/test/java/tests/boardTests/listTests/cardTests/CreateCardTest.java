package tests.boardTests.listTests.cardTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;
import utils.ElementUtil;

import java.util.List;

public class CreateCardTest extends TestInit {
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();

    private String boardId;
    private String listId;

    @BeforeMethod
    private void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
    }

    @Test(description = "PJ2024-12")
    @Description("Add a new card to the list")
    private void createCardTest() {
        apiCardClient.createNewCard(cardBody, listId, 200);
        refreshPage();

        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        List<String> allCardsTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        ElementsCollection allCardsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllCardsTitlesInList(listName);
        allCardsTitles = ElementUtil.getListOfStrings(allCardsTitlesElements);

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)));
    }

    @AfterMethod
    private void deleteBoard() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}
