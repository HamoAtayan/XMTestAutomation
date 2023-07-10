package com.xm.pages;

import com.xm.helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EconomicCalendarPage extends BasePage<EconomicCalendarPage> {
    private String dateElement = "//button[@aria-label='#']";

    @FindBy(css = ".tc-economic-calendar-item-header-left-title.tc-normal-text")
    private WebElement displayedSelectedDate;

    @FindBy(css = ".tc-economic-calendar-view-container-filter-n-list.tc-normal-background")
    private WebElement calendar;

    @FindBy(css = "[class='mat-focus-indicator mat-calendar-next-button mat-icon-button mat-button-base']")
    private WebElement calendarMonthNextButton;

    public EconomicCalendarPage(WebDriver driver) {
        super(driver, "/research/economicCalendar");
    }

    public void waitUntilPageLoads() {
        WaitHelper.waitUntilFrameToBeAvailable(getDriver(), By.cssSelector("[id='iFrameResizer0']"), 10);
        WaitHelper.waitForVisibility(getDriver(), By.xpath("//span[text()='Past Events']"));
    }

    public void clickOnDateOnCalendar(String date) {
        String date2 = dateElement.replace("#", date);
        WaitHelper.waitForElementToBeClickable(getDriver(), By.xpath(date2));
        moveTo(getDriver().findElement(By.id("mat-calendar-button-0")));
        getDriver().findElement(By.xpath((date2))).click();
        getDriver().findElement(By.xpath((date2))).click();
    }

    public String getDisplayedSelectedDate() {
        WaitHelper.waitForVisibility(getDriver(), displayedSelectedDate);
        return displayedSelectedDate.getText();
    }

    public void clickOnNextMonthButton() {
        moveTo(getDriver().findElement(By.cssSelector("[class='tc-accent2-background tc-economic-calendar-view-container']")));
        WaitHelper.waitForElementToBeClickable(getDriver(), calendarMonthNextButton);
        calendarMonthNextButton.click();
    }

    public void clickOnRiskDisclosureLink() {
        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.xpath(riskDisclosureLink)).click();
    }

    public String getUrlForPDf(){
        switchFocusOnNewTab();
        return getCurrentUrl();
    }
}
