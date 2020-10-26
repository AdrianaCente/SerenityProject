package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.HomePage;
import org.fasttrack.pages.MyAccountPage;
import org.junit.Assert;

public class LoginSteps {

    private HomePage homePage;
    private MyAccountPage myAccountPage;

    @Step
    public void enterCredentials(String username, String password){
        myAccountPage.setUserUsername(username);
        myAccountPage.setLoginPassword(password);
    }

    @Step
    public void clickLogin(){
        myAccountPage.clickLogin();
    }

    @Step
    public void verifyUserIsLoggedIn(String userName){
        myAccountPage.verifySuccessUser(userName);
    }

    @Step
    public void verifyUserIsNotLoggedIn(String[] errorMessage, int index){
        Assert.assertTrue(myAccountPage.verifyMandatoryFields(errorMessage, index));
    }

    @Step
    public void logIn(String username, String pass){
        homePage.open();
        homePage.clickMyAccountLink();
        enterCredentials(username, pass);
        clickLogin();
    }
}
