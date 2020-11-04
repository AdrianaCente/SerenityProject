package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

@DefaultUrl("http://qa4.fasttrackit.org:8008/")
public class HomePage extends PageObject {

    @FindBy(xpath = "//li[starts-with(@class,'menu-item')]/a[text()='My account']")
    private WebElementFacade accountLink;

    @FindBy(xpath = "//li[starts-with(@class,'menu-item')]/a[text()='Shop']")
    private WebElementFacade shopLink;

    @FindBy(xpath = "//li[starts-with(@class,'menu-item')]/a[text()='Home']")
    private WebElementFacade homeLink;

    @FindBy(css = "article")
    private List<WebElementFacade> articleList;

    @FindBy(css = ".header-search-button")
    private WebElementFacade searchIcon;

    @FindBy(css = "input[title='Search for:']")
    private WebElementFacade searchInputField;

    @FindBy(css = "#search-2 .search-field")
    private WebElementFacade asideSearchInputField;

    @FindBy(css = "input[title='Search for:']+input[type='submit']")
    private WebElementFacade searchButton;

    @FindBy(css = ".fa-shopping-cart")
    private WebElementFacade cartIcon;

    public void clickMyAccountLink(){
        clickOn(accountLink);
    }

    public void clickSearchIcon(){
        clickOn(searchIcon);
    }

    public void setSearchInputField(String textInput) {
        typeInto(searchInputField, textInput);
    }

    public void setSearchInputFieldAndPressEnter(String textInput) {
        asideSearchInputField.sendKeys(textInput + Keys.ENTER);
    }

    public void clickSearchButton() {
        clickOn(searchButton);
    }

    public void clickCartIcon() {
        clickOn(cartIcon);
    }

    public void clickShopLink() {
        clickOn(shopLink);
    }

    public void clickHomeLink() {
        clickOn(homeLink);
    }

    public void findArticleByTitle(String title) {
        for (WebElementFacade item : articleList) {
            if (item.findBy(By.cssSelector(".entry-title a")).getText().equals(title)) {
                clickOn(item);
                break;
            }
        }
    }

    public boolean checkAddedPost(String title) {
        for (WebElementFacade item : articleList) {
            if (item.findBy(By.cssSelector(".entry-title a")).getText().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
}
