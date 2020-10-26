package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.RegisterSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class RegisterTest {

    private String[] mandatoryFieldsMessage = {"Error: Please enter an account password.",
            "Error: Please provide a valid email address.",
            "Error: An account is already registered with your email address. Please log in."};

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private RegisterSteps registerSteps;

    @Test
    public void registerWithValidCredentials() {
        registerSteps.registerUser("ana@yahoo.com", "1qaz@WSX3edc");
        registerSteps.verifySuccessRegister("ana");
    }

    @Test
    public void registerWithTheSameEmail() {
        registerSteps.registerUser("ana@yahoo.com", "1qaz@WSX3edc");
        registerSteps.verifyMandatoryFields(mandatoryFieldsMessage, 2);
    }

    @Test
    public void registerWithInvalidEmail() {
        registerSteps.registerUser("don@yahoo", "1qaz@WSX3edc");
        registerSteps.verifyMandatoryFields(mandatoryFieldsMessage, 1);
    }

    @Test
    public void registerWithoutEmail() {
        registerSteps.registerUser("", "1qaz@WSX3edc");
        registerSteps.verifyMandatoryFields(mandatoryFieldsMessage, 1);
    }

    @Test
    public void registerWithoutPassword() {
        registerSteps.registerUser("emm@yahoo.com", "");
        registerSteps.verifyMandatoryFields(mandatoryFieldsMessage, 0);
    }

    @Test
    public void registerWithInvalidPassword() {
        registerSteps.navigateToMyAccountPage();
        registerSteps.enterRegistrationCredentials("emm@yahoo.com", "1qaz");
        registerSteps.verifyDisabledButton();
    }
}
