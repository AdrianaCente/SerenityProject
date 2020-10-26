package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPage extends PageObject {

    @FindBy(css = ".product")
    private List<WebElementFacade> productNameList;

    @FindBy(css = ".woocommerce-info")
    private WebElementFacade noItemSearchMessage;

    @FindBy(css = "button[name='add-to-cart']")
    private WebElementFacade addToCartButton;

    @FindBy(css = "[name='orderby']")
    private WebElementFacade sortOptions;

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
            return  true;
        }
        return false;
    }
}
