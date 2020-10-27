package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class MyAccountPage extends PageObject {

    @FindBy(css = "#reg_email")
    private WebElementFacade email;

    @FindBy(css = "#reg_password")
    private WebElementFacade password;

    @FindBy(css = ".register button")
    private WebElementFacade registerButton;

    @FindBy(css = ".woocommerce-MyAccount-content p:first-of-type")
    private WebElementFacade welcomeRegisteredUserMessage;

    @FindBy(css = ".woocommerce-error li")
    private WebElementFacade errorMessage;

    @FindBy(css = "#username")
    private WebElementFacade userUsername;

    @FindBy(css = "#password")
    private WebElementFacade userPassword;

    @FindBy(css = ".login button")
    private WebElementFacade loginButton;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--customer-logout a")
    private WebElementFacade logoutLink;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-account a")
    private WebElementFacade accountDetailsLink;

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--edit-address a")
    private WebElementFacade accountAddress;

    @FindBy(css = "#account_first_name")
    private WebElementFacade firstName;

    @FindBy(css = "#account_last_name")
    private WebElementFacade lastName;

    @FindBy(css = "#password_current")
    private WebElementFacade currentPassword;

    @FindBy(css = "#password_1")
    private WebElementFacade passOne;

    @FindBy(css = "#password_2")
    private WebElementFacade passTwo;

    @FindBy(css = "[name='save_account_details']")
    private WebElementFacade saveButton;

    @FindBy(css = ".woocommerce-message")
    private WebElementFacade accountMessage;

    @FindBy(xpath = "//h3[text()= 'Billing address']/following-sibling ::a[@class='edit']")
    private WebElementFacade editBillingInfoButton;

    @FindBy(xpath = "//h3[text()= 'Billing address']/following::button[@class='button']")
    private WebElementFacade saveBillingInfoButton;

    @FindBy(xpath = "//h3[text()= 'Shipping address']/following-sibling ::a[@class='edit']")
    private WebElementFacade editShippingInfoButton;

    @FindBy(xpath = "//h3[text()= 'Shipping address']/following::button[@class='button']")
    private WebElementFacade saveShippingInfoButton;

    public void setEmail(String emailValue) {
        typeInto(email, emailValue);
    }

    public void setPassword(String passwordValue) {
        typeInto(password, passwordValue);
    }

    public void clickRegister() {
        clickOn(registerButton);
    }

    public void setUserUsername(String username) {
        typeInto(userUsername, username);
    }

    public void setLoginPassword(String password) {
        typeInto(userPassword, password);
    }

    public void clickLogin() {
        clickOn(loginButton);
    }

    public void verifySuccessUser(String name) {
        welcomeRegisteredUserMessage.shouldContainText("Hello " + name);
    }

    public boolean verifyMandatoryFields(String[] mandatoryFieldsMessage, int index) {
        if (errorMessage.getText().equals(mandatoryFieldsMessage[index])) {
            return true;
        }
        return false;
    }

    public void verifyDisabledButton() {
        Assert.assertTrue(registerButton.isDisabled());
    }

    public void logoutUser() {
        clickOn(logoutLink);
    }

    public void verifyLogoutUser(String name) {
        Assert.assertFalse(welcomeRegisteredUserMessage.equals("Hello " + name));
    }

    public void clickAccountDetailsLink() {
        clickOn(accountDetailsLink);
    }

    public void setFirstName(String fName) {
        typeInto(firstName, fName);
    }

    public void setLastName(String lName) {
        typeInto(lastName, lName);
    }

    public void setCurrentPassword(String pass) {
        typeInto(currentPassword, pass);
    }

    public void setNewPassword(String newPass) {
        typeInto(passOne, newPass);
    }

    public void confirmNewPassword(String newPassConfirmation) {
        typeInto(passTwo, newPassConfirmation);
    }

    public void checkUpdateAccountDetails() {
        waitFor(accountMessage);
        accountMessage.containsText("Account details changed successfully.");
    }

    public void clickSaveChangesButton() {
        clickOn(saveButton);
    }

    public void clickAccountAddress() {
        clickOn(accountAddress);
    }

    public void clickEditBillingAddress() {
        clickOn(editBillingInfoButton);
    }

    public void clickSaveBillingAddress() {
        clickOn(saveBillingInfoButton);
    }

    public void verifyBillingAddress() {
        accountMessage.containsText("Address changed successfully.");
    }

    public void clickEditShippingAddress() {
        clickOn(editShippingInfoButton);
    }

    public void clickSaveShippingAddress() {
        clickOn(saveShippingInfoButton);
    }
}
