package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.CheckoutSteps;
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

    @Steps
    private CheckoutSteps checkoutSteps;

    @Test
    public void modifyPassword() {
        loginSteps.logIn("ema@yahoo.com","1qaz@WSX3edc");
        myAccountSteps.navigateToAccountDetails();
        myAccountSteps.enterCredentials("ema", "dim", "1qaz@WSX3edc", "1qaz@WSX3edc1", "1qaz@WSX3edc1");
        myAccountSteps.checkUpdatedAccountDetails();
    }

    @Test
    public void modifyBillingAndShippingAddress() {
        loginSteps.logIn("emma@yahoo.com","1qaz@WSX3edc");
        myAccountSteps.navigateToBillingAddress();
        checkoutSteps.enterBillingInformation("Emma", "Thomson", "RO", "Eagle street, no.12", "Rino", "CJ", "45655456", "02789456", "thomson@yahoo.com");
        myAccountSteps.saveBillingAddress();
        myAccountSteps.navigateToShippingAddress();
        checkoutSteps.enterShippingInformation("Emilia", "Floppy", "RO", "Slow street, no.10", "Kana", "CJ", "234589");
        myAccountSteps.saveShippingAddress();
        myAccountSteps.checkModifiedBillingShippingAddress();
    }
}
