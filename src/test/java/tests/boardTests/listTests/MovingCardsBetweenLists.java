package tests.boardTests.listTests;

import api.clients.ApiBoardClient;
import api.clients.ApiCardClient;
import api.clients.ApiListClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.requests.CardBuilder;
import api.pojo.requests.ListBuilder;
import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;

public class MovingCardsBetweenLists extends TestInit {

    private final SoftAssert softAssert = new SoftAssert();
    private final BoardBuilder boardBody = BoardBuilder.builder().build();
    private final ListBuilder listBuilder = ListBuilder.builder().build();
    private final CardBuilder cardBuilder = CardBuilder.builder().build();
    private final ApiListClient apiListClient = new ApiListClient(BASE_URL);
    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final ApiCardClient apiCardClient = new ApiCardClient(BASE_URL);

    private String boardId;
    private String idListSource;
    private String idListTarget;
    private String idCardFromListSource;
    private String idCardFromListTarget;


    @BeforeMethod
    public void setup() {
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();

        idListSource = apiListClient.createNewList(listBuilder, boardId, 200).getId();
        idListTarget = apiListClient.createNewList(listBuilder, boardId, 200).getId();

        idCardFromListSource = apiCardClient.createNewCard(cardBuilder, idListSource, 200).getId();
        idCardFromListTarget = apiCardClient.createNewCard(cardBuilder, idListTarget, 200).getId();
    }

    @Test(description = "PJ2024-38")
    @Description("Moving cards between Lists")
    public void movingCardsBetweenLists() {
        Selenide.refresh();
        apiListClient.moveCardsToAnotherList(boardId, idListSource, idListTarget, 200);

        boolean isCardInTargetList = idListTarget.contains(idCardFromListSource);

        softAssert.assertTrue(isCardInTargetList, "Card is not in the target list");
    }

    @AfterMethod
    public void shoutDown() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}

