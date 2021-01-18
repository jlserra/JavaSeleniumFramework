package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(testcases.ListenerTestcase.class)
public class VerifyWelcomePageTestCase extends BaseTestcase{

    @Test(testName = "verifyWelcomePage", priority = 0)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Welcome page should be displayed")
    @Story("Story: Login")
    public void verifyWelcomePage() throws Exception {
        getStartedPage.clickBtnGetStarted();
        assertTrue(welcomePage.verifyIfLoginPage());
    }

    @Test(testName = "verifyLoggingUsingInvalidNumber", priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User should get an error message")
    @Story("Story: Login")
    public void verifyErrorSpielWhenLoginInUsingInvalidNumber() throws Exception {
        String errorSpiel = "Sorry, you have entered an invalid Globe myBusiness Prepaid Internet number";
        welcomePage.enterPrepaidNumber("09212341298");
        welcomePage.clickBtnNext();
        assertTrue(welcomePage.verifyInvalidNumberErrorSpiel(errorSpiel));
    }

    @Test(testName = "verifyLoggingUsingValidNumber", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User should get an error message")
    @Story("Story: Login")
    public void verifyLoginInUsingValidNumber() throws Exception {
        welcomePage.deleteEnteredNumber();
        welcomePage.enterPrepaidNumber("09212341294");
        welcomePage.clickBtnNext();
        assertTrue(secureAppPage.verifyIfSecurePage());
    }



}
