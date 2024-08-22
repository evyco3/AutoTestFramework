package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    @FindBy(css = ".positive.message p")
    private WebElement successAddToCartMsg;
    @FindBy(css = "button[name='submit-type']")
    private WebElement proceedToCheckoutPage;




    public String getSuccessAddToCartMessage(){
        return this.successAddToCartMsg.getText();
    }

    public CheckoutPage navigateToCheckoutPage(){
        click(this.proceedToCheckoutPage,"checkout button");
        waitForElementToBeVisible(driver.findElement(By.xpath("//*[normalize-space()='Billing address']")));
        return new CheckoutPage();
    }
}
