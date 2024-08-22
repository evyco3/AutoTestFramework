package com.evy.test;

import com.evy.framework.config.ConfigManager;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("End-to-End Tests")
@Feature("User Journey")
public class EndToEndTest extends BaseTest {

    @Test(dataProvider = "endToEndData")
    @Story("Complete user journey from login to order placement")
    @Description("Test to perform a complete end-to-end journey from logging in, selecting a product, and placing an order.")
    public void testUserPerformEndToEndJourney(String email, String password, String mainCategory, String subCategory,
                                               String productName, String productSize, String productQuantity,
                                               String firstName, String lastName, String address, String country,
                                               String city, String postcode,String expectedMessage)  {


      String actualMessage=  performEndToEnd(email, password, mainCategory, subCategory, productName, productSize, productQuantity,
                firstName, lastName, address, country, city, postcode);

        AssertionUtils.assertThatString(actualMessage).equalsTo(expectedMessage);
    }

    private String performEndToEnd(String email, String password, String mainCategory, String subCategory,
                                 String productName, String productSize, String productQuantity,
                                 String firstName, String lastName, String address, String country,
                                 String city, String postcode)  {
      return   HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToLoginPage()
                .login(email, password, true, HomePage.class)
                .navigateToProductDropdown()
                .selectProductCategories(mainCategory, subCategory)
                .selectProductByName(productName)
                .setProductSize(productSize)
                .setProductQuantity(productQuantity)
                .clickAddToCartBtn()
                .navigateToCheckoutPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setCountry(country)
                .setCity(city)
                .setPostcode(postcode)
                .clickShipmentFedexMethod()
                .clickBankTransferMethod()
                .clickPlaceOrderBtn()
                .getSuccessOrderMessage();
    }

    @DataProvider(name = "endToEndData")
    public Object[][] getEndToEndData() {
        return new Object[][]{
                {
                        ConfigManager.get().email(),
                        ConfigManager.get().password(),
                        "Jeans", "Men",
                        "Basic regular", "XL", "1",
                        "Evy", "Tester", "There 1 Street",
                        "Canada", "VA", "77181","thank you!\n" +
                        "you have successfully placed an order."
                },

        };
    }
}
