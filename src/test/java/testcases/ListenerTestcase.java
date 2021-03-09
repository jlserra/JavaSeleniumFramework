package testcases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ListenerTestcase extends BaseTestcase implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        config.setTestConfiguration(result.getTestClass().getName(),result.getName());
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
        log.error("ERROR : " + Arrays.toString(result.getThrowable().getStackTrace()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("***********************  SKIPPED Testcase: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("***********************  FAILED Testcase: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
