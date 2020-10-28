package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.HomePage;
import org.fasttrack.pages.SearchPage;
import org.junit.Assert;

public class SearchSteps {
    private HomePage homePage;
    private SearchPage searchPage;

    @Step
    public void navigateToSearch() {
        homePage.open();
        homePage.clickSearchIcon();
    }

    @Step
    public void searchProductByName(String searchText) {
        homePage.setSearchInputField(searchText);
        homePage.clickSearchButton();
    }

    @Step
    public void searchProduct(String itemName) {
        navigateToSearch();
        searchProductByName(itemName);
    }

    @Step
    public void verifiedSearchedProducts(String title) {
        Assert.assertTrue("Product is not on the page.", searchPage.checkSearchedProductName(title));
    }

    @Step
    public void verifiedNonexistentProduct() {
        searchPage.checkSearchedMessage();
    }

    @Step
    public void selectSortOption(String option) {
        searchPage.selectOption(option);
    }

    @Step
    public void verifySelectedSortOption() {
        Assert.assertTrue(searchPage.checkProductPrice());
    }

    @Step
    public void verifyProductPriceOnColumns() {
        Assert.assertTrue(searchPage.checkProductPriceOnColumns());
    }
}
