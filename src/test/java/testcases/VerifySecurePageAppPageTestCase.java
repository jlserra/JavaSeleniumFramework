package testcases;


import com.sun.istack.internal.localization.NullLocalizable;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.SecureAppPage;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

@Listeners(testcases.ListenerTestcase.class)
public class VerifySecurePageAppPageTestCase extends BaseTestcase {

    @Test(testName = "verifyWelcomePage", priority = 0)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Welcome page should be displayed")
    @Story("Story: Login")
    public void verifyWelcomePage() throws Exception {
        getStartedPage.clickBtnGetStarted();
        assertTrue(welcomePage.verifyIfLoginPage());
    }

    @Test(testName = "verifyLoggingUsingValidNumber", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User should get an error message")
    @Story("Story: Login")
    public void verifyLoginInUsingValidNumber() throws Exception {
        welcomePage.enterPrepaidNumber("09271080510");
        welcomePage.clickBtnNext();
    }

    @Test(testName = "verifySecureAppPage", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: SecureApp page should be displayed")
    @Story("Story: Security")
    public void verifySecureAppPage() throws Exception {
        secureAppPage.clickBtnNext();
        assertTrue(secureAppPage.verifyIfSecurePage());
    }

    @Test(testName = "verifyEnteringSecureCode", priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: SecureApp page should be displayed")
    @Story("Story: Security")
    public void verifyEnteringSecureCode() throws Exception {
        secureAppPage.enterPin("1111");
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();
    }

    @Test(testName = "verifyReEnteringUnmatchedSecureCode", priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: SecureApp page should be displayed")
    @Story("Story: Security")
    public void verifyReEnteringUnmatchedSecureCode() throws Exception {
        String errorSpiel = "App PIN did not match. Try again";
        secureAppPage.enterPin("1234");
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();
        assertTrue(secureAppPage.verifyUnmatchedSecurePinErrorSpiel(errorSpiel));
    }

    @Test(testName = "verifyReEnteringMatchedSecureCode", priority = 6)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: SecureApp page should be displayed")
    @Story("Story: Security")
    public void verifyReEnteringMatchedSecureCode() throws Exception {
        int secureAppPin;
        for(secureAppPin=0;secureAppPin<4;) {
            secureAppPage.clickBtnDelete();
            secureAppPin++;
        }
        secureAppPage.enterPin("1111");
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();
        assertTrue(secureAppPage.verifyIfSecurePageConfirmation());
    }
}