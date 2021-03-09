package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(testcases.ListenerTestcase.class)
public class CustomerProfileTestcase extends BaseTestcase {

    @Test(testName = "customerProfileEnterNickname")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will enter nickname in customer profile page")
    @Story("Story: Customer Profile")
    public void customerProfileEnterNickname() throws Exception {

//        Get Started Page
        assertTrue(getStartedPage.verifyIfGetStartedPage());
        getStartedPage.clickBtnGetStarted();

//        Login Page
        assertTrue(welcomePage.verifyIfLoginPage());
        welcomePage.enterPrepaidNumber(excel.getTestdata("mobileNumber"));
        assertTrue(welcomePage.verifyIfMobileNumberIsEntered(excel.getTestdata("mobileNumber")));
        assertTrue(welcomePage.verifyIfBtnNextIsEnabled());
        welcomePage.clickBtnNext();

//        Secure Application Page
        assertTrue(secureAppPage.verifyIfSecurePage());
        secureAppPage.enterPin(excel.getTestdata("pin"));
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

        assertTrue(secureAppPage.verifyIfSecurePageConfirmation());
        secureAppPage.enterPin(excel.getTestdata("pin"));
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

//        Customer Profile
        assertTrue(customerProfilePage.verifyIfCustomerProfilePage());
        customerProfilePage.enterNickname(excel.getTestdata("nickname"));
        assertTrue(customerProfilePage.verifyIfBtnNextIsEnabled());
        customerProfilePage.clickBtnNext();

    }

    @Test(testName = "customerProfileSkipEnterNickname", priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will skip enter nickname in customer profile page")
    @Story("Story: Customer Profile")
    public void customerProfileSkipEnterNickname() throws Exception {

//        Get Started Page
        assertTrue(getStartedPage.verifyIfGetStartedPage());
        getStartedPage.clickBtnGetStarted();

//        Login Page
        assertTrue(welcomePage.verifyIfLoginPage());
        welcomePage.enterPrepaidNumber(excel.getTestdata("mobileNumber"));
        assertTrue(welcomePage.verifyIfMobileNumberIsEntered(excel.getTestdata("mobileNumber")));
        assertTrue(welcomePage.verifyIfBtnNextIsEnabled());
        welcomePage.clickBtnNext();

//        Secure Application Page
        assertTrue(secureAppPage.verifyIfSecurePage());
        secureAppPage.enterPin(excel.getTestdata("pin"));
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

        assertTrue(secureAppPage.verifyIfSecurePageConfirmation());
        secureAppPage.enterPin(excel.getTestdata("pin"));
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

//        Customer Profile
        assertTrue(customerProfilePage.verifyIfCustomerProfilePage());
        customerProfilePage.clickBtnSkip();

    }

}
