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

@Epic("Product Navigation")
@Feature("Product Selection")
public class ProductListingTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"mainCategory", "subCategory", "productName", "expectedTitle"})
    @Story("User selects a product by name from the listing page")
    @Description("Verifies that the user can select a product by its name from the product listing page and navigate to the correct product page.")
    public void testUserSelectProductByName(String mainCategory, String subCategory, String productName, String expectedTitle) {
        String actualTitle = performSelectProductByName(mainCategory, subCategory, productName);
        AssertionUtils.assertThatString(actualTitle).contains(expectedTitle);
    }

    private String performSelectProductByName(String mainCategory, String subCategory, String productName) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectProductCategories(mainCategory, subCategory)
                .selectProductByName(productName)
                .getTitle();
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"Jeans", "Men", "Basic regular", "Basic regular"},
                {"Jeans", "Men", "Slim fit classic", "Slim fit classic"},
                {"Jeans", "Men", "Regular Fit casual", "Regular Fit casual"},
                {"Jeans", "Men", "Slim fit elegant", "Slim fit elegant"}
        };
    }
}
