package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.steps.CartSteps;
import org.fasttrack.steps.LoginSteps;
import org.fasttrack.steps.ReviewSteps;
import org.fasttrack.steps.SearchSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ReviewTest {

    private String[] errorMessages = {"ERROR: please fill the required fields (name, email).",
            "ERROR: please type a comment.",
            "ERROR: please enter a valid email address."};

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private ReviewSteps reviewSteps;

    @Steps
    private SearchSteps searchSteps;

    @Steps
    private CartSteps cartSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void addReview() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetails("Review test comment", "Review author", "author@yahoo.com");
        reviewSteps.checkReview();
    }

    @Test
    public void addReviewWithLoggedInUser() {
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetailsWhileLoggedIn("Review test comment with logged in user");
        reviewSteps.checkReview();
    }

    @Test
    public void addReviewWithoutAuthorFieldFilledIn() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetails("Review test comment", "", "author@yahoo.com");
        reviewSteps.checkMandatoryFields(errorMessages, 0, driver);
    }

    @Test
    public void addReviewWithoutEmailFieldFilledIn() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetails("Review test comment", "Review author", "");
        reviewSteps.checkMandatoryFields(errorMessages, 0, driver);
    }

    @Test
    public void addReviewWithoutValidEmailFieldFilledIn() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetails("Review test comment", "Review author", "author@yahoo");
        reviewSteps.checkMandatoryFields(errorMessages, 2, driver);
    }


    @Test
    public void addReviewWithoutCommentFieldFilledIn() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterReviewDetails("", "Review author", "author@yahoo.com");
        reviewSteps.checkMandatoryFields(errorMessages, 1, driver);
    }

    @Test
    public void addReviewWithoutRatings() {
        searchSteps.navigateToSearch();
        searchSteps.searchProductByName("Beanie");
        cartSteps.navigateToDetailsPage("Beanie with Logo");
        reviewSteps.navigateToReviewSection();
        reviewSteps.enterInputFieldsWithoutRatings("Review test comment", "Review author", "author@yahoo.com", errorMessages, 0, driver);
    }
}
