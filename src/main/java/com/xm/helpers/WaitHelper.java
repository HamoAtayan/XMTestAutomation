package com.xm.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitHelper {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    public static void waitForVisibility(WebDriver driver, WebElement element) {
        waitForVisibility(driver, element, TIMEOUT);
    }

    public static void waitForVisibility(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibility(WebDriver driver, By locator, Duration timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForVisibility(WebDriver driver, By locator) {
        waitForVisibility(driver, locator, TIMEOUT);
    }

    public static void waitForElementTextToBe(WebDriver driver, WebElement element, String expectedText, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        waitForElementToBeClickable(driver, element, TIMEOUT);
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        waitForElementToBeClickable(driver, locator, TIMEOUT);
    }

    public static void waitUntilFrameToBeAvailable(WebDriver driver, By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    private WaitHelper() {
        throw new UnsupportedOperationException("Not allowed to crate an instance of WaitHelper class");
    }
}
