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
    private String listId;
    private String labelName;

    @BeforeMethod
    public void createBoardAttachments() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        apiCardClient.createNewCard(cardBody, listId, 200);
    }

    @Test(description = "Create label in card")
    @Description("PJ2024-19")
    public void createLabelTest(){
         labelName =apiLabelClient.createNewLabel("LabelName", "red", boardId, 200).getName();

        String boardName = boardBody.getName();


        List<String> allLableTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        //не клікнула по карді,виправ. Перейшла на бурду , але не знаходишся на карді,щоб витягувати нейми лейблів. Втомилась

        allLableTitles = boardPage.getCardFragment().getLableTitles();

        softAssert.assertTrue(allLableTitles.stream().anyMatch(genre -> genre.equals(labelName)),
                "No such card with name: " + labelName);
    }
}
