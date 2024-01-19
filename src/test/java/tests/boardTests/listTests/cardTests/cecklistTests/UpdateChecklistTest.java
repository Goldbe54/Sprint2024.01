package tests.boardTests.listTests.cardTests.cecklistTests;

import api.clients.ApiCardClient;
import api.clients.ApiChecklistClient;
import api.clients.ApiListClient;
import api.pojo.requests.CardBuilder;
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

public class UpdateChecklistTest extends TestInit {
    private final ApiChecklistClient apiChecklistClient = new ApiChecklistClient(BASE_URL);
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private static final BoardPage boardPage = new BoardPage();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static ChecklistBuilder checklistBody = ChecklistBuilder.builder().build();
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private String checklistId, listId, cardId;


    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }


    @Test(description = "TC Update a Checklist")
    @Description("PJ2024-47")
    public void updateChecklistTest() {
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        String boardName = boardBody.getName();
        String newChecklistName = "Updated Name";
        List<String> allCheckListsTitles;

        checklistId = apiChecklistClient.createNewChecklist(checklistBody, cardId, 200).getId();
        checklistBody = ChecklistBuilder.builder().name(newChecklistName).build();

        apiChecklistClient.updateChecklist(checklistId, newChecklistName, 200);
        refresh();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName, cardName).click();

        allCheckListsTitles = boardPage.getCardFragment().getChecklistTitles();

        softAssert.assertTrue(allCheckListsTitles.contains(newChecklistName),
                "Updated checklist name is not present. Expected name: " + newChecklistName);
    }
}
