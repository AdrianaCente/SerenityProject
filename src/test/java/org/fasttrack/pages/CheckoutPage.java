package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage extends PageObject {
    @FindBy(css = "#billing_first_name")
    private WebElementFacade firstName;

    @FindBy(css = "#billing_last_name")
    private WebElementFacade lastName;

    @FindBy(css = "#billing_country")
    private WebElementFacade billingCountry;

    @FindBy(css = "input#billing_address_1")
    private WebElementFacade addressOne;

    @FindBy(css = "input#billing_city")
    private WebElementFacade billingCity;

    @FindBy(css = "select#billing_state")
    private WebElementFacade billingState;

    @FindBy(css = "input#billing_postcode")
    private WebElementFacade billingPostcode;

    @FindBy(css = "input#billing_phone")
    private WebElementFacade billingPhone;

    @FindBy(css = "input#billing_email")
    private WebElementFacade billingEmail;

    @FindBy(css = "#place_order")
    private WebElementFacade placeOrderButton;

    @FindBy(css = ".showlogin")
    private WebElementFacade showLoginLink;

    @FindBy(css = "#username")
    private WebElementFacade username;

    @FindBy(css = "#password")
    private WebElementFacade password;

    @FindBy(css = "button[name='login']")
    private WebElementFacade loginButton;

    @FindBy(css = "#shipping_first_name")
    private WebElementFacade shippingFirstName;

    @FindBy(css = "#shipping_last_name")
    private WebElementFacade shippingLastName;

    @FindBy(css = "#shipping_country")
    private WebElementFacade shippingCountry;

    @FindBy(css = "input#shipping_address_1")
    private WebElementFacade shippingAddressOne;

    @FindBy(css = "input#shipping_city")
    private WebElementFacade shippingCity;

    @FindBy(css = "select#shipping_state")
    private WebElementFacade shippingState;

    @FindBy(css = "input#shipping_postcode")
    private WebElementFacade shippingPostcode;

    @FindBy(css = "#ship-to-different-address-checkbox")
    private WebElementFacade differentShipAddressCheckbox;

    public void setFirstName(String fName) {
        typeInto(firstName, fName);
    }

    public void setLastName(String lName) {
        typeInto(lastName, lName);
    }

    public void selectCountry(String country) {
        billingCountry.selectByValue(country);
    }

    public void setAddress(String address) {
        typeInto(addressOne, address);
    }

    public void setCity(String city) {
        typeInto(billingCity, city);
    }

    public void selectCounty(String state) {
        billingState.selectByValue(state);
    }

    public void setZipcode(String zipCode) {
        typeInto(billingPostcode, zipCode);
    }

    public void setTelephone(String phone) {
        typeInto(billingPhone, phone);
    }

    public void setEmail(String email) {
        typeInto(billingEmail, email);
    }

    public void clickPlaceOrder() {
        clickOn(placeOrderButton);
    }

    public void setUsername(String user) {
        typeInto(username, user);
    }

    public void setLoginPassword(String pass) {
        typeInto(password, pass);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    public void clickLoginLink() {
        clickOn(showLoginLink);
    }


    public void setShippingFirstName(String fName) {
        typeInto(shippingFirstName, fName);
    }

    public void setShippingLastName(String lName) {
        typeInto(shippingLastName, lName);
    }

    public void selectShippingCountry(String country) {
        shippingCountry.selectByValue(country);
    }

    public void setShippingAddress(String address) {
        typeInto(shippingAddressOne, address);
    }

    public void setShippingCity(String city) {
        typeInto(shippingCity, city);
    }

    public void selectShippingCounty(String state) {
        shippingState.selectByValue(state);
    }

    public void setShippingZipcode(String zipCode) {
        typeInto(shippingPostcode, zipCode);
    }

    public void clickAddDifferentShippingAddress() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(differentShipAddressCheckbox).click().build().perform();
    }
}
