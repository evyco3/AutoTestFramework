package com.evy.test;

import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Shopping Cart")
@Feature("Add Product to Cart")
public class AddProductToCartTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"productSize", "productQuantity", "expectedMessage"})
    @Story("User adds a product to the cart with specified size and quantity")
    @Description("Verifies that the user can select a product with a specific size and quantity, add it to the cart, and receive a success message.")
    public void testUserSetProductAttributeAndAddProductToCart(String productSize, String productQuantity, String expectedMessage) {
        String actualMessage = performSetProductAttributeAndAddProductToCart(productSize, productQuantity);
        AssertionUtils.assertThatString(actualMessage).equalsTo(expectedMessage);
    }




    private String performSetProductAttributeAndAddProductToCart(String productSize, String productQuantity) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectProductCategories("Jeans", "Men")
                .selectProductByName("Basic regular")
                .setProductSize(productSize)
                .setProductQuantity(productQuantity)
                .clickAddToCartBtn()
                .getSuccessAddToCartMessage();
    }





    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"S","1", "Item has been added to cart"},
                {"M","1", "Item has been added to cart"},
                {"L","1", "Item has been added to cart"},
                {"XL","1", "Item has been added to cart"},
                {"XXL","1", "Item has been added to cart"},
        };
    }
}
