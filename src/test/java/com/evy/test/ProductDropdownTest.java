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
@Feature("Product Dropdown Functionality")
public class ProductDropdownTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"mainCategory", "subCategory", "expectedUrl"})
    @Story("User selects categories from the dropdown")
    @Description("Verifies that selecting a category and subcategory from the product dropdown navigates to the correct URL.")
    public void testUserSelectCategoriesFromDropdown(String mainCategory, String subCategory, String expectedUrl) {
        String actualUrl = performSelectCategoriesFromProductDropdown(mainCategory, subCategory);
        AssertionUtils.assertThatString(actualUrl).contains(expectedUrl);
    }

    /**
     * Performs the selection of categories from the product dropdown and returns the current URL.
     *
     * @param mainCategory The main category to select (e.g., "Caps").
     * @param subCategory  The subcategory to select (e.g., "Simple").
     * @return The current URL after the category selection.
     */
    private String performSelectCategoriesFromProductDropdown(String mainCategory, String subCategory) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectProductCategories(mainCategory, subCategory)
                .getCurrentUtl();
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"Caps", "Simple", "/caps/simple"},
                {"Caps", "With pompons", "/caps/with-pompons"},
                {"Dresses", "", "/dresses"},
                {"Jeans", "Men", "/jeans/men"},
                {"Jeans", "Women", "/jeans/women"},
                {"T-Shirts", "Men", "/t-shirts/men"},
                {"T-Shirts", "Women", "/t-shirts/women"},
        };
    }
}
