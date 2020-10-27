package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.CartPage;
import org.fasttrack.pages.CheckoutPage;
import org.fasttrack.pages.OrderPage;

public class CheckoutSteps {
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderPage orderPage;

    @Step
    public void proceedToCheckout() {
        cartPage.clickCheckoutButton();
    }

    @Step
    public void enterBillingInformation(String firstName, String lastName, String country, String address, String city,
                                        String county, String zipcode, String phone, String email) {
        checkoutPage.setFirstName(firstName);
        checkoutPage.setLastName(lastName);
        checkoutPage.selectCountry(country);
        checkoutPage.setAddress(address);
        checkoutPage.setCity(city);
        checkoutPage.selectCounty(county);
        checkoutPage.setZipcode(zipcode);
        checkoutPage.setTelephone(phone);
        checkoutPage.setEmail(email);
    }

    @Step
    public void checkAddDifferentShippingAddress() {
        checkoutPage.clickAddDifferentShippingAddress();
    }

    @Step
    public void enterShippingInformation(String firstName, String lastName, String country, String address, String city,
                                         String county, String zipcode) {
        checkoutPage.setShippingFirstName(firstName);
        checkoutPage.setShippingLastName(lastName);
        checkoutPage.selectShippingCountry(country);
        checkoutPage.setShippingAddress(address);
        checkoutPage.setShippingCity(city);
        checkoutPage.selectShippingCounty(county);
        checkoutPage.setShippingZipcode(zipcode);
    }

    @Step
    public void clickPlaceOrder() {
        checkoutPage.clickPlaceOrder();
    }

    @Step
    public void checkOrderMessage() {
        orderPage.checkOrderMessage();
    }

    @Step
    public void enterLoginCredentials(String user, String pass) {
        checkoutPage.clickLoginLink();
        checkoutPage.setUsername(user);
        checkoutPage.setLoginPassword(pass);
        checkoutPage.clickLoginButton();
    }
}
