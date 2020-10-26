package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.AdminCouponsPage;
import org.fasttrack.pages.AdminHomePage;
import org.junit.Assert;

public class AdminCouponSteps {
    private AdminCouponsPage adminCouponsPage;
    private AdminHomePage adminHomePage;

    @Step
    public void navigateToCouponPage() {
        adminHomePage.clickWooCommerceMenu();
        adminHomePage.clickCouponSubmenu();
    }

    @Step
    public void checkValidCoupons() {
        Assert.assertTrue(adminCouponsPage.compareDates().length() > 0);
    }

    @Step
    public String getCoupon() {
        return adminCouponsPage.compareDates();
    }

    @Step
    public String getCouponAmount(String title) {
        return adminCouponsPage.getCouponAmount(title);
    }
}
