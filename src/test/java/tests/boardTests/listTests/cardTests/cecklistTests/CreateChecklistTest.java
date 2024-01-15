package tests.boardTests.listTests.cardTests.cecklistTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiChecklistClient;
import api.clients.ApiListClient;
import api.pojo.requests.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class CreateChecklistTest extends TestInit {
    private final SoftAssert softAssert = new SoftAssert();
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiChecklistClient apiChecklistClient = new ApiChecklistClient(BASE_URL);
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final CheckitemBuilder checkitemBody = CheckitemBuilder.builder().build();
    private final ChecklistBuilder checklistBody = ChecklistBuilder.builder().build();
    private String boardId, listId, cardId, checklistId;

    @BeforeMethod
    private void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
        checklistId = apiChecklistClient.createNewChecklist(checklistBody, cardId, 200).getId();
        apiChecklistClient.createNewCheckitem(checkitemBody, checklistId, 200);
    }

    @Test(description = "TC Create a Checklist with a Checkitem")
    @Description("PJ2024-46")
    public void CreateChecklistTest() {
        List<String> checklistsTitles;
        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        String checklistName = checklistBody.getName();
        String checkitemName = checkitemBody.getName();

        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName,cardName).click();

        checklistsTitles = boardPage.getCardFragment().getListCheckitemsTitlesInChecklist(checklistName);

        softAssert.assertTrue(checklistsTitles.stream().anyMatch(genre -> genre.equals(checkitemName)),"No such checkitem in checklist");
    }
}
