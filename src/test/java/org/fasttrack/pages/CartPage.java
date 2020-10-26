package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

public class CartPage extends PageObject {
    @FindBy(css = "tr.cart_item")
    private List<WebElementFacade> cartListItems;

    @FindBy(css = "tr.cart_item .remove")
    private List<WebElementFacade> removeCartItems;

    @FindBy(css = ".product-quantity input")
    private WebElementFacade productQuantity;

    @FindBy(css = ".woocommerce-message")
    private WebElementFacade cartMessage;

    @FindBy(css = ".shipping")
    private WebElementFacade shippingPrice;

    @FindBy(css = ".cart-subtotal")
    private WebElementFacade cartSubtotal;

    @FindBy(css = ".order-total")
    private WebElementFacade totalPrice;

    @FindBy(css = ".cart-empty")
    private WebElementFacade emptyCartMessage;

    @FindBy(css = "[name='update_cart']")
    private WebElementFacade updateButton;

    @FindBy(css = ".checkout-button")
    private WebElementFacade checkoutButton;

    @FindBy(css = "#coupon_code")
    private WebElementFacade couponField;

    @FindBy(css = "[name='apply_coupon']")
    private WebElementFacade couponButton;

    @FindBy(css = ".cart-discount")
    private WebElementFacade discountAmount;

    private String priceSelector = ".woocommerce-Price-amount";

    public boolean checkAddedItemToCart(String title) {
        for (WebElementFacade item : cartListItems) {
            if (item.findBy(By.cssSelector(".product-name a")).getText().equals(title))
                return true;
        }
        return false;
    }

    public int getFormattedNumber(WebElementFacade resultedItem, String selectorValue) {
        String stringPrice = resultedItem.find(By.cssSelector(selectorValue)).getText().replaceAll(",", ".");
        Double doubleFormatPrice = Double.valueOf(stringPrice.substring(0, stringPrice.length() - 4).trim());
        Integer castPriceInteger = doubleFormatPrice.intValue();
        return castPriceInteger;
    }

    public int checkProductTotalPrice(WebElementFacade item, String selectorPrice, String selectorQuantity) {
        Integer individualPrice = getFormattedNumber(item, selectorPrice);
        Integer individualQuantity = Integer.valueOf(item.find(By.cssSelector(selectorQuantity)).getValue());
        return individualPrice * individualQuantity;
    }

    public void checkProductsTotalPrice() {
        for (WebElementFacade item : cartListItems) {
            Integer productTotalPrice = checkProductTotalPrice(item, ".product-price>span", ".product-quantity input");
            Integer productTotal = getFormattedNumber(item, ".product-subtotal>span");
            Assert.assertEquals(productTotalPrice, productTotal);
        }
    }

    public WebElementFacade findCartElementByTitle(String productName) {
        for (WebElementFacade item : cartListItems) {
            if (item.findBy(By.cssSelector(".product-name a")).getText().equals(productName)) {
                return item;
            }
        }
        return null;
    }

    public void updateQuantity(String productName, String newQuantity) {
        WebElementFacade product = findCartElementByTitle(productName);
        typeInto(product.findBy(By.cssSelector(".product-quantity input")), newQuantity + Keys.ENTER);
    }

    public boolean checkUpdateMessage() {
        return cartMessage.getText().equals("Cart updated.");
    }

    public void verifyUpdatedQuantity(String productName, String newQuantity) {
        Assert.assertEquals((findCartElementByTitle(productName).findBy(By.cssSelector(".product-quantity input"))).getValue(), newQuantity);
    }

    public int getProductsTotalPrice() {
        int totalSum = 0;
        for (WebElementFacade item : cartListItems) {
            totalSum += getFormattedNumber(item, ".product-subtotal>span");
        }
        return totalSum;
    }

    public void checkTotalPrice() {
        int shippingTax = getFormattedNumber(shippingPrice, priceSelector);
        int computedTotalPrice = getProductsTotalPrice();
        int cartPriceSubtotal = getFormattedNumber(cartSubtotal, priceSelector);
        int cartPrice = getFormattedNumber(totalPrice, priceSelector);
        Assert.assertEquals(cartPrice, computedTotalPrice + shippingTax);
        Assert.assertEquals(cartPrice, cartPriceSubtotal + shippingTax);
    }

    public void removeItemFromCart(String productName) {
        WebElementFacade product = findCartElementByTitle(productName);
        clickOn(product.findBy(By.cssSelector(".remove")));
    }

//    public void emptyCart() {
//        while (cartListItems.size() > 1) {
//            System.out.println("1=" + (cartListItems.size()));
//            waitFor(cartListItems.get(0));
//            clickOn(cartListItems.get(0).findBy(By.cssSelector(".product-remove a")));
//            waitFor(cartMessage);
//            Assert.assertTrue(cartMessage.getText().contains("removed. Undo?"));
//            waitFor(cartListItems.size());
//            System.out.println("2=" + (cartListItems.size()));
//        }
//        System.out.println("3=" + (cartListItems.size()));
//        waitFor(cartListItems.get(0));
//        clickOn(cartListItems.get(0).findBy(By.cssSelector(".product-remove a")));
//    }

//    public void emptyCart() {
//        for (int i = cartListItems.size(); i > 1; i--) {
//            System.out.println(i);
//            String productName = cartListItems.get(i-1).findBy(By.cssSelector(".product-name a")).getText();
//            removeItemFromCart(productName);
//            waitFor(cartMessage);
//            System.out.println(i);
//        }
//        System.out.println(cartListItems.size());
//        String productName = cartListItems.get(0).findBy(By.cssSelector(".product-name a")).getText();
//        System.out.println(productName);
//        removeItemFromCart(productName);
//    }

    public void emptyCart() {
        for (WebElementFacade item : cartListItems) {
            typeInto(item.findBy(By.cssSelector(".product-quantity input")), "0");
        }
        clickOn(updateButton);
    }

    public void verifyRemovedItemFromCart(String productName) {
        cartMessage.getText().equals("“" + productName + "” removed. Undo?");
    }

    public void verifyEmptyCart() {
        emptyCartMessage.getText().equals("Your cart is currently empty.");
    }

    public void clickCheckoutButton() {
        clickOn(checkoutButton);
    }

    public void applyCoupon(String code) {
        typeInto(couponField, code);
        clickOn(couponButton);
    }

    public boolean checkAppliedCoupon(String code) {
        Integer cartPriceSubtotal = getFormattedNumber(cartSubtotal, priceSelector);
        Integer couponPercentage = Integer.valueOf(code);
        Integer couponComputedAmount = getFormattedNumber(discountAmount, "td span.woocommerce-Price-amount");
        if (cartPriceSubtotal / couponComputedAmount == couponPercentage) {
            return true;
        }
        return false;
    }
}
