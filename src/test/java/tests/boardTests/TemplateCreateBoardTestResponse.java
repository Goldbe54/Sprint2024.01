package tests.boardTests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.TestInit;
import ui.pages.TrelloHomePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;

public class TemplateCreateBoardTestResponse extends TestInit {

    private final TrelloHomePage trelloHomePage = new TrelloHomePage();

    @Test(description = "TC Checking the creation of a new board")
    @Description("PJ2024-35")
    public void test() {
        String boardName = boardBody.getName();
        List<String> boardsTitles;

        refresh();

        boardsTitles = trelloHomePage.getAllBoardsFragment().getAllYourBoardsTitles();

        softAssert.assertTrue(boardsTitles.stream().anyMatch(genre -> genre.equals(boardName)),
                "No such board with name: " + boardName);
    }
}