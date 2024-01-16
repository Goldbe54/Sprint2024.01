package tests.boardTests;

import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class CheckTheAvailabilityAllElements extends TestInit {

    private String listId;
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);

    @BeforeMethod
    public void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        apiCardClient.createNewCard(cardBody, listId, 200);
    }

    @Test(description = "2.2 Check the availability and correctness of the main elements of the interface (board, lists, cards)")
    @Description("PJ2024-28")
    public void checkTheAvailabilityAllElements() {
        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        List<String> allListTitles;
        List<String> allCardsTitles;

        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(listName);
        allListTitles = boardPage.getBoardWorkSpaceFragment().getListTitles();

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)),
                "No such card with name: " + cardName);
        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(listName)),
                "No such list with name :" + listName);
    }
}