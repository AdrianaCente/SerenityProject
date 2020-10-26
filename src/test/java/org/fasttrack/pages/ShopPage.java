package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class ShopPage extends PageObject {
    @FindBy(css ="li.product")
    private List<WebElementFacade> productList;

    public boolean checkAddedProduct(String title) {
        if (productList.get(0).findBy(By.cssSelector(".woocommerce-loop-product__title")).getText().equals(title)) {
            return true;
        }
        return false;
    }
}
