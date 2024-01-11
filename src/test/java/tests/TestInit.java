package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.*;
import ui.steps.PreLoginSteps;

import static com.codeborne.selenide.Selenide.open;
import static utils.ConfigProvider.*;

@Listeners(listeners.TestListener.class)
public class TestInit {
    protected final String BASE_URL = "https://api.trello.com";
    PreLoginSteps preLoginSteps = new PreLoginSteps();

    @Step("Preparing a browser for the test")
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = BASE_URL;
        Configuration.reportsFolder = "./target";
        Configuration.downloadsFolder = "./target";
        Configuration.screenshots = false;

        open("/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        preLoginSteps.loginViaEmail(EMAIL,PASSWORD);
    }

    protected static void refreshPage() {
        Selenide.refresh();
    }

    @AfterMethod
    public void closeBrowser(){
        WebDriverRunner.getWebDriver().quit();
    }
}