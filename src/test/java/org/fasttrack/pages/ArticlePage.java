package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class ArticlePage extends PageObject {

    @FindBy(css = "textarea#comment")
    private WebElementFacade comment;

    @FindBy(css = "input#author")
    private WebElementFacade author;

    @FindBy(css = "input#email")
    private WebElementFacade email;

    @FindBy(css = "input#submit")
    private WebElementFacade submit;

    @FindBy(css = ".vcard+em")
    private WebElementFacade commentMessage;

    public void setComment(String text) {
        typeInto(comment, text);
    }

    public void setAuthor(String authorComment) {
        typeInto(author, authorComment);
    }

    public void setEmail(String emailComment) {
        typeInto(email, emailComment);
    }

    public void clickSubmitComment() {
        clickOn(submit);
    }

    public void verifyAddedComment() {
        waitFor(commentMessage);
        Assert.assertTrue(commentMessage.getText().equals("Your comment is awaiting moderation."));
    }
}
