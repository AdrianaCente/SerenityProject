package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.AdminLoginSteps;
import org.fasttrack.steps.AdminPostsSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AdminPostsTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Steps
    private AdminPostsSteps adminPostsSteps;

    @Test
    public void addNewPost() {
        driver.manage().window().maximize();
        adminLoginSteps.navigateToAdminLogin();
        adminLoginSteps.enterAdminCredentials("admin", "parola11");
        adminPostsSteps.navigateToPostMenu();
        adminPostsSteps.addNewPost("Project post title", "Project content");
        adminPostsSteps.publicPost();
        adminPostsSteps.checkAddedPost("Project post title");
    }
}
