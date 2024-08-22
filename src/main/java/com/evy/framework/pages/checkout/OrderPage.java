package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends BasePage {
    @FindBy(css = "#sylius-thank-you")
    private WebElement successOrderMessage;


    public String getSuccessOrderMessage(){
        return getText(this.successOrderMessage,"success order message");
    }
}
