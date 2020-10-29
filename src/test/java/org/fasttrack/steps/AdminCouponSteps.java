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

    @Step
    public void navigateToAddCouponPage() {
        adminCouponsPage.clickAddCoupon();
    }

    @Step
    public void addNewCouponDetails(String title, String amount) {
        adminCouponsPage.setCouponTitle(title);
        adminCouponsPage.setCouponAmount(amount);
        adminCouponsPage.setCouponExpirationDate();
        adminCouponsPage.clickNextDatePicker();
        adminCouponsPage.clickNextDatePicker();
        adminCouponsPage.clickDesiredDate();
    }

    @Step
    public void publishCoupon() {
        adminCouponsPage.clickPublishButton();
    }

    @Step
    public void verifyAddedCoupon() {
        adminCouponsPage.checkAddedCoupon();
    }
}
