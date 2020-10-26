package org.fasttrack.steps;

import io.cucumber.java.eo.Se;
import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.*;
import org.junit.Assert;

public class CartSteps {
    private SearchPage searchPage;
    private CartPage cartPage;
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;
    private AdminCouponsPage adminCouponsPage;
    private AdminHomePage adminHomePage;

    @Step
    public void addProductToCart(String title) {
        searchPage.addProductToCart(title);
    }

    @Step
    public void clickViewCartLink(String title) {
        searchPage.clickViewCart(title);
    }

    @Step
    public void checkAddedItem(String title) {
        Assert.assertTrue(cartPage.checkAddedItemToCart(title));
    }

    @Step
    public void checkProductPrice() {
        cartPage.checkProductsTotalPrice();
    }

    @Step
    public void clickCartHeader() {
        homePage.clickCartIcon();
    }

    @Step
    public void navigateToDetailsPage(String productName) {
        searchPage.navigateToProductDetailsPage(productName);
    }

    @Step
    public void clickAddProductFromDetailsPage() {
        productDetailsPage.clickAddProductToCart();
    }

    @Step
    public void addProductToCartFromDetailsPage(String productName) {
        navigateToDetailsPage(productName);
        clickAddProductFromDetailsPage();
    }

    @Step
    public void updateProductQuantity(String productName, String newQuantity) {
        cartPage.updateQuantity(productName, newQuantity);
    }

    @Step
    public void verifyUpdatedCart() {
        Assert.assertTrue(cartPage.checkUpdateMessage());
    }

    @Step
    public void verifyUpdatedQuantityInCart(String productName, String newQuantity) {
        cartPage.verifyUpdatedQuantity(productName, newQuantity);
    }

    @Step
    public void checkTotalCartPrice() {
        cartPage.checkTotalPrice();
    }

    @Step
    public void removeItemFromCart(String itemName) {
        cartPage.removeItemFromCart(itemName);
    }

    @Step
    public void checkRemovedItemFromCart(String productName) {
        cartPage.verifyRemovedItemFromCart(productName);
    }

    @Step
    public void emptyProductCart() {
        cartPage.emptyCart();
    }

    @Step
    public void verifyEmptyCart() {
        cartPage.verifyEmptyCart();
    }

    @Step
    public void applyCoupon(String code) {
        cartPage.applyCoupon(code);
    }

    @Step
    public void checkAppliedCoupon(String code) {
        Assert.assertTrue(cartPage.checkAppliedCoupon(code));
    }
}
