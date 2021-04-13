package pageobjects;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    @Step("Step: Enter username")
    public void enterUsername() throws Exception {
        log.info("Step: Enter username");
        action.sendKeys("txtUsername", excel.getTestdata("username"));
    }

    @Step("Step: Enter password")
    public void enterPassword() throws Exception {
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

    @Step("Step: Select Inpatient Ward")
    public void selectIsolationWard() {
        log.info("Step: Select Inpatient Ward");
        action.click("lblInpatientWard");
    }

    @Step("Login using credentials and select location")
    public boolean loginAsInpatientWard() throws Exception {
        log.info("Login using credentials and select location");
        enterUsername();
        enterPassword();
        selectInpatientWard();
        clickBtnLogin();
        return true;
    }

    @Step("Login using credentials and select location")
    public boolean loginAsIsolationWard() throws Exception {
        log.info("Login using credentials and select location");
        enterUsername();
        enterPassword();
        selectIsolationWard();
        clickBtnLogin();
        return true;
    }

}





