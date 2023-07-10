package com.xm.pages;

import com.xm.helpers.ComponentHelper;
import com.xm.helpers.WaitHelper;
import com.xm.utils.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public  class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> implements ComponentHelper {

    private  WebDriver driver;
    private  String serverUrl;
    private  String pagePath;

    protected String riskDisclosureLink = "//*[@id='risk-block']//a";

    private Config config;

    protected BasePage(WebDriver driver, String pagePath) {
        this.driver = driver;
        this.serverUrl = getUrl();
        this.pagePath = pagePath;

        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }


    @Override
    protected void load() {
        driver.get(serverUrl + pagePath);
    }


    @Override
    protected void isLoaded() {
        assert isOnPage();
    }

    public boolean isOnPage() {
        return getCurrentUrl().endsWith(pagePath);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getUrl() {
        if (config == null) {
            config = new Config("app.properties");
        }
        return config.get("url");
    }


    public String getExpectedUrl() {
        return String.format("%s%s", serverUrl, pagePath);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getPagePath() {
        return pagePath;
    }

}
