package com.evy.framework.pages;

import com.evy.framework.driver.Driver;
import com.evy.framework.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(){
        this.driver= Driver.getInstance().get();
        PageFactory.initElements(this.driver,this);
    }


    protected WebElement waitForElementToBeVisible(WebElement element){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void sendKeys(WebElement element, String value, String elementName) {
        ActionUtils.execVoidFunction(getClass(), () -> {
            WebElement webElement = waitForElementToBeVisible(element);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].value = arguments[1];", webElement, value);
            return null;
        }, "Send keys to " + elementName + ": " + value, "Failed to send keys to " + elementName);
    }

    protected void click(WebElement element,String elementName){
        ActionUtils.execVoidFunction(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", webElement);
            return null;
        },"Clicked on "+elementName,"Failed to click on "+elementName);
    }

    protected void waitForPageTitle(String pageTitle){
        ActionUtils.execVoidFunction(getClass(),()->{
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleContains(pageTitle));
            return null;
        },driver.getTitle()+" contains "+pageTitle+" Page Title",pageTitle+ "Is not matched to the current title: "+driver.getTitle());
    }

    protected void clickWaitForTitleAndNavigate(WebElement element,String elementName,String pageTitle,String nextPageClass){
        ActionUtils.execVoidFunction(getClass(),()->{
            click(element,elementName);
            waitForPageTitle(pageTitle);
            return null;
        },"Navigate to "+nextPageClass,"Failed to navigate to "+nextPageClass);
    }


    protected String getText(WebElement element,String elementName){
     return    ActionUtils.execStringFunction(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            return webElement.getText().trim().toLowerCase();
        },"Retrieve text from "+elementName+":"+element.getText().trim(),"Failed to retrieve text from "+elementName);

    }

    protected void moveTo(WebElement element,String elementName){
        ActionUtils.execVoidFunction(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            Actions actions=new Actions(driver);
            actions.moveToElement(webElement).build().perform();
            return null;
        },"Hover on "+elementName,"Failed to hover on "+elementName);
    }

    public String getCurrentUtl(){
        return ActionUtils.execStringFunction(getClass(),()->{
            return driver.getCurrentUrl();
        },"Retrieve current url: "+driver.getCurrentUrl(),"Failed to retrieve current url");
    }

    public String getTitle(){
        return ActionUtils.execStringFunction(getClass(),()->{
            return driver.getTitle();
        },"PageTitle: "+driver.getTitle(),"Failed to retrieve page title");
    }

    protected void selectByVisibleText(WebElement element,String value,String elementName){
        ActionUtils.execVoidFunction(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            Select select=new Select(element);
            select.selectByVisibleText(value);
            return null;
        },"Select "+value+" from "+elementName+" dropdown","Failed to select "+value+" from dropdown");
    }
}
