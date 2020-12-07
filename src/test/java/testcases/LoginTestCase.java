package testcases;

import static org.testng.Assert.assertTrue;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utilities.ActionUtilities;

public class LoginTestCase extends BaseTestcase {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will be logged in to the system")
    @Story("Story: Login")
    public void loginUser() throws Exception {

        log.startTestCase(getClass().getName());

        assertTrue(getStartedPage.verifyBtnGetStarted());
        getStartedPage.clickBtnGetStarted();
        action.takeSnapShot(getClass().getSimpleName() + "Login Page");

        log.endTestCase(getClass().getName());
    }

}
