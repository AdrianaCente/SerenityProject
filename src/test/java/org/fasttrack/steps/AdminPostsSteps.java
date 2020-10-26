package org.fasttrack.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.pages.*;
import org.junit.Assert;

public class AdminPostsSteps {
    private AdminAddPostPage adminAddPostPage;
    private AdminHomePage adminHomePage;
    private HomePage homePage;

    @Step
    public void navigateToPostMenu() {
        adminHomePage.clickPostsMenu();
        adminHomePage.clickAddPostSubmenu();
    }

    @Step
    public void addNewPost(String title, String content) {
        adminAddPostPage.setPostTitle(title);
        adminAddPostPage.setPostContent(content);
        adminAddPostPage.clickSetCategory();
    }

    @Step
    public void publicPost() {
        adminAddPostPage.clickPublishButton();
    }

    @Step
    public void checkAddedPost(String title) {
        homePage.open();
        Assert.assertTrue(homePage.checkAddedPost(title));
    }
}
