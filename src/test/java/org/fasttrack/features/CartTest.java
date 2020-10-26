package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class CartTest {
    private String productName = "Beanie with Logo";

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private RegisterSteps registerSteps;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private LogoutSteps logoutSteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private SearchSteps searchSteps;

    @Steps
    private AdminLoginSteps adminLoginSteps;

    @Steps
    private AdminCouponSteps adminCouponSteps;

    @Test
    public void addToCartTest(){
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.addProductToCart(productName);
        cartSteps.clickViewCartLink(productName);
        cartSteps.checkAddedItem(productName);
    }

    @Test
    public void addToCartTestWhileLoggedIn(){
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.addProductToCart(productName);
        cartSteps.clickViewCartLink(productName);
        cartSteps.checkAddedItem(productName);
    }

    @Test
    public void addMultipleProductsToCart(){
        //products added from product gallery and details page
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        searchSteps.searchProduct("Hoodie");
        cartSteps.addProductToCart("Hoodie with Zipper");
        cartSteps.clickCartHeader();
        cartSteps.checkProductPrice();
    }

    @Test
    public void addMultipleProductsToCartFromDetailsPage(){
        //products added from details page
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCartFromDetailsPage("Beanie");
        searchSteps.searchProduct("t-shirt");
        cartSteps.addProductToCartFromDetailsPage("T-Shirt with Logo");
        searchSteps.searchProduct("Hoodie");
        cartSteps.addProductToCartFromDetailsPage("Hoodie with Logo");
        cartSteps.clickCartHeader();
        cartSteps.checkProductPrice();
    }

    @Test
    public void updateQuantityInCart() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie");
        searchSteps.searchProduct("t-shirt");
        cartSteps.addProductToCart("T-Shirt with Logo");
        cartSteps.clickCartHeader();
        cartSteps.updateProductQuantity("Beanie", "4");
        cartSteps.verifyUpdatedCart();
        cartSteps.verifyUpdatedQuantityInCart("Beanie", "4");
    }

    @Test
    public void verifyTotalPriceInCart() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie");
        searchSteps.searchProduct("t-shirt");
        cartSteps.addProductToCart("T-Shirt with Logo");
        cartSteps.clickCartHeader();
        cartSteps.checkTotalCartPrice();
    }

    @Test
    public void removeItemFromCart() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        cartSteps.removeItemFromCart("Cap");
        cartSteps.checkRemovedItemFromCart("Cap");
    }

    @Test
    public void emptyCart() {
        searchSteps.searchProduct("Beanie");
        cartSteps.addProductToCart("Beanie with Logo");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        searchSteps.searchProduct("Hoodie");
        cartSteps.addProductToCart("Hoodie with Zipper");
        cartSteps.clickCartHeader();
        cartSteps.emptyProductCart();
        cartSteps.verifyEmptyCart();
        driver.close();
    }

    @Test
    public void applyCoupon() {
        driver.manage().window().maximize();
        adminLoginSteps.adminLogin("admin", "parola11");
        adminCouponSteps.navigateToCouponPage();
        String code = adminCouponSteps.getCoupon();
        String amount = adminCouponSteps.getCouponAmount(code);

        registerSteps.navigateToMyAccountPage();
        logoutSteps.logoutUser();
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        searchSteps.searchProduct("Hoodie");
        cartSteps.addProductToCart("Hoodie with Zipper");
        searchSteps.searchProduct("Cap");
        cartSteps.addProductToCart("Cap");
        cartSteps.clickCartHeader();
        cartSteps.applyCoupon(code);
        cartSteps.checkAppliedCoupon(amount);
    }
}


