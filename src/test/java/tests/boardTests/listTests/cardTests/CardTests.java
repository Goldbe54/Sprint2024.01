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

public class CardTests extends TestInit {
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private static final ListBuilder listBody = ListBuilder.builder().build();
    private static final CardBuilder cardBody = CardBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private String listId, cardId;

    List<String> allCardsTitles;

    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, 200).getId();
    }

    @Test(description = "Add a new card to the list")
    @Description("PJ2024-12")
    public void createCardTest() {
        apiCardClient.createNewCard(cardBody, listId, 200);
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
        String idCard = apiCardClient.createNewCard(cardBody, listId, 200).getId();

        apiCardClient.createCommentOnTheCard(commentOnTheCardBuilder, idCard, 200);
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

        String idCard = apiCardClient.createNewCard(cardBody, listId, 200).getId();
        String nameInitialAttachment = apiCardClient.createAttachmentOnCard(attachmentBody, idCard, 200).getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        String attachmentName = String.valueOf(boardPage.getCardFragment().getSelectedAttachment(attachmentBody.getName()));

        softAssert.assertTrue(attachmentName
                .contains(nameInitialAttachment), "No such attachment with name: " + attachmentName);
    }

    @Test(description = "3.5 Edit card at the board")
    @Description("PJ2024-32")
    public void editCardTest() {
        String cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();

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
    }
}