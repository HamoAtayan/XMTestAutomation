package com.xm.pages;

import com.xm.helpers.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage<HomePage> {

    @FindBy(css = ".main_nav_research")
    private WebElement researchAndEducation;

    @FindBy(xpath = "(//a[@href='https://www.xm.com/research/economicCalendar'])[1]")
    private WebElement economicCalendarTextBtn;

    public void clickOnResearchAndEducationLinkText() {
        WaitHelper.waitForElementToBeClickable(getDriver(), researchAndEducation, Duration.ofSeconds(10));
        researchAndEducation.click();
    }

    public void clickOnAcceptAllButton(){
        String acceptAll = "//button[text() = 'ACCEPT ALL']";
        if (isElementExists(By.xpath(acceptAll))){
            getDriver().findElement(By.xpath(acceptAll)).click();
        }
    }

    public HomePage(WebDriver driver) {
        super(driver, "");
    }

    @Override
    public boolean isOnPage() {
        return isElementExists(researchAndEducation);
    }

    public void waitUntilPageLoads() {
        WaitHelper.waitForVisibility(getDriver(), researchAndEducation, Duration.ofSeconds(10));
    }

    public void clickOneEconomicCalendarTextBtn(){
        WaitHelper.waitForElementToBeClickable(getDriver(),economicCalendarTextBtn,Duration.ofSeconds(5));
        economicCalendarTextBtn.click();
    }
}
