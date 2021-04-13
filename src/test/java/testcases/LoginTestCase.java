package testcases;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(testcases.ListenerTestcase.class)
public class LoginTestCase extends BaseTestcase {

    @Test(testName = "loginUser")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: User will be logged in to the system")
    @Story("Story: Login")
    public void loginUser() throws Exception {
        assertTrue(loginPage.loginAsInpatientWard());
}

}
