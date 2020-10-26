package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AdminHomePage extends PageObject {
    @FindBy(css = "#wp-admin-bar-my-account>a>span.display-name")
    private WebElementFacade helloMessage;

    @FindBy(xpath = "//div[text()='Products']")
    private WebElementFacade productsMenu;

    @FindBy(xpath = "//div[text()='WooCommerce']")
    private WebElementFacade wooCommerceMenu;

    @FindBy(xpath = "//div[text()='Posts']")
    private WebElementFacade postsMenu;

    @FindBy(xpath = "//li[@id='menu-posts-product']//a[text()='Add New']")
    private WebElementFacade addNewProductSubmenu;

    @FindBy(xpath = "//li[@id='menu-posts']//a[text()='Add New']")
    private WebElementFacade addNewPostSubmenu;

    @FindBy(xpath = "//li//a[text()='Coupons']")
    private WebElementFacade couponsSubmenu;

    public void checkSuccessLogin(String name) {
        helloMessage.equals("name");
    }


    public void clickProductsMenu() {
        clickOn(productsMenu);
    }

    public void clickAddProduct() {
        clickOn(addNewProductSubmenu);
    }

    public void clickWooCommerceMenu() {
        clickOn(wooCommerceMenu);
    }

    public void clickCouponSubmenu() {
        clickOn(couponsSubmenu);
    }

    public void clickPostsMenu() {
        clickOn(postsMenu);
    }

    public void clickAddPostSubmenu() {
        clickOn(addNewPostSubmenu);
    }
}
