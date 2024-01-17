package tests.boardTests.listTests.cardTests;

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

public class EditCardTest extends TestInit {
    private final BoardPage boardPage = new BoardPage();
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private String listId, cardId;
    List<String> allCardsTitles;
    @BeforeMethod
    public void createCard() {
        listId = apiListClient.createNewList(listBody,boardId,200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }

    @Test(description = "3.5 Edit card at the board")
    @Description("PJ2024-32")
    public void editCardTest() {
        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String newCardName  = "Updated Name";
        String newCardDesc  = "Updated description";

        CardBuilder cardBody = CardBuilder.builder().name(newCardName).desc(newCardDesc).build();

        apiCardClient.editCard(cardId, cardBody, 200);
        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(listName);

        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName,newCardName).click();

        String checkedCardDesc = boardPage.getCardFragment().getCardDescription().getText();

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(title -> title.equals(newCardName)),
                "Card name was not updated correctly");
        softAssert.assertEquals(newCardDesc , checkedCardDesc, "Card description was not updated correctly");
        softAssert.assertAll();
    }
}
