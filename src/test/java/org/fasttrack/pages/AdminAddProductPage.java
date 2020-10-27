package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

@DefaultUrl("http://qa4.fasttrackit.org:8008/wp-admin/post-new.php?post_type=product")
public class AdminAddProductPage extends PageObject {

    @FindBy(css = "#title")
    private WebElementFacade productName;

    @FindBy(css = "textarea#content")
    private WebElementFacade productDescription;

    @FindBy(css = "#_regular_price")
    private WebElementFacade regularPrice;

    @FindBy(css = ".inventory_options a")
    private WebElementFacade inventoryTab;

    @FindBy(css = "#_manage_stock")
    private WebElementFacade manageStockInput;

    @FindBy(css = "#_stock")
    private WebElementFacade stockQuantity;

    @FindBy(css = "#publish")
    private WebElementFacade publishButton;

    @FindBy(css = "#set-post-thumbnail")
    private WebElementFacade productImageLink;

    @FindBy(css = "[aria-label='vneck-tee-2.jpg']")
    private WebElementFacade productImage;

    @FindBy(css = ".media-toolbar-primary button")
    private WebElementFacade imageButton;

    @FindBy(css = "li[id^='product_cat']")
    private List<WebElementFacade> categoryList;

    public void setProductName(String name) {
        waitFor(productName);
        typeInto(productName, name);
    }

    public void setProductDescription(String description) {
        typeInto(productDescription, description);
    }

    public void setProductPrice(String price) {
        typeInto(regularPrice, price);
    }

    public void clickInventory() {
        clickOn(inventoryTab);
    }

    public void clickManageStock() {
        clickOn(manageStockInput);
    }

    public void setProductQuantity(String qty) {
        waitFor(stockQuantity);
        typeInto(stockQuantity, qty);
    }

    public void clickPublish() {
        clickOn(publishButton);
    }

    public void clickAddProductImage() {
        clickOn(productImageLink);
    }

    public void addImageFromModal() {
        clickAddProductImage();
        clickOn(productImage);
        clickOn(imageButton);
    }

    public void selectCategory(String category) {
        for (WebElementFacade item : categoryList) {
            if (item.findBy(By.cssSelector("label")).getText().equals(category)) {
                clickOn(item.findBy(By.cssSelector("label")));
                break;
            }
        }
    }
}
