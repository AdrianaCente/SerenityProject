package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.List;
import java.util.Random;

public class AdminAddPostPage extends PageObject {
    @FindBy(css = "#title")
    private WebElementFacade postTitle;

    @FindBy(css = "textarea#content")
    private WebElementFacade postContent;

    @FindBy(css = "#publishing-action")
    private WebElementFacade publishButton;

    @FindBy(xpath = "//label[text()=' Uncategorized']")
    private WebElementFacade selectCategory;

    public void setPostTitle(String title) {
        waitFor(postTitle);
        typeInto(postTitle, title);
    }

    public void setPostContent(String content) {
        waitFor(postContent);
        typeInto(postContent, content);
    }

    public void clickSetCategory() {
        clickOn(selectCategory);
    }

    public void clickPublishButton() {
        waitFor(publishButton);
        clickOn(publishButton);
    }
}
