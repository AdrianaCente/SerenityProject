package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class OrderPage extends PageObject {
    @FindBy(css = ".woocommerce-notice")
    private WebElementFacade placeOrderMessage;

    public void checkOrderMessage() {
        waitFor(placeOrderMessage);
        Assert.assertTrue(placeOrderMessage.getText().equals("Thank you. Your order has been received."));
    }
}
