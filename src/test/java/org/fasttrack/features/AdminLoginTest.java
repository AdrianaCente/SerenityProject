package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.AdminLoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AdminLoginTest {
    private String usernameAdmin = "admin";
    private String[] errorMessages = {"ERROR: Invalid username.", "ERROR: The password you entered for the username " + usernameAdmin + " is incorrect."};


    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Test
    public void loginAdmin() {
        adminLoginSteps.navigateToAdminLogin();
        adminLoginSteps.enterAdminCredentials("admin", "parola11");
        adminLoginSteps.checkSuccessLogin("admin");
    }

    @Test
    public void loginAdminWithInvalidUsername() {
        adminLoginSteps.navigateToAdminLogin();
        adminLoginSteps.enterAdminCredentials("adm", "parola11");
        adminLoginSteps.checkLoginMessage(errorMessages);
    }

    @Test
    public void loginAdminWithInvalidPassword() {
        adminLoginSteps.navigateToAdminLogin();
        adminLoginSteps.enterAdminCredentials("admin", "pass");
        adminLoginSteps.checkLoginMessage(errorMessages);
    }
}
