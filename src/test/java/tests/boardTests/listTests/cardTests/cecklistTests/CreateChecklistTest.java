package tests.boardTests.listTests.cardTests.cecklistTests;

import api.clients.ApiCardClient;
import api.clients.ApiChecklistClient;
import api.clients.ApiListClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CheckItemBuilder;
import api.pojo.requests.ChecklistBuilder;
import api.pojo.requests.ListBuilder;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class CreateChecklistTest extends TestInit {
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiChecklistClient apiChecklistClient = new ApiChecklistClient(BASE_URL);
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private static final CheckItemBuilder checkItemBody = CheckItemBuilder.builder().build();
    private static final ChecklistBuilder checklistBody = ChecklistBuilder.builder().build();
    private String listId, cardId;

    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }

    @Test(description = "TC Create a Checklist with a Checkitem")
    @Description("PJ2024-46")
    public void createChecklistTest() {
        List<String> checklistsTitles;
        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        String checklistName = checklistBody.getName();
        String checkitemName = checkItemBody.getName();
        String checklistId = apiChecklistClient.createNewChecklist(checklistBody, cardId, 200).getId();

        apiChecklistClient.createNewCheckitem(checkItemBody, checklistId, 200);

        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName,cardName).click();

        checklistsTitles = boardPage.getCardFragment().getListCheckitemsTitlesInChecklist(checklistName);

        softAssert.assertTrue(checklistsTitles.stream().anyMatch(genre -> genre.equals(checkitemName))
                ,"No such checkitem in checklist " + checklistName + ", with name: " + checkitemName);
    }
}
