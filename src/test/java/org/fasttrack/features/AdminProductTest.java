package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.AdminProductSteps;
import org.fasttrack.steps.AdminLoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AdminProductTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Steps
    private AdminProductSteps adminProductSteps;

    @Test
    public void addNewProduct() {
        driver.manage().window().maximize();
        adminLoginSteps.adminLogin("admin", "parola11");
        adminProductSteps.addNewProduct("Red T-Shirt", "red t-shirt description", "27", "10", "Tshirts");
        adminProductSteps.checkAddedProduct("Red T-Shirt");
        driver.close();
    }

    @Test
    public void removeProduct() {
        driver.manage().window().maximize();
        adminLoginSteps.adminLogin("admin", "parola11");
        adminProductSteps.navigateToProductsPage();
        adminProductSteps.removeItem("Red T-Shirt");
        adminProductSteps.checkRemovedItem("Red T-Shirt");
    }
}
