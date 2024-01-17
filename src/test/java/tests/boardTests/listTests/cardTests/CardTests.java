package tests.boardTests.listTests.cardTests;

import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.AttachmentBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CommentOnTheCardBuilder;
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

public class CardTests extends TestInit {
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private String listId;

    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, HTTP_OK).getId();
    }

    @Test(description = "Add a new card to the list")
    @Description("PJ2024-12")
    public void createCardTest() {
        apiCardClient.createNewCard(cardBody, listId, HTTP_OK);
        refresh();

        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        List<String> allCardsTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(listName);

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)),
                "No such card with name: " + cardName);
    }

    @Test(description = "Positive: Adding comments to cards")
    @Description("PJ2024-17")
    public void addCommentToTheCard() {
        CommentOnTheCardBuilder commentOnTheCardBuilder = CommentOnTheCardBuilder.builder().build();
        String initialCommentOnTheCard = commentOnTheCardBuilder.getText();
        String idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();

        apiCardClient.createCommentOnTheCard(commentOnTheCardBuilder, idCard, HTTP_OK);
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        String commentText = boardPage.getCardFragment().getCommentOnTheCard().getText();

        softAssert.assertEquals(initialCommentOnTheCard, commentText,
                "Expected comment text: " + initialCommentOnTheCard + ", but was: " + commentText);
    }

    @Test(description = "Positive: Adding attachment to the cart")
    @Description("PJ2024-51")
    public void addAttachmentOnCard() {
        AttachmentBuilder attachmentBody = AttachmentBuilder.builder().build();

        String idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();
        String nameInitialAttachment = apiCardClient.createAttachmentOnCard(attachmentBody, idCard, HTTP_OK).getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        String attachmentName = String.valueOf(boardPage.getCardFragment().getSelectedAttachment(attachmentBody.getName()));

        softAssert.assertTrue(attachmentName
                .contains(nameInitialAttachment), "No such attachment with name: " + attachmentName);
    }

    @Test(description = "Moving cards between Lists")
    @Description("PJ2024-38")
    public void movingCardsBetweenLists() {
        ListBuilder customBodyList = ListBuilder.builder().name("List").build();

        String idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();

        String targetListId = apiListClient.createNewList(customBodyList, boardId, HTTP_OK).getId();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boolean enableCardOnInitialList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).isDisplayed();

        softAssert.assertTrue(enableCardOnInitialList, "The card does not exist in this list");

        apiCardClient.moveCardsToAnotherList(idCard, targetListId, HTTP_OK);

        boolean enableCardOnTargetList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(customBodyList.getName(), cardBody.getName()).isDisplayed();
        softAssert.assertTrue(enableCardOnTargetList, "The card does not exist in this list");
    }
}