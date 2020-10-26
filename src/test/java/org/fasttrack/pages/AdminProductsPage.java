package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AdminProductsPage extends PageObject {
    @FindBy(css = "#the-list tr")
    private List<WebElementFacade> productsList;

    public void findElementByName(String name, WebDriver driver) {
        for (WebElementFacade item : productsList) {
            if (item.findBy(By.cssSelector(".name>strong>a")).getText().equals(name)) {
                Actions action = new Actions(driver);
                action.moveToElement(item).perform();
                clickOn(item.findBy(By.cssSelector(".trash a")));
                break;
            }
        }
    }
}
