package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class SearchPage extends PageObject {

    @FindBy(css = ".product")
    private List<WebElementFacade> productNameList;

    @FindBy(css = "[class*='azera_shop_grid_column']")
    private List<WebElementFacade> productListColumns;

    @FindBy(css = ".woocommerce-info")
    private WebElementFacade noItemSearchMessage;

    @FindBy(css = "button[name='add-to-cart']")
    private WebElementFacade addToCartButton;

    @FindBy(css = "[name='orderby']")
    private WebElementFacade sortOptions;

    @FindBy(xpath = "//span/span[@class='woocommerce-Price-amount amount'] | //ins/span[@class='woocommerce-Price-amount amount']")
    private WebElementFacade productPrice;

    public boolean checkSearchedProductName(String title) {
        for (WebElementFacade item : productNameList) {
            if (item.findBy(By.cssSelector(".woocommerce-loop-product__title")).getText().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void checkSearchedMessage() {
        noItemSearchMessage.shouldContainText("No products were found matching your selection.");
    }

    public void addProductToCart(String title) {
        Boolean isPresent = false;
        for (WebElementFacade item : productNameList) {
            isPresent = item.findElements(By.cssSelector(".summary")).size() > 0;
            if (isPresent) {
                clickOn(addToCartButton);
                break;
            } else {
                if (item.findBy(By.cssSelector(".woocommerce-loop-product__title")).getText().equals(title)) {
                    clickOn(item.findElement(By.cssSelector(".product a.button")));
                    break;
                }
            }
        }
    }

    public void clickViewCart(String title) {
        for (WebElementFacade item : productNameList) {
            if (item.findBy(By.cssSelector(".woocommerce-loop-product__title")).getText().contains(title)) {
                clickOn(item.findElement(By.cssSelector("a[title='View cart']")));
                break;
            }
        }
    }

    public void navigateToProductDetailsPage(String productName) {
        for (WebElementFacade item : productNameList) {
            if (item.findBy(By.cssSelector(".woocommerce-loop-product__title")).getText().equals(productName)) {
                clickOn(item.findElement(By.cssSelector(".woocommerce-LoopProduct-link")));
                break;
            }
        }
    }

    public void selectOption(String option) {
        sortOptions.selectByValue(option);
    }

    public int getFormattedValue(WebElementFacade resultedItem, String selectorValue) {
        String stringPrice = resultedItem.find(By.cssSelector(selectorValue)).getText().replaceAll(",", ".");
        Double doubleFormatPrice = Double.valueOf(stringPrice.substring(0, stringPrice.length() - 4).trim());
        Integer castPriceInteger = doubleFormatPrice.intValue();
        return castPriceInteger;
    }

    public boolean checkProductPrice() {
        Integer castItemOne = getFormattedValue(productNameList.get(0), ".woocommerce-Price-amount ");
        Integer castLastItem = getFormattedValue(productNameList.get(productNameList.size() - 1), ".woocommerce-Price-amount ");
        if (castItemOne <= castLastItem) {
            return true;
        }
        return false;
    }

    public boolean checkProductPriceOnColumns() {
        int index = 0;
        for (int i = 0; i < productListColumns.size() - 1; i++) {
            if ((productListColumns.get(i).findElements(By.cssSelector("li"))).size() > (productListColumns.get(i + 1).findElements(By.cssSelector("li"))).size()) {
                index = i;
            } else {
                index = i + 1;
            }
        }
        Integer castItemOne = getFormattedValue(productNameList.get(0), ".woocommerce-Price-amount ");
        List<WebElement> liElementsInColumn = productListColumns.get(index).findElements(By.cssSelector("li"));
        WebElement elementInColumn = liElementsInColumn.get(liElementsInColumn.size() - 1);
        String stringPrice = elementInColumn.findElement(By.cssSelector(".woocommerce-Price-amount ")).getText().replaceAll(",", ".");
        Double doubleFormatPrice = Double.valueOf(stringPrice.substring(0, stringPrice.length() - 4).trim());
        Integer castLastItem = doubleFormatPrice.intValue();
        System.out.println(castItemOne);
        System.out.println(castLastItem);
        if (castItemOne <= castLastItem) {
            return true;
        }

        return false;
    }
}
