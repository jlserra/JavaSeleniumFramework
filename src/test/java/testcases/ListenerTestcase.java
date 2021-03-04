package testcases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestcase extends BaseTestcase implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        config.setTestcase(result.getName());
        log.info("***********************  START Testcase: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("***********************  PASSED Testcase: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("***********************  FAILED Testcase: " + result.getName());
        log.error("ERROR : " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("***********************  SKIPPED Testcase: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        log.info("****************************************************************************************");
        log.info("***********************  SUITE START - " + context.getName() + "");
        log.info("****************************************************************************************");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("****************************************************************************************");
        log.info("***********************  SUITE END - " + context.getName() + "");
        log.info("****************************************************************************************");
    }
}
