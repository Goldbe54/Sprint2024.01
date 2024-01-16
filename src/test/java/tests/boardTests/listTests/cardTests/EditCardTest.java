package tests.boardTests.listTests.cardTests;

import api.clients.ApiCardClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import api.pojo.responses.CardResponse;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.TrelloHomePage;
import ui.pages.BoardPage;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.refresh;

public class EditCardTest extends TestInit {
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private String listId;

    @BeforeMethod
    public void createCard() {
        listId = apiCardClient.createNewCard(cardBody, boardId, 200).getId();
    }

    @Test(description = "3.5 Edit card at the board")
    @Description("PJ2024-32")
    public void EditCard() {
        String originalCardName = cardBody.getName();
        CardResponse createdCard = apiCardClient.createNewCard(cardBody, listId, 200);
        String createdCardId = createdCard.getId();
        String newCardName = "Updated " + originalCardName;
        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), newCardName).shouldBe(Condition.visible);

        softAssert.assertEquals(cardBody.getName(), newCardName, "Card name was not updated correctly");
        softAssert.assertNotNull(createdCardId, "Failed to create a new card");
        softAssert.assertAll();
    }
}