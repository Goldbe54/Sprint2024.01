package tests.boardTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import api.pojo.responses.BoardResponse;
import com.codeborne.selenide.ElementsCollection;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.TrelloHomePage;
import utils.ElementUtil;

import java.util.List;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.refresh;
import static org.testng.Assert.assertTrue;

public class EditBoardTest extends TestInit {

    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private TrelloHomePage trelloHomePage = new TrelloHomePage();
    private BoardResponse response;
    private String boardId;

    @BeforeMethod
    private void setUp() {
        boardBody = BoardBuilder.builder().build();
    }

    @Test(description = "PJ2024-13")
    @Description("User updated board name")
    public void userUpdateBoardName() {

        response = apiBoardClient.createNewBoard(boardBody, 200);
        boardId = response.getId();

        refresh();
        String boardName = boardBody.getName();
        trelloHomePage.getAllBoardsFragment().specialBoardTitle(boardName).click();
        String updatedName = apiBoardClient.updateBoard(boardId, "Updated name board", 200).getName();

        back();
        refresh();
        ElementsCollection boardsTitlesElements = trelloHomePage.getAllBoardsFragment().getAllBoardsInWorkspace();
        List<String> listsBoards = ElementUtil.getListOfStrings(boardsTitlesElements);

        assertTrue(listsBoards.stream().anyMatch(genre -> genre.equals(updatedName)));
    }

    @AfterMethod
    private void deleteBoard() {
        apiBoardClient.deleteExistingBoard(boardId, 200);
    }
}
