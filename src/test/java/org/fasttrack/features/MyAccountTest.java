package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.LoginSteps;
import org.fasttrack.steps.MyAccountSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class MyAccountTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private MyAccountSteps myAccountSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void modifyPassword() {
        loginSteps.logIn("ema@yahoo.com","1qaz@WSX3edc");
        myAccountSteps.navigateToAccountDetails();
        myAccountSteps.enterCredentials("ema", "dim", "1qaz@WSX3edc", "1qaz@WSX3edc1", "1qaz@WSX3edc1");
        myAccountSteps.checkUpdatedAccountDetails();
    }
}
