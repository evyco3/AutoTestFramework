package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#_username")
    private WebElement email;
    @FindBy(css = "#_password")
    private WebElement password;
    @FindBy(css = "button[type*='submit']")
    private WebElement loginBtn;
    @FindBy(css = "#menu div.item")
    private WebElement successLoginMessage;
    @FindBy(css = ".negative.message p")
    private WebElement failLoginMessage;

    public <T>T login(String email, String password, boolean criteria, Class<T> nextPageClass) {
        try {
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");
            if (criteria) {
                clickWaitForTitleAndNavigate(this.loginBtn, "login button", "Fashion Plus Web Store", "HomePage");
            } else {
                click(this.loginBtn, "login button");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            String errorMsg = "Error occurred during the login operation for user: " + email;
            LoggerUtils.error(getClass(), errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    public String getLoginResponseMessage(String operation) {
        String message;
        if ("valid login data".equalsIgnoreCase(operation)) {
            message = getText(this.successLoginMessage, "success login message");
        } else if ("invalid login data".equalsIgnoreCase(operation)) {
            message = getText(this.failLoginMessage, "fail login message");
        } else {
            String warnMsg = "Illegal operation: " + operation;
            LoggerUtils.warn(getClass(), warnMsg);
            throw new IllegalArgumentException(warnMsg);
        }
        return message;
    }
}
