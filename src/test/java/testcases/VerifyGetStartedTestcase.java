package testcases;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testcases.ListenerTestcase.class)
public class VerifyGetStartedTestcase extends BaseTestcase {

    @Test(testName = "verifyGetStartedPages", priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will browse get started pages")
    @Story("Story: Get Started")
    public void verifyGetStartedPages() throws Exception {
        getStartedPage.verifyGetStartedPages();
    }

    @Test(testName = "clickButtonGetStarted", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will immediately click get started button")
    @Story("Story: Get Started")
    public void clickButtonGetStarted() throws Exception {
        getStartedPage.clickBtnGetStarted();
    }


}
