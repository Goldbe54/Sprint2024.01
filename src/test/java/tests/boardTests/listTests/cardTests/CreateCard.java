package tests.boardTests.listTests.cardTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CommentOnTheCardBuilder;
import api.pojo.requests.ListBuilder;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;

import static org.testng.Assert.assertEquals;

public class CreateCard extends TestInit {

    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final CommentOnTheCardBuilder commentOnTheCardBuilder = CommentOnTheCardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();
    private final String initialCommentOnTheCard = commentOnTheCardBuilder.getText();

    private String boardId;
    private String listId;
    private String idCard;

    @BeforeMethod
    private void setUp() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
        idCard = apiCardClient.createNewCard(cardBody, listId, 200).getId();
    }

    @Test(description = "PJ2024-17")
    @Description("Positive: Adding comments to cards")
    public void addCommentToTheCard() {
        apiCardClient.createCommentOnTheCard(commentOnTheCardBuilder, idCard, 200);

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getAllCardsFromTheLists(cardBody.getName()).click();
        String receivedCommentFromTheCard = boardPage.getCardFragment().getCommentOnTheCard().getText();

        assertEquals(initialCommentOnTheCard, receivedCommentFromTheCard);
    }

    @AfterMethod
    private void shoutDown() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}