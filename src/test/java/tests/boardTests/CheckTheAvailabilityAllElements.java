package tests.boardTests;

import api.clients.ApiCardClient;
import api.clients.ApiListClient;
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

public class CheckTheAvailabilityAllElements extends TestInit {

    private String listId;
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @BeforeMethod
    public void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }

    @Test(description = "2.2 Check the availability and correctness of the main elements of the interface (board, lists, cards)")
    @Description("PJ2024-28")
    public void checkTheAvailabilityAllElements() {
        refreshPage();

        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        List<String> allListTitles;
        List<String> allCardsTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        ElementsCollection allCardsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllCardsTitlesInList(listName);
        allCardsTitles = ElementUtil.getListOfStrings(allCardsTitlesElements);

        ElementsCollection allListsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllListTitles();
        allListTitles = ElementUtil.getListOfStrings(allListsTitlesElements);

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)));
        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(listName)));
    }
}