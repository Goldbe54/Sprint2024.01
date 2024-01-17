package tests.boardTests.listTests.cardTests;

import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.AttachmentBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.CommentOnTheCardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.BoardPage;
import ui.pages.TrelloHomePage;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;
import static java.net.HttpURLConnection.HTTP_OK;

public class CardTests extends TestInit {
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);
    private final ListBuilder listBody = ListBuilder.builder().build();
    private final CardBuilder cardBody = CardBuilder.builder().build();
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final BoardPage boardPage = new BoardPage();
    private final SoftAssert softAssert = new SoftAssert();

    private String listId;
    private String idCard;

    @BeforeMethod
    private void setUp() {
        listId = apiListClient.createNewList(listBody, boardId, HTTP_OK).getId();
    }

    @Test(description = "Add a new card to the list")
    @Description("PJ2024-12")
    private void createCardTest() {
        apiCardClient.createNewCard(cardBody, listId, HTTP_OK);
        refresh();

        String boardName = boardBody.getName();
        String listName = listBody.getName();
        String cardName = cardBody.getName();
        List<String> allCardsTitles;

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();

        ElementsCollection allCardsTitlesElements = boardPage.getBoardWorkSpaceFragment().getAllCardsTitlesInList(listName);
        allCardsTitles = ElementUtil.getListOfStrings(allCardsTitlesElements);

        softAssert.assertTrue(allCardsTitles.stream().anyMatch(genre -> genre.equals(cardName)));
    }

    @Test(description = "Positive: Adding comments to cards")
    @Description("PJ2024-17")
    public void addCommentToTheCard() {
        CommentOnTheCardBuilder commentOnTheCardBuilder = CommentOnTheCardBuilder.builder().build();
        String initialCommentOnTheCard = commentOnTheCardBuilder.getText();

        idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();
        apiCardClient.createCommentOnTheCard(commentOnTheCardBuilder, idCard, HTTP_OK);

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        softAssert.assertEquals(initialCommentOnTheCard, boardPage.getCardFragment().getCommentOnTheCard().getText(), "Comments are different");
    }

    @Test(description = "Positive: Adding attachment to the cart")
    @Description("PJ2024-51")
    private void addAttachmentOnCard() {
        AttachmentBuilder attachmentBody = AttachmentBuilder.builder().build();

        idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();
        String nameInitialAttachment = apiCardClient.createAttachmentOnCard(attachmentBody, idCard, HTTP_OK).getName();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).click();

        softAssert.assertTrue(String.valueOf(boardPage.getCardFragment().getSelectedAttachment(attachmentBody.getName()))
                .contains(nameInitialAttachment), "Attachment doesn't exist");
    }

    @Test(description = "Moving cards between Lists")
    @Description("PJ2024-38")
    public void movingCardsBetweenLists() {
        ListBuilder customBodyList = ListBuilder.builder().name("List").build();

        idCard = apiCardClient.createNewCard(cardBody, listId, HTTP_OK).getId();

        String targetListId = apiListClient.createNewList(customBodyList, boardId, HTTP_OK).getId();

        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardBody.getName()).click();
        boolean enableCardOnInitialList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(listBody.getName(), cardBody.getName()).isDisplayed();

        softAssert.assertTrue(enableCardOnInitialList);

        apiCardClient.moveCardsToAnotherList(idCard, targetListId, HTTP_OK);

        boolean enableCardOnTargetList = boardPage.getBoardWorkSpaceFragment().getSpecificCardTitleInList(customBodyList.getName(), cardBody.getName()).isDisplayed();
        softAssert.assertTrue(enableCardOnTargetList);
    }
}