package listeners;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    @SneakyThrows
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] captureScreen() {
        File screenshot = Screenshots.takeScreenShotAsFile();
        FileUtils.copyFile(screenshot, new File("./target/allure-results//" + screenshot.getName()));
        System.out.println("Screenshot taken:" + screenshot.getName());
        return Files.toByteArray(screenshot);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Failed because of - " + result.getThrowable());
        System.out.println("---------------------------------------------------------------");
        captureScreen();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Skipped because of - " + result.getThrowable());
        System.out.println("---------------------------------------------------------------");
        captureScreen();
    }
}
