package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.ArticlePage;
import org.fasttrack.pages.HomePage;

public class CommentsSteps {
    private HomePage homePage;
    private ArticlePage articlePage;

    @Step
    public void navigateToBlogPage() {
        homePage.open();
        homePage.clickHomeLink();
    }

    @Step
    public void navigateToArticleByTitle(String title) {
        homePage.findArticleByTitle(title);
    }

    @Step
    public void addComment(String comment, String author, String email) {
        articlePage.setComment(comment);
        articlePage.setAuthor(author);
        articlePage.setEmail(email);
        articlePage.clickSubmitComment();
    }

    @Step
    public void addCommentWhileLoggedIn(String comment) {
        articlePage.setComment(comment);
        articlePage.clickSubmitComment();
    }

    @Step
    public void verifyAddedComment() {
        articlePage.verifyAddedComment();
    }
}
