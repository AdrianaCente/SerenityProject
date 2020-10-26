package org.fasttrack.features;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.LoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class LoginTest {

    private String emailAddressTest = "test@yahoo.com";
    private String[] errorMessage = {"ERROR: Invalid email address. Lost your password?",
            "ERROR: The password you entered for the email address " + emailAddressTest + " is incorrect. Lost your password?",
            "Error: Username is required.",
            "ERROR: The password field is empty."};

    @Managed(uniqueSession = true)
    public WebDriver driver;


    @Steps
    private LoginSteps loginSteps;


    @Test
    public void validLoginTest(){
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        loginSteps.verifyUserIsLoggedIn("test");
    }

    @Test
    public void invalidEmailLoginTest(){
        loginSteps.logIn("testOne@yahoo.com","1qaz@WSX3edc");
        loginSteps.verifyUserIsNotLoggedIn(errorMessage, 0);
    }

    @Test
    public void invalidPasswordLoginTest(){
        loginSteps.logIn(emailAddressTest,"1qazfgdfgdf");
        loginSteps.verifyUserIsNotLoggedIn(errorMessage, 1);
    }

    @Test
    public void emptyFieldsLoginTest(){
        loginSteps.logIn("","");
        loginSteps.verifyUserIsNotLoggedIn(errorMessage, 2);
    }

    @Test
    public void emptyPasswordFieldLoginTest(){
        loginSteps.logIn("testOne@yahoo.com","");
        loginSteps.verifyUserIsNotLoggedIn(errorMessage, 3);
    }
}
