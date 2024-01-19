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
    private static CardBuilder cardBody = CardBuilder.builder().build();
    private static final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private static final BoardPage boardPage = new BoardPage();
    private String listId;

    List<String> allCardsTitles;

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
        String cardName = cardBody.getName();
        String listName = listBody.getName();
        String customListName = customBodyList.getName();
        String idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();
        String targetListId = apiListClient.createNewList(customBodyList, boardId, HTTP_OK).getId();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();

        boolean enableCardOnInitialList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName, cardName).isDisplayed();

        apiCardClient.moveCardsToAnotherList(idCard, targetListId, HTTP_OK);

        boolean enableCardOnTargetList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(customListName, cardName).isDisplayed();

        softAssert.assertTrue(enableCardOnInitialList, "The card with name: " + cardName + "does not exist in this list with name " + listName);
        softAssert.assertTrue(enableCardOnTargetList, "The card with name: " + cardName + "does not exist in this list with name " + customListName);
    }

    @Test(description = "3.5 Edit card at the board")
    @Description("PJ2024-32")
    public void editCardTest() {
        String cardId = apiCardClient.createNewCard(cardBody, listId, 200).getId();

        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String newCardName = "Updated Name";
        String newCardDesc = "Updated description";

        CardBuilder cardBody = CardBuilder.builder().name(newCardName).desc(newCardDesc).build();

        apiCardClient.updateCard(cardId, cardBody, 200);
        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        allCardsTitles = boardPage.getBoardWorkSpaceFragment().getCardTitles(listName);

        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listName, newCardName).click();

        String checkedCardDesc = boardPage.getCardFragment().getCardDescription().getText();

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(title -> title.equals(newCardName)),
                "Card name was not updated correctly");
        softAssert.assertEquals(newCardDesc, checkedCardDesc, "Card description was not updated correctly");
    }

    @Test(description = "Checking the card archiving")
    @Description("PJ2024-53")
    public void archiveTheCardTest() {
        String idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();
        cardBody = CardBuilder.builder().closed(true).build();
        String cardName = cardBody.getName();
        List<String> archivedTitles;

        apiCardClient.updateCard(idCard, cardBody, HTTP_OK);
        refresh();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();

        archivedTitles = boardPage.getListOfAllArchivedTitles();

        softAssert.assertTrue(archivedTitles.stream().anyMatch(title -> title.equals(cardName)),
                "The card with name: " + cardName + "does not exist in archived list");
    }

    @Test(description = " 6.1. Search by the content of boards and cards")
    @Description("PJ2024-20")
    public void searchByContent() {
        String boardName = boardBody.getName();
        String cardName = cardBody.getName();
        List<String> searchBoardResult, searchCardResult;

        apiCardClient.createNewCard(cardBody,listId,HTTP_OK);
        refresh();
        trelloHomePage.getTrelloHomeHeaderFragment().getSearchField().sendKeys(boardName);

        searchBoardResult = trelloHomePage.getTrelloHomeHeaderFragment().getListOfSearchResultTitles();

        trelloHomePage.getTrelloHomeHeaderFragment().getSearchField().clear();
        trelloHomePage.getTrelloHomeHeaderFragment().getSearchField().sendKeys(cardName);

        searchCardResult = trelloHomePage.getTrelloHomeHeaderFragment().getListOfSearchResultTitles();

        softAssert.assertTrue(searchBoardResult.stream().anyMatch(genre -> genre.equals(boardName))
                ,"No such result with name: " + boardName);
        softAssert.assertTrue(searchCardResult.stream().anyMatch(genre -> genre.equals(cardName))
                ,"No such result with name: " + cardName);
    }
}