package org.fasttrack.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementDescriber;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminCouponsPage extends PageObject {
    @FindBy(css = "#the-list tr")
    private List<WebElementFacade> couponsList;

    @FindBy(css = "td.column-amount")
    private WebElementFacade expirationDate;

    public String compareDates() {
        try {
            String pattern = "MMMM dd, yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));

            for (WebElementFacade item : couponsList) {
                if (simpleDateFormat.parse(item.findBy(By.cssSelector(".expiry_date")).getText()).compareTo(date) > 0) {
                    String title = item.findBy(By.cssSelector("td.coupon_code a.row-title")).getText();
                    return title;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getCouponAmount(String title) {
        for (WebElementFacade item : couponsList) {
            if (item.findBy(By.cssSelector("td.coupon_code a.row-title")).getText().equals(title)) {
                return item.findBy(By.cssSelector("td.column-amount")).getText();
            }
        }
        return "";
    }
}
