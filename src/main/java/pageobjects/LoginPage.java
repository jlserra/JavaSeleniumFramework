package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Step: Enter username")
    public void enterUsername() {
        log.info("Step: Enter username");
        action.sendKeys("txtUsername", excel.getTestdata("username"));
    }

    @Step("Step: Enter password")
    public void enterPassword() {
        log.info("Step: Enter password");
        action.sendKeys("txtPassword", excel.getTestdata("password"));
    }

    @Step("Step: Click Login Button")
    public void clickBtnLogin() {
        log.info("Step: Click Login Button");
        action.click("btnLogin");
    }

    @Step("Step: Select Inpatient Ward")
    public void selectInpatientWard() {
        log.info("Step: Select Inpatient Ward");
        action.click("lblInpatientWard");
    }

    @Step("Login using credentials and select location")
    public boolean loginAsInpatientWard() {
        log.info("Login using credentials and select location");
        enterUsername();
        enterPassword();
        selectInpatientWard();
        clickBtnLogin();
        return true;
    }

}





