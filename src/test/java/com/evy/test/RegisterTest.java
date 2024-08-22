package com.evy.test;

import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.account.RegistrationPage;
import com.evy.framework.utils.AssertionUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("User Registration")
@Feature("Registration Functionality")
public class RegisterTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test(dataProvider = "data")
    @Parameters({"firstname", "lastname", "email", "password", "confirmation", "operation", "expectedMessage"})
    @Story("User attempts to register with various details")
    @Description("Tests different registration scenarios including valid and invalid registration details.")
    public void testUserRegistrationScenarios(String firstname, String lastname, String email, String password, String confirmation, String operation, String expectedMessage) {
        String actualMessage = performRegistration(firstname, lastname, email, password, confirmation, operation);
        AssertionUtils.assertThatString(actualMessage).equalsTo(expectedMessage);
    }

    /**
     * Performs the registration operation and returns the registration response message.
     *
     * @param firstname    The first name of the user.
     * @param lastname     The last name of the user.
     * @param email        The email to register with.
     * @param password     The password to register with.
     * @param confirmation The confirmation password (if applicable).
     * @param operation    The type of operation being tested (e.g., valid or invalid registration).
     * @return The registration response message after attempting to register.
     */
    private String performRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegistrationPage()
                .register(firstname, lastname, email, password, confirmation, false, RegistrationPage.class)
                .getRegistrationResponseMessage(operation);
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"evy", "tester", faker.internet().emailAddress(), "Password123", "Password123", "valid registration data", "success"},
                {"evy", "tester", "fashion@example.com", "Password123", "Password123", "invalid registration data", "this email is already used."},
                {"evy", "tester", "user@", "Password123", "Password123", "invalid registration data", "this email is invalid."},
                {"evy", "tester", faker.internet().emailAddress(), "password", "mismatchPassword", "invalid registration data", "the entered passwords don't match"},
                {"evy", "tester", faker.internet().emailAddress(), "123", "123", "invalid registration data", "password must be at least 4 characters long."},


        };
    }
}
