package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.ProductDetailsPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class ReviewSteps {
    private ProductDetailsPage productDetailsPage;

    @Step
    public void navigateToReviewSection() {
        productDetailsPage.clickReviewLink();
    }

    @Step
    public void enterReviewDetails(String comment, String author, String email) {
        productDetailsPage.clickStarRating();
        productDetailsPage.setReviewComment(comment);
        productDetailsPage.setReviewAuthor(author);
        productDetailsPage.setReviewEmail(email);
        productDetailsPage.clickSubmitReview();
    }

    @Step
    public void enterReviewDetailsWhileLoggedIn(String comment) {
        productDetailsPage.clickStarRating();
        productDetailsPage.setReviewComment(comment);
        productDetailsPage.clickSubmitReview();
    }

    @Step
    public void enterInputFieldsWithoutRatings(String comment, String author, String email, String[] errorMessage, int index, WebDriver driver) {
        productDetailsPage.setReviewComment(comment);
        productDetailsPage.setReviewAuthor(author);
        productDetailsPage.setReviewEmail(email);
        productDetailsPage.clickSubmitReview();
        Assert.assertTrue(productDetailsPage.checkMandatoryFields(errorMessage, index, driver));
    }

    @Step
    public void checkReview() {
        Assert.assertTrue(productDetailsPage.verifyReview());
    }

    @Step
    public void checkMandatoryFields(String[] errorMessage, int index, WebDriver driver) {
        Assert.assertTrue(productDetailsPage.checkMandatoryFields(errorMessage, index, driver));
    }
}
