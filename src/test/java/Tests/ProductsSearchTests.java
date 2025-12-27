package Tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;

/**
 * Test class for product search functionality in the ITI E-Commerce Website.
 * Extends BaseTestClass to inherit common test setup and tear down methods.
 *
 * @Listeners - Configures TestNG listeners for test execution reporting
 * @Epic - Defines the epic for these tests (ITI E-Commerce Website)
 * @Feature - Defines the feature under test (UI Products)
 * @Story - Defines the user story being tested (Products Search)
 * @Severity - Defines the severity level of these tests (CRITICAL)
 * @Owner - Defines the owner of these tests (Gomaa)
 * @Test - Marks this class as a TestNG test class and specifies the test group
 */
@Listeners({Listners.TestNGListners.class, AllureTestNg.class})
@Epic("ITI E-Commerce Website")
@Feature("UI Products")
@Story("Products Search")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToProductPage")
public class ProductsSearchTests extends BaseTestClass {
    
    /**
     * Test case for searching products with all criteria
     * Verifies that products displayed are within the specified price range
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    @Description("TC_SRCH_001_SearchProduct_All")
    @Test(priority = 1, groups =  {"invalid"}, dataProvider = "validSearchData1")
    public void TC_SRCH_001_SearchProduct_All(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .productGridsContainPricesInRange(Double.parseDouble(minPrice), Double.parseDouble(maxPrice)), "Unexpected Text");
    }
    
    /**
     * Test case for searching products in Electronics category
     * Verifies that products displayed are within the specified price range
     * and belong to the Electronics category
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    @Description("TC_SRCH_002_SearchProduct_With_Electronics_Category")
    @Test(priority = 2, groups =  {"valid"}, dataProvider = "validSearchData2")
    public void TC_SRCH_002_SearchProduct_With_Electronics_Category(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .productGridsContainPricesInRange(Double.parseDouble(minPrice), Double.parseDouble(maxPrice)), "Unexpected Text")
                 .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }
    /**
     * Test case for searching products in Jeans category
     * Verifies that products displayed belong to the Jeans category
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    @Description("TC_SRCH_003_SearchProduct_With_Jeans")
    @Test(priority = 3, groups =  {"valid"}, dataProvider = "validSearchData3")
    public void TC_SRCH_003_SearchProduct_With_Jeans(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }
    /**
     * Test case for searching products related to Python
     * Verifies that products displayed belong to the correct category
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    @Description("TC_SRCH_004_SearchProduct_With_Python")
    @Test(priority = 4, groups =  {"valid"}, dataProvider = "validSearchData4")
    public void TC_SRCH_004_SearchProduct_With_Python(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }
    /**
     * Negative test case for searching with non-existent product
     * Verifies that no products are displayed when searching for non-existent items
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    @Description("TC_SRCH_005_SearchProduct_With_NonExistProduct")
    @Test(priority = 5, groups =  {"invalid"}, dataProvider = "invalidSearchData1")
    public void TC_SRCH_005_SearchProduct_With_NonExistProduct(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .isNoProducts(), "Unexpected Text");
    }
    /**
     * Negative test case for searching with invalid criteria
     * Verifies that no products are displayed when using invalid search criteria
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
    //negative test
    @Description("TC_SRCH_006_SearchProduct_With_NonExistProduct")
    @Test(priority = 6, groups =  {"invalid"}, dataProvider = "invalidSearchData2")
    public void TC_SRCH_006_SearchProduct_With_NonExistProduct(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .isNoProducts(), "Unexpected Text");
    }
    /**
     * Test case for verifying the clear button functionality
     * Verifies that clicking the clear button resets the search results
     *
     * @param search - Search term
     * @param category - Product category
     * @param minPrice - Minimum price filter
     * @param maxPrice - Maximum price filter
     */
     //clear button test
     @Description("TC_SRCH_007_SearchProduct_With_ClearButton")
     @Test(priority = 7, groups =  {"valid"}, dataProvider = "invalidSearchData2")
     public void TC_SRCH_007_SearchProduct_With_ClearButton(String search, String category, String minPrice, String maxPrice) {
     products     .performSearch(search, category, minPrice, maxPrice);
     Assertions   .assertTrue(products
                  .isNoProducts(), "Unexpected Text");
     products     .clickClearButton();
     Assertions   .assertTrue(products
                  .isProductExist(), "Unexpected Text");
     }
}
