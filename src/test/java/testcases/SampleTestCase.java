package testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class SampleTestCase extends BaseTestcase {

    @Test (priority = 1, description = "Test Click")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Test Click")
    @Story("Story: Test Click")
    public void activityIndicators() throws Exception {

        samplePage.verifyItemActivityIndicators();
        samplePage.clickItemActivityIndicators();

    }


}
