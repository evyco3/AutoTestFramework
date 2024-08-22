package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class
NavigateToProductDropdown extends BasePage {

    public ProductListingPage selectProductCategories(String mainCategory,String subCategory){
        try {

            if("Dresses".equalsIgnoreCase(mainCategory)){
                WebElement element=driver.findElement(By.xpath("//a[normalize-space()='Dresses']"));
                click(element,mainCategory);
            }
            else{
                String mainCategoryStrValue=String.format("//div[@class='ui large stackable menu']//span[normalize-space()='%s']",mainCategory);
                WebElement mainCategoryElement=driver.findElement(By.xpath(mainCategoryStrValue));

                String subCategoryStrValue=String.format("//div[@class='ui large stackable menu']//span[normalize-space()='%s']" +
                        "/parent::div//a[normalize-space()='%s']",mainCategory,subCategory);
                WebElement subCategoryElement=driver.findElement(By.xpath(subCategoryStrValue));
                click(mainCategoryElement,mainCategory);
                click(subCategoryElement,subCategory);
            }
            return new ProductListingPage();

        }catch (Exception e){
            String errorMessage = String.format("Failed to select category: '%s' -> '%s'.", mainCategory, subCategory);
            LoggerUtils.error(getClass(), errorMessage, e);
            throw new RuntimeException(errorMessage, e);

        }
    }


}
