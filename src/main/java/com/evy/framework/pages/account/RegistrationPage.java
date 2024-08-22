package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    @FindBy(css = "#sylius_customer_registration_firstName")
    private WebElement firstname;
    @FindBy(css = "#sylius_customer_registration_lastName")
    private WebElement lastname;
    @FindBy(css = "#sylius_customer_registration_email")
    private WebElement email;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_first")
    private WebElement password;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_second")
    private WebElement confirmation;
    @FindBy(css = "button[type*='mit']")
    private WebElement registerBtn;
    @FindBy(css = ".positive.message .header")
    private WebElement successRegistrationMessage;
    @FindBy(css = ".sylius-validation-error")
    private WebElement errorRegistrationMessage;


    public <T>T register(String firstname,String lastname,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){
        try {
            sendKeys(this.firstname,firstname,"firstname");
            sendKeys(this.lastname,lastname,"lastname");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");

            if(criteria){
                clickWaitForTitleAndNavigate(this.registerBtn,"register button","Fashion Plus Web Store","HomePage");
            }
            else{
                click(this.registerBtn,"register button");
            }

            return nextPageClass.getDeclaredConstructor().newInstance();

        }catch (Exception e){
            String errorMsg = "Error occurred during the registration operation";
            LoggerUtils.error(getClass(),errorMsg,e);
            throw new RuntimeException(errorMsg,e);
        }
    }


    public String getRegistrationResponseMessage(String operation){
        if("valid registration data".equalsIgnoreCase(operation)){
            return getText(this.successRegistrationMessage,"success registration message");
        }
        else if("invalid registration data".equalsIgnoreCase(operation)){
            return getText(this.errorRegistrationMessage,"error registration message");
        }
        else{
            String warnMsg = "Illegal operation: " + operation;
            LoggerUtils.warn(getClass(), warnMsg);
            throw new IllegalArgumentException(warnMsg);
        }
    }
}
