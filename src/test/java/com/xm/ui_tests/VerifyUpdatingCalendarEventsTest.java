package com.xm.ui_tests;

import com.xm.BaseUiTest;
import com.xm.helpers.CommonHelper;
import com.xm.pages.EconomicCalendarPage;
import com.xm.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyUpdatingCalendarEventsTest extends BaseUiTest {

    @Test
    public void verifyUpdatingCalendarEvents() {
        HomePage homePage = new HomePage(driver);
        String formatting = "EEE MMM dd yyyy";
        EconomicCalendarPage economicCalendarPage = new EconomicCalendarPage(driver);
        homePage.get();
        homePage.clickOnAcceptAllButton();
        homePage.waitUntilPageLoads();
        Assert.assertTrue(homePage.isOnPage());
        homePage.clickOnResearchAndEducationLinkText();
        homePage.clickOneEconomicCalendarTextBtn();
        economicCalendarPage.waitUntilPageLoads();

        String currentDate = CommonHelper.getCurrentDate(formatting);
        String selectedDate = CommonHelper.formatDate(currentDate);
        economicCalendarPage.clickOnDateOnCalendar(currentDate);
        Assert.assertEquals(selectedDate, economicCalendarPage.getDisplayedSelectedDate());

        String tomorrow = CommonHelper.getDateFromCurrentDateInRange(1, formatting);
        selectedDate = CommonHelper.formatDate(tomorrow);
        economicCalendarPage.clickOnDateOnCalendar(tomorrow);
        Assert.assertEquals(selectedDate, economicCalendarPage.getDisplayedSelectedDate());

        String nextWeek = CommonHelper.getNextWeekDate(formatting);
        selectedDate = CommonHelper.formatDate(nextWeek);
        economicCalendarPage.clickOnDateOnCalendar(nextWeek);
        Assert.assertEquals(selectedDate, economicCalendarPage.getDisplayedSelectedDate());

        economicCalendarPage.clickOnNextMonthButton();
        String nextMonth = CommonHelper.getNextMonthDate(formatting);
        selectedDate = CommonHelper.formatDate(nextMonth);
        economicCalendarPage.clickOnDateOnCalendar(nextMonth);
        Assert.assertEquals(selectedDate, economicCalendarPage.getDisplayedSelectedDate());

        economicCalendarPage.clickOnRiskDisclosureLink();

        Assert.assertTrue(economicCalendarPage.getUrlForPDf().contains("XMGlobal-Risk-Disclosures-for-Financial-Instruments.pdf"));
    }
}
