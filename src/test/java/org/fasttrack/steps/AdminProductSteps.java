package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AdminProductSteps {
    private AdminHomePage adminHomePage;
    private AdminAddProductPage adminAddProductPage;
    private AdminProductsPage adminProductsPage;
    private ShopPage shopPage;
    private HomePage homePage;
    private SearchPage searchPage;

    @Step
    public void navigateToProductsPage() {
        adminHomePage.clickProductsMenu();
    }

    @Step
    public void addNewProduct(String name, String description, String price, String qty, String category) {
        adminHomePage.clickProductsMenu();
        adminHomePage.clickAddProduct();
        adminAddProductPage.open();
        adminAddProductPage.setProductName(name);
        adminAddProductPage.setProductDescription(description);
        adminAddProductPage.setProductPrice(price);
        adminAddProductPage.clickInventory();
        adminAddProductPage.clickManageStock();
        adminAddProductPage.setProductQuantity(qty);
        adminAddProductPage.addImageFromModal();
        adminAddProductPage.selectCategory(category);
        adminAddProductPage.clickPublish();
    }

    @Step
    public void checkAddedProduct(String name) {
        homePage.open();
        homePage.clickShopLink();
        searchPage.selectOption("date");
        Assert.assertTrue(shopPage.checkAddedProduct(name));
    }

    @Step
    public void removeItem(String name) {
        adminProductsPage.findElementByName(name);
    }

    @Step
    public void checkRemovedItem(String name) {
        homePage.open();
        homePage.clickShopLink();
        searchPage.selectOption("date");
        Assert.assertFalse(shopPage.checkAddedProduct(name));
    }
}
