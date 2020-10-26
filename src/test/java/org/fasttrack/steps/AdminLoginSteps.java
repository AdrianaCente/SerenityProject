package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.AdminHomePage;
import org.fasttrack.pages.AdminLoginPage;
import org.junit.Assert;

public class AdminLoginSteps {
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;

    @Step
    public void navigateToAdminLogin() {
        adminLoginPage.open();
    }

    @Step
    public void enterAdminCredentials(String username, String pass) {
        adminLoginPage.setUsername(username);
        adminLoginPage.setPassword(pass);
        adminLoginPage.clickLoginButton();
    }

    @Step
    public void checkSuccessLogin(String name) {
        adminHomePage.checkSuccessLogin(name);
    }

    @Step
    public void adminLogin(String username, String pass) {
        navigateToAdminLogin();
        enterAdminCredentials(username, pass);
    }

    @Step
    public void checkLoginMessage(String[] errorMessages) {
        Assert.assertTrue(adminLoginPage.checkLoginMessage(errorMessages));
    }
}
