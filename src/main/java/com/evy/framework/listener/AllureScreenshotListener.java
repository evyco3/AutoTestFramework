package com.evy.framework.listener;

import com.evy.framework.driver.Driver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

/**
 * TestNG listener that captures screenshots on test failure and attaches them to Allure reports.
 */
public class AllureScreenshotListener implements ITestListener {



    @Override
    public void onTestFailure(ITestResult result) {
            TakesScreenshot screenshot = (TakesScreenshot) Driver.getInstance().get();
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            attachScreenshotToAllure(screenshotBytes);

    }

    @Step("Attach screenshot to Allure report")
    public void attachScreenshotToAllure(byte[] screenshot) {
        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");
    }

    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
