package tests.boardTests.listTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;
import utils.ElementUtil;

import java.util.List;

public class EditListAtTheBoard extends TestInit {

    private String boardId;
    private String listId;
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @BeforeMethod
    public void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
    }

    @Test(description = "3.4 Edit list at the board")
    @Description("PJ2024-36")
    public void checkTheAvailabilityAllElements() {

        String boardName = boardBody.getName();
        String cardName = cardBody.getName();
        List<String> allListTitles;
        List<String> allCardsTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        String updatedListName = apiListClient.updateList(listId, "Updated List", 200).getName();
        refreshPage();

        ElementsCollection allListsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllListTitles();
        allListTitles = ElementUtil.getListOfStrings(allListsTitlesElements);

        apiCardClient.createNewCard(cardBody, listId, 200).getId();

        ElementsCollection allCardsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllCardsTitlesInList(updatedListName);
        allCardsTitles = ElementUtil.getListOfStrings(allCardsTitlesElements);

        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(updatedListName)));
        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)));
    }
}