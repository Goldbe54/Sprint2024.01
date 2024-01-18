package tests.boardTests.listTests.cardTests.labelsTests;

import api.clients.ApiCardClient;
import api.clients.ApiLabelClient;
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

public class CreateLabelTest extends TestInit {

    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ApiLabelClient apiLabelClient = new ApiLabelClient(BASE_URL);
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private String listId, labelId, cardId;
    private String labelName = "LabelName";

    @BeforeMethod
    public void createBoardAttachments() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }

    @Test(description = "Create label in card")
    @Description("PJ2024-19")
    public void createLabelTest() {
        labelId = apiLabelClient.createNewLabel(labelName, "red", boardId, 200).getId();
        apiLabelClient.addLabelToCard(cardId, labelId, 200);

        String boardName = boardBody.getName();
        List<String> allLabelTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        allLabelTitles = boardPage.getCardFragment().getLabelTitles();

        softAssert.assertTrue(allLabelTitles.stream().anyMatch(genre -> genre.equals(labelName)),
                "No such label with name: " + labelName);
    }
}