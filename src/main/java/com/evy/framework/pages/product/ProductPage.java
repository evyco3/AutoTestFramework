package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.checkout.CartPage;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "#sylius_add_to_cart_cartItem_variant_jeans_size")
    private WebElement productSize;
    @FindBy(css = "#sylius_add_to_cart_cartItem_quantity")
    private WebElement productQuantity;
    @FindBy(css = "button[type*='mit']")
    private WebElement addToCartBtn;


    public ProductPage setProductSize(String productSize){
        selectByVisibleText(this.productSize,productSize,"product size");
        return this;
    }

    public ProductPage setProductQuantity(String productQuantity){
        sendKeys(this.productQuantity,productQuantity,"product quantity");
        return this;
    }

    public CartPage clickAddToCartBtn(){
      clickWaitForTitleAndNavigate(this.addToCartBtn,"add to cart button","Your shopping cart ","CartPage");
      return new CartPage();
    }


}
