package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(testcases.ListenerTestcase.class)
public class VerifyChooseModem extends BaseTestcase{

    @Test(testName = "VerifyChooseModem1", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will check for the first modem")
    @Story("Story: Choose modem 1")
    public void verifyChooseModem() throws Exception {

        assertTrue(getStartedPage.verifyIfGetStartedPage());
        getStartedPage.clickBtnGetStarted();
        welcomePage.enterPrepaidNumber("09271080510");
        welcomePage.clickBtnNext();


        assertTrue(secureAppPage.verifyIfSecurePage());
        secureAppPage.enterPin("1111");
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

        assertTrue(secureAppPage.verifyIfSecurePageConfirmation());
        secureAppPage.enterPin("1111");
        secureAppPage.verifyIfBtnNextIsEnabled();
        secureAppPage.clickBtnNext();

        customerProfilePage.enterNickname("Test Abe");
        customerProfilePage.clickBtnNext();

        chooseModemPage.clickCheckBox();
        chooseModemPage.clickLetsGoBtn();

        chooseModemPage.clickSkipBtn();
        chooseModemPage.clickCloseModalBtn();
        chooseModemPage.clickModemOneBtn();

    }

}
