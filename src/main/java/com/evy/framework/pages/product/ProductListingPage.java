package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListingPage extends BasePage {

    public ProductPage selectProductByName(String productName) {
        try {
            String productStrValue = String.format("//div[@id='products']//a[normalize-space()='%s']", productName);
            WebElement productElement = driver.findElement(By.xpath(productStrValue));
            clickWaitForTitleAndNavigate(productElement, productName, productName, "ProductPage");
            return new ProductPage();
        } catch (Exception e) {
            String errorMessage = "Failed to select the product with name: " + productName;
            LoggerUtils.error(getClass(), errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
