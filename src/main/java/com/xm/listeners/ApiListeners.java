package com.xm.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ApiListeners implements ITestListener {

    private final Logger log = LoggerFactory.getLogger(ApiListeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed: " + result.getName());
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

}
