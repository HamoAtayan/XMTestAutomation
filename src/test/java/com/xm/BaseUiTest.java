package com.xm;

import com.xm.helpers.DriverHelper;
import com.xm.listeners.UiListeners;
import com.xm.utils.Config;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(UiListeners.class)
public abstract class BaseUiTest {

    protected WebDriver driver;

    private Config config;

    @BeforeClass
    void setupConfig() {
        config = new Config("app.properties");
    }

    @BeforeMethod
    public void setUp() {
        DriverHelper driverHelper =  DriverHelper.getInstance();
        driver = driverHelper.initDriver();
        driver.manage().window().setSize(new Dimension(1024,768));
    }

    @AfterTest
    protected void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
