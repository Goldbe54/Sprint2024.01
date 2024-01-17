package tests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import ui.steps.PreLoginSteps;

import static com.codeborne.selenide.Selenide.open;
import static utils.ConfigProvider.EMAIL;
import static utils.ConfigProvider.PASSWORD;

@Listeners(listeners.TestListener.class)
public class TestInit {
    protected final String BASE_URL = "https://api.trello.com";
    PreLoginSteps preLoginSteps = new PreLoginSteps();
    protected final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    protected final BoardBuilder boardBody = BoardBuilder.builder().build();
    protected String boardId;

    @Step("Preparing a browser for the test")
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = BASE_URL;
        Configuration.reportsFolder = "./target";
        Configuration.downloadsFolder = "./target";
        Configuration.screenshots = false;
        Configuration.holdBrowserOpen = true;

        open("/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        preLoginSteps.loginViaEmail(EMAIL, PASSWORD);
        boardId = apiBoardClient.createNewBoard(boardBody, 200).getId();
    }

//    @AfterMethod
//    public void closeBrowser() {
//        apiBoardClient.deleteExistingBoard(boardId, 200);
//        WebDriverRunner.getWebDriver().quit();
//    }
}