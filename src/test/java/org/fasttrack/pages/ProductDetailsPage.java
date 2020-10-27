package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductDetailsPage extends PageObject {

    @FindBy(css = "button[name='add-to-cart']")
    private WebElementFacade addToCartButton;

    @FindBy(css = "#tab-title-reviews a")
    private WebElementFacade reviewLink;

    @FindBy(css = ".star-4")
    private WebElementFacade starRating;

    @FindBy(css = "#comment")
    private WebElementFacade reviewComment;

    @FindBy(css = "#author")
    private WebElementFacade reviewAuthor;

    @FindBy(css = "#email")
    private WebElementFacade reviewEmail;

    @FindBy(css = "#submit")
    private WebElementFacade submitButton;

    @FindBy(css = ".woocommerce-review__awaiting-approval")
    private WebElementFacade reviewMessage;

    @FindBy(css = "#error-page p:nth-of-type(2)")
    private WebElementFacade mandatoryFieldsErrorMessage;

    public void clickReviewLink() {
        clickOn(reviewLink);
    }

    public void clickStarRating() {
        clickOn(starRating);
    }

    public void setReviewComment(String comment) {
        typeInto(reviewComment, comment);
    }

    public void setReviewAuthor(String author) {
        typeInto(reviewAuthor, author);
    }

    public void setReviewEmail(String email) {
        typeInto(reviewEmail, email);
    }

    public void clickAddProductToCart() {
        clickOn(addToCartButton);
    }

    public void clickSubmitReview() {
        clickOn(submitButton);
    }

    public boolean verifyReview() {
        waitFor(reviewMessage);
        return reviewMessage.getText().equals("Your review is awaiting approval");
    }

    public boolean checkMandatoryFields(String[] mandatoryFieldsMessage, int index) {
        boolean foundAlert = false;
        try {
            getDriver().switchTo().alert();
            foundAlert = true;
        } catch (NoAlertPresentException Ex) {
            foundAlert = false;
        }

        if (foundAlert == true) {
            return true;
        }

        if (mandatoryFieldsErrorMessage.getText().equals(mandatoryFieldsMessage[index])) {
            return true;
        }

        return false;
    }
}
