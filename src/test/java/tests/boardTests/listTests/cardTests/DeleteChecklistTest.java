package tests.boardTests.listTests.cardTests;

import api.clients.ApiCardClient;
import api.clients.ApiChecklistClient;
import api.clients.ApiListClient;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ChecklistBuilder;
import api.pojo.requests.ListBuilder;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class DeleteChecklistTest extends TestInit {
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private String checklistId, listId, cardId;
    private static final BoardPage boardPage = new BoardPage();
    private final ApiChecklistClient apiChecklistClient = new ApiChecklistClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private static final ChecklistBuilder checklistBody = ChecklistBuilder.builder().build();
    private static final ChecklistBuilder secondChecklistBody = ChecklistBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();

    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }


    @Test(description = "Delete a Checklist")
    @Description("PJ2024-48")
    public void deleteChecklist() {
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        String boardName = boardBody.getName();
        List<String> allCheckListsTitles;
        String checklistName = checklistBody.getName();
        String secondChecklistName = secondChecklistBody.getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName, cardName).click();
        checklistId = apiChecklistClient.createNewChecklist(checklistBody, cardId, 200).getId();
        apiChecklistClient.createNewChecklist(secondChecklistBody, cardId, 200);
        refresh();

        apiChecklistClient.deleteChecklist(checklistId, 200);
        refresh();

        allCheckListsTitles = boardPage.getCardFragment().getChecklistTitles();
        softAssert.assertFalse(allCheckListsTitles.contains(checklistName),
                "Checklist is still present after deletion");

        softAssert.assertTrue(allCheckListsTitles.contains(secondChecklistName),
                "Second checklist is not present after deletion of the first checklist");
        softAssert.assertAll();
    }
}
