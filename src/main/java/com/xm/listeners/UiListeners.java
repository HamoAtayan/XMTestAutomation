package com.xm.listeners;

import com.xm.helpers.DriverHelper;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UiListeners implements ITestListener {

    private final Logger log = LoggerFactory.getLogger(UiListeners.class);

    private WebDriver driver;
    private static final String SCREENSHOT_FOLDER = "target/screenshots/";

    @Override
    public void onTestStart(ITestResult result) {
        DriverHelper driverHelper = DriverHelper.getInstance();
        driver = driverHelper.initDriver();
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed: " + result.getName());
        takeScreenshot(result.getName());
        attachScreenshotToReport();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do nothing
    }

    @Override
    public void onStart(ITestContext context) {
        // Do nothing
    }

    @Override
    public void onFinish(ITestContext context) {
        // Do nothing
    }

    @Step("Take Screenshot")
    public void takeScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            saveScreenshot(screenshot, screenshotName);
        } catch (WebDriverException e) {
            log.info("Failed to take screenshot: " + e.getMessage());
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshotToReport() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void saveScreenshot(byte[] screenshot, String testName) {
        // Save screenshot to file system
        try {
            Path path = Paths.get(SCREENSHOT_FOLDER, testName + ".png");
            Files.createDirectories(path.getParent());
            Files.write(path, screenshot);
            log.info("Screenshot saved to: " + path);
        } catch (Exception e) {
            log.info("Failed to save screenshot: " + e.getMessage());
        }
    }

}
