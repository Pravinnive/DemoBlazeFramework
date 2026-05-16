package com.krce.listeners;

import com.aventstack.extentreports.*;
import com.krce.utils.DriverFactory;
import com.krce.utils.ExtentManager;
import com.krce.utils.ScreenshotUtil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        String screenshotPath = ScreenshotUtil.captureScreenshot(
                DriverFactory.driver,
                result.getMethod().getMethodName()
        );

        try {
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}