package tests.uiCheckTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.ArrayList;
import java.util.List;

public class TemplateCreateBoardTestResponse extends TestInit {

    private ApiBoardClient apiBoardClient;
    private BoardBuilder boardBody;
    private TrelloHomePage trelloHomePage;
    private SoftAssert softAssert;

    @BeforeMethod
    private void setUp() {
        apiBoardClient = new ApiBoardClient(BASE_URL);
        trelloHomePage = new TrelloHomePage();
        softAssert = new SoftAssert();
        boardBody = BoardBuilder.builder().build();
    }

    @Test
    public void test() {
        BoardResponse response = apiBoardClient.createNewBoard(boardBody);
        List<String> boardsTitles = new ArrayList<>();

        Selenide.refresh();

        boardsTitles = trelloHomePage.getAllYourBoardsTitles();

        softAssert.assertEquals(response.getName(),boardBody.getName());
        softAssert.assertTrue(boardsTitles.stream().anyMatch(genre -> genre.equals(response.getName())));

        softAssert.assertAll();
    }
}
