package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.LoginSteps;
import org.fasttrack.steps.LogoutSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class LogoutTest {

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private LogoutSteps logoutSteps;

    @Test
    public void logoutUser() {
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        logoutSteps.logoutUser();
        logoutSteps.verifyUserIsLogout("test");
    }
}
