package tests.boardTests.listTests;

import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

public class EditListAtTheBoard extends TestInit {

    private String listId;
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    List<String> allListTitles;
    List<String> allCardsTitles;

    @BeforeMethod
    public void createList() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
    }

    @Test(description = "3.4 Edit list at the board")
    @Description("PJ2024-36")
    public void editListAtTheBoard() {

        String boardName = boardBody.getName();
        String cardName = cardBody.getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        String updatedListName = apiListClient.renameList(listId, "Updated List", 200).getName();
        refreshPage();
        allListTitles = boardPage.getBoardWorkSpaceFragment().getListTitles();

        apiCardClient.createNewCard(cardBody, listId, 200);

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(updatedListName);

        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(updatedListName)), "List name is not updated");
        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)), "Card is not created");
    }
}
