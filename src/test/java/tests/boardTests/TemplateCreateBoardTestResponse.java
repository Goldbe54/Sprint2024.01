package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

public class TemplateCreateBoardTestResponse extends TestInit {

    private final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private final TrelloHomePage trelloHomePage = new TrelloHomePage();
    private final SoftAssert softAssert = new SoftAssert();
    private BoardResponse response;

    @Test(description = "TC Checking the creation of a new board")
    @Description("PJ2024-35")
    public void test() {
        response = apiBoardClient.createNewBoard(boardBody, 200);
        boardId = response.getId();

        refreshPage();

        List<String> boardsTitles;
        boardsTitles = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();

        softAssert.assertTrue(boardsTitles.stream().anyMatch(genre -> genre.equals(boardBody.getName())));
        softAssert.assertAll();
    }
}