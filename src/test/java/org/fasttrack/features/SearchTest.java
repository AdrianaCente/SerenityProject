package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.AdminPostsSteps;
import org.fasttrack.steps.SearchSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchTest {
    private String searchProduct = "Beanie";

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private SearchSteps searchSteps;

    @Steps
    private AdminPostsSteps adminPostsSteps;

    @Test
    public void searchProduct() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName(searchProduct);
        searchSteps.verifiedSearchedProducts("Beanie with Logo");
    }

    @Test
    public void searchNonExistentItem() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("test");
        searchSteps.verifiedNonexistentProduct();
    }

    @Test
    public void searchAndCompareProductPrice() {
        driver.manage().window().maximize();
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("t-shirt");
        searchSteps.selectSortOption("price");
        searchSteps.verifySelectedSortOption();
    }

    @Test
    public void searchAndCompareProductPriceOnColumns() {
        driver.manage().window().maximize();
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("t-shirt");
        searchSteps.selectSortOption("price");
        searchSteps.verifyProductPriceOnColumns();
    }

    @Test
    public void searchProductWithEnterKey() {
        driver.manage().window().maximize();
        searchSteps.searchProductByNameByPressingEnter(searchProduct);
        searchSteps.checkProductPost("Beanie with Logo");
    }
}
