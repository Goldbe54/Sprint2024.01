package tests.boardTests.listTests;

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
import static java.net.HttpURLConnection.HTTP_OK;

public class EditListAtTheBoard extends TestInit {

    private String listId;
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);

    @BeforeMethod
    public void createList() {
        listId = apiListClient.createNewList(listBody, boardId, HTTP_OK).getId();
    }

    @Test(description = "3.4 Edit list at the board")
    @Description("PJ2024-36")
    public void editListAtTheBoard() {

        List<String> allListTitles;
        List<String> allCardsTitles;
        String boardName = boardBody.getName();
        String cardName = cardBody.getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        String updatedListName = apiListClient.renameList(listId, "Updated List", HTTP_OK).getName();
        refresh();
        allListTitles = boardPage.getBoardWorkSpaceFragment().getListTitles();

        apiCardClient.createNewCard(cardBody, listId, HTTP_OK);

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(updatedListName);

        softAssert.assertTrue(allListTitles.stream().anyMatch(genre -> genre.equals(updatedListName)),
                "List name is not updated");
        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)),
                "No such card with name: " + cardName);
    }
}
