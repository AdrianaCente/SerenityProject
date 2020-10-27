package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.CartSteps;
import org.fasttrack.steps.CheckoutSteps;
import org.fasttrack.steps.LoginSteps;
import org.fasttrack.steps.SearchSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class CheckoutTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private CheckoutSteps checkoutSteps;

    @Steps
    private SearchSteps searchSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void placeOrderWithGuest() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        checkoutSteps.proceedToCheckout();
        checkoutSteps.enterBillingInformation("Ema", "Thomson", "RO", "Eagle street, no.12", "Rino", "CJ", "45655456", "02789456", "thomson@yahoo.com");
        checkoutSteps.clickPlaceOrder();
        checkoutSteps.checkOrderMessage();
    }

    @Test
    public void placeOrderWithLoginUser() {
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        checkoutSteps.proceedToCheckout();
        checkoutSteps.enterBillingInformation("ana", "dimm", "RO", "Eagle street, no.25", "Tower", "CJ", "5556654", "025588522", "dimm@yahoo.com");
        checkoutSteps.clickPlaceOrder();
        checkoutSteps.checkOrderMessage();
    }

    @Test
    public void placeOrderAndLoginUserInCheckout() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        checkoutSteps.proceedToCheckout();
        checkoutSteps.enterLoginCredentials("test@yahoo.com","1qaz@WSX3edc");
        checkoutSteps.enterBillingInformation("karin", "dream", "RO", "Eagle street, no.30", "Thor", "CJ", "354666", "88997744", "dream@yahoo.com");
        checkoutSteps.clickPlaceOrder();
        checkoutSteps.checkOrderMessage();
    }

    @Test
    public void placeOrderWithDifferentShippingInformation() {
        driver.manage().window().maximize();
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        checkoutSteps.proceedToCheckout();
        checkoutSteps.enterBillingInformation("Ema", "Thomson", "RO", "Eagle street, no.12", "Rino", "CJ", "45655456", "02789456", "thomson@yahoo.com");
        checkoutSteps.checkAddDifferentShippingAddress();
        checkoutSteps.enterShippingInformation("Emilia", "Floppy", "RO", "Slow street, no.10", "Kana", "CJ", "234589");
        checkoutSteps.clickPlaceOrder();
        checkoutSteps.checkOrderMessage();
    }
}
