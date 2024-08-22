package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    @FindBy(css = "#checkout_billingAddress_firstName")
    private WebElement firstname;
    @FindBy(css = "#checkout_billingAddress_lastName")
    private WebElement lastname;
    @FindBy(css = "#checkout_billingAddress_street")
    private WebElement address;
    @FindBy(css = "#checkout_billingAddress_countryCode")
    private WebElement country;
    @FindBy(css = "#checkout_billingAddress_city")
    private WebElement city;
    @FindBy(css = "#checkout_billingAddress_postcode")
    private WebElement postcode;
    @FindBy(css = "label[for='checkout_shipments_0_method_2']")
    private WebElement shipmentFedex;
    @FindBy(css = "label[for='checkout_payments_0_method_1']")
    private WebElement bankTransfer;
    @FindBy(css = "button[type*='mit']")
    private WebElement placeOrderBtn;



    public CheckoutPage setFirstName(String firstname){
        sendKeys(this.firstname,firstname,"firstname");
        return this;
    }

    public CheckoutPage setLastName(String lastName){
        sendKeys(this.lastname,lastName,"lastname");
        return this;
    }

    public CheckoutPage setAddress(String address){
        sendKeys(this.address,address,"address");
        return this;
    }

    public CheckoutPage setCountry(String country){
        selectByVisibleText(this.country,country,"country");
        return this;
    }

    public CheckoutPage setCity(String city) {
        sendKeys(this.city,city,"city");
        return this;
    }

    public CheckoutPage setPostcode(String postcode){
        sendKeys(this.postcode,postcode,"postcode");
        return this;
    }

    public CheckoutPage clickShipmentFedexMethod(){
        click(this.shipmentFedex,"shipment fedex");
        return this;
    }

    public CheckoutPage clickBankTransferMethod(){
        click(this.bankTransfer,"bank transfer");
        return this;
    }

    public OrderPage clickPlaceOrderBtn(){
        if(!shipmentFedex.isSelected()){
            click(this.shipmentFedex,"shipment fedex");
            click(this.placeOrderBtn,"place order button");
            return new OrderPage();
        }
       return null;
    }

}
