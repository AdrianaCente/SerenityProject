package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.AdminCouponSteps;
import org.fasttrack.steps.AdminLoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AdminCouponsTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private AdminCouponSteps adminCouponSteps;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Test
    public void checkValidCoupons() {
        driver.manage().window().maximize();
        adminLoginSteps.adminLogin("admin", "parola11");
        adminCouponSteps.navigateToCouponPage();
        adminCouponSteps.checkValidCoupons();
    }
}
