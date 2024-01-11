package tests.uiCheckTests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.TestInit;

public class CheckTheAvailabilityOfTheMainElements extends TestInit {
    private ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    private BoardBuilder boardBody;
    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    private void setUp() {
        boardBody = BoardBuilder.builder().build();
    }

    @Test(description = "PJ2024-35")
    @Description("TC Checking the creation of a new board")
    public void test() {
        apiBoardClient.createNewBoard(boardBody);
        Selenide.refresh();

    }

}
