package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigateToAuthentication extends BasePage {

    @FindBy(css = "a[href*='login']")
    private WebElement loginBtn;
    @FindBy(css = "a[href*='register']")
    private WebElement registerBtn;


    public LoginPage navigateToLoginPage(){
        clickWaitForTitleAndNavigate(this.loginBtn,"login button","login","LoginPage");
        return new LoginPage();
    }

    public RegistrationPage navigateToRegistrationPage(){
        clickWaitForTitleAndNavigate(this.registerBtn,"register button","Register","RegisterPage");
        return new RegistrationPage();
    }
}
