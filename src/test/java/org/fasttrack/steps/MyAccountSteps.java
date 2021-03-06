package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.MyAccountPage;

public class MyAccountSteps {

    private MyAccountPage myAccountPage;

    @Step
    public void navigateToAccountDetails() {
        myAccountPage.clickAccountDetailsLink();
    }

    @Step
    public void enterCredentials(String firstName, String lastName, String pass, String newPass, String confirmPass) {
        myAccountPage.setFirstName(firstName);
        myAccountPage.setLastName(lastName);
        myAccountPage.setCurrentPassword(pass);
        myAccountPage.setNewPassword(newPass);
        myAccountPage.confirmNewPassword(confirmPass);
        myAccountPage.clickSaveChangesButton();
    }

    @Step
    public void checkUpdatedAccountDetails() {
        myAccountPage.checkUpdateAccountDetails();
    }

    @Step
    public void navigateToBillingAddress() {
        myAccountPage.clickAccountAddress();
        myAccountPage.clickEditBillingAddress();
    }

    @Step
    public void navigateToShippingAddress() {
        myAccountPage.clickEditShippingAddress();
    }

    @Step
    public void saveBillingAddress() {
        myAccountPage.clickSaveBillingAddress();
    }

    @Step
    public void saveShippingAddress() {
        myAccountPage.clickSaveShippingAddress();
    }

    @Step
    public void checkModifiedBillingShippingAddress() {
        myAccountPage.verifyBillingShippingAddress();
    }
}
