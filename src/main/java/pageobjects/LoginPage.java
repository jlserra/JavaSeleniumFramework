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
    public void clickBtnLogin() throws Exception {
        log.info("Step: Click Login Button");
        action.click("btnLogin");
    }

    @Step("Step: Select Inpatient Ward")
    public void selectInpatientWard() throws Exception {
        log.info("Step: Select Inpatient Ward");
        action.click("lblInpatientWard");
    }

    @Step("Step: Select Inpatient Ward")
    public void selectIsolationWard() throws Exception {
        log.info("Step: Select Isolation Ward");
        action.click("lblIsolationWard");
    }

    @Step("Login using credentials in Inpatient Ward")
    public boolean loginAsInpatientWard() throws Exception {
        log.info("Login using credentials in Inpatient Ward");
        enterUsername();
        enterPassword();
        selectInpatientWard();
        clickBtnLogin();
        action.takeSnapShot("loginAsInpatientWard");
        return true;
    }

    @Step("Login using credentials in Isolation Ward")
    public boolean loginAsIsolationWard() throws Exception {
        log.info("Login using credentials in Isolation Ward");
        enterUsername();
        enterPassword();
        selectIsolationWard();
        clickBtnLogin();
        action.takeSnapShot("loginAsIsolationWard");
        return true;
    }

}





