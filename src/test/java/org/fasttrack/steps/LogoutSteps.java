package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.MyAccountPage;

public class LogoutSteps {

    private MyAccountPage myAccountPage;

    @Step
    public void logoutUser() {
        myAccountPage.logoutUser();
    }

    @Step
    public void verifyUserIsLogout(String userName){
        myAccountPage.verifyLogoutUser(userName);
    }
}
