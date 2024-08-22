package com.evy.test;

import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.account.LoginPage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"email", "password", "operation", "expectedMessage"})
    @Story("User attempts to log in with various credentials")
    @Description("Tests different login scenarios including valid and invalid credentials.")
    public void testUserLoginScenarios(String email, String password, String operation, String expectedMessage) {
        String actualMessage = performLogin(email, password, operation);
        AssertionUtils.assertThatString(actualMessage).contains(expectedMessage);
    }

    /**
     * Performs the login operation and returns the login response message.
     *
     * @param email       The email to log in with.
     * @param password    The password to log in with.
     * @param operation   The type of operation being tested (e.g., valid or invalid login).
     * @return The login response message after attempting to log in.
     */
    private String performLogin(String email, String password, String operation) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .getLoginResponseMessage(operation);
    }

    @DataProvider(name = "data")
    private  Object[][] getData() {
        return new Object[][]{
                {"fashion@example.com", "sylius","valid login data", "hello"},
                {"fashion@example.com", "wrongPassword","invalid login data", "invalid credentials."},
                {"wrongEmail@example.com", "sylius","invalid login data", "invalid credentials."},
                {"wrongEmail@example.com", "wrongPassword","invalid login data", "invalid credentials."},
                {"fashion@example.com", "", "invalid login data", "invalid credentials."},
                {"", "sylius", "invalid login data", "invalid credentials."},
                {"", "", "invalid login data", "invalid credentials."}
        };
    }
}
