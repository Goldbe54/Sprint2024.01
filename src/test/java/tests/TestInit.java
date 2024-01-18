package tests;

import api.clients.ApiBoardClient;
import api.pojo.requests.BoardBuilder;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import ui.steps.PreLoginSteps;
import utils.SuiteConfiguration;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static java.net.HttpURLConnection.HTTP_OK;
import static utils.ConfigProvider.EMAIL;
import static utils.ConfigProvider.PASSWORD;

@Listeners(listeners.TestListener.class)
public class TestInit {
    protected final String BASE_URL = "https://api.trello.com";
    PreLoginSteps preLoginSteps = new PreLoginSteps();
    protected final ApiBoardClient apiBoardClient = new ApiBoardClient(BASE_URL);
    protected final BoardBuilder boardBody = BoardBuilder.builder().build();
    protected final SoftAssert softAssert = new SoftAssert();

    protected String boardId;

    @Step("Preparing a browser for the test")
    @BeforeMethod
    public void setup() throws IOException {
        SuiteConfiguration conf = new SuiteConfiguration();

        Configuration.browser = conf.getBrowserProperty();
        Configuration.baseUrl = BASE_URL;
        Configuration.reportsFolder = "./target";
        Configuration.downloadsFolder = "./target";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        open("/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        preLoginSteps.loginViaEmail(EMAIL, PASSWORD);
        boardId = apiBoardClient.createNewBoard(boardBody, HTTP_OK).getId();
    }

    @AfterMethod
    public void closeBrowser() {
        softAssert.assertAll();
        apiBoardClient.deleteExistingBoard(boardId, HTTP_OK);
        WebDriverRunner.getWebDriver().quit();
    }
}