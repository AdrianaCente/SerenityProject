package org.fasttrack.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.fasttrack.pages.HomePage;
import org.fasttrack.steps.CommentsSteps;
import org.fasttrack.steps.LoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class CommentsTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private CommentsSteps commentsSteps;

    @Steps
    private LoginSteps loginSteps;

    @Test
    public void addCommentToArticle() {
        commentsSteps.navigateToBlogPage();
        commentsSteps.navigateToArticleByTitle("TEST POST");
        commentsSteps.addComment("Post comment article", "ana", "ana@yahoo.com");
        commentsSteps.verifyAddedComment();
    }

    @Test
    public void addCommentToArticleWhileLoggedIn() {
        loginSteps.logIn("test@yahoo.com","1qaz@WSX3edc");
        commentsSteps.navigateToBlogPage();
        commentsSteps.navigateToArticleByTitle("TEST POST");
        commentsSteps.addCommentWhileLoggedIn("Post comment article, by logged in user ");
        commentsSteps.verifyAddedComment();
    }
}
