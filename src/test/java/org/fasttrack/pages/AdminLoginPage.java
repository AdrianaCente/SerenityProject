package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;

@DefaultUrl("http://qa4.fasttrackit.org:8008/wp-admin/")
public class AdminLoginPage extends PageObject {

    @FindBy(id = "user_login")
    private WebElementFacade usernameField;

    @FindBy(id = "user_pass")
    private WebElementFacade passwordField;

    @FindBy(id = "wp-submit")
    private WebElementFacade loginButton;

    @FindBy(css = "#login_error")
    private WebElementFacade errorMessage;

    public void setUsername(String user) {
        typeInto(usernameField, user);
    }

    public void setPassword(String pass) {
        typeInto(passwordField, pass);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    public boolean checkLoginMessage(String[] errorMessages) {
        for (String error : errorMessages) {
            if (errorMessage.getText().contains(error)) {
                return true;
            }
        }
        return false;
    }
}
