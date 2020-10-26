package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.HomePage;
import org.fasttrack.pages.MyAccountPage;
import org.junit.Assert;

public class RegisterSteps {
    private MyAccountPage myAccountPage;
    private HomePage homePage;

    @Step
    public void navigateToMyAccountPage() {
        homePage.open();
        homePage.clickMyAccountLink();
    }

    @Step
    public void enterRegistrationCredentials(String email, String password) {
        myAccountPage.setEmail(email);
        myAccountPage.setPassword(password);
    }

    @Step
    public void submitRegistration() {
        myAccountPage.clickRegister();
    }

    @Step
    public void verifySuccessRegister(String name) {
        myAccountPage.verifySuccessUser(name);
    }

    @Step
    public void verifyMandatoryFields(String[] mandatoryFieldsMessage, int index) {
        Assert.assertTrue(myAccountPage.verifyMandatoryFields(mandatoryFieldsMessage, index));
    }

    @Step
    public void verifyDisabledButton() {
        myAccountPage.verifyDisabledButton();
    }

    @Step
    public void registerUser(String email, String password) {
        navigateToMyAccountPage();
        enterRegistrationCredentials(email, password);
        submitRegistration();
    }
}
