package com.xm.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHelper {

    private static DriverHelper driverHelper;

    private DriverHelper() {
    }

    public static DriverHelper getInstance() {
        if (driverHelper == null) {
            driverHelper = new DriverHelper();
        }

        return driverHelper;
    }

    private WebDriver driver;

    public WebDriver initDriver() {
        String driverName = (null != System.getProperty("driver")) ? (System.getProperty("driver")) : ("chrome");

        switch (driverName) {
            case "chrome" -> {
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                }
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }
}
