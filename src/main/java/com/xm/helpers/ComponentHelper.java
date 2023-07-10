package com.xm.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public interface ComponentHelper {

    WebDriver getDriver();

    default void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, -Math.max(document.documentElement.scrollHeight));");
    }

    default void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight));");
    }

    default void moveTo(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    default boolean isElementExists(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException elementException) {
            return false;
        }
    }

    default boolean isElementExists(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    default void switchFocusOnNewTab() {
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    default  void scrollUntilClickable(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        while (true) {
            try {
                if (element.isDisplayed() && element.isEnabled()) {
                    break;
                }
            } catch (NoSuchElementException e) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            }
        }
    }

    default void click(WebElement element){
        scrollUntilClickable(element);
        element.click();
    }
}

