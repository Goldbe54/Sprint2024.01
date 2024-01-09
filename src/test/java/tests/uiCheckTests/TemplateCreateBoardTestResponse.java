package tests.uiCheckTests;

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
import ui.fragments.AllBoardsFragment;

import java.util.ArrayList;
import java.util.List;

public class TemplateCreateBoardTestResponse extends TestInit {

    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private AllBoardsFragment allBoardsFragment = new AllBoardsFragment();
    private SoftAssert softAssert = new SoftAssert();
    BoardResponse response;
    private String boardId;

    @BeforeMethod
    private void setUp() {
        boardBody = BoardBuilder.builder().build();
    }

    @Test(description = "PJ2024-35")
    @Description("TC Checking the creation of a new board")
    public void test() {
        response = apiBoardClient.createNewBoard(boardBody, 200);
        boardId = response.getId();

        Selenide.refresh();

        List<String> boardsTitles = new ArrayList<>();
        boardsTitles = allBoardsFragment.getAllYourBoardsTitles();

        softAssert.assertTrue(boardsTitles.stream().anyMatch(genre -> genre.equals(boardBody.getName())));
        softAssert.assertAll();
    }

    @AfterMethod
    private void deleteBoard() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}
