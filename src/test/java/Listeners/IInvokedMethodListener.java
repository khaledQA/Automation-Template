package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListener implements org.testng.IInvokedMethodListener {
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context)
    {
        if (testResult.getStatus()==ITestResult.FAILURE)
            LogsUtils.info("Test Case "+ testResult.getTestName() + " failed");
            Utility.takeScreenshot(getDriver(),testResult.getName());

    }
}
