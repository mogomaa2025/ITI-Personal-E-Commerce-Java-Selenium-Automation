package Tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;


@Listeners({Listners.TestNGListners.class, AllureTestNg.class})
@Epic("ITI E-Commerce Website")
@Feature("UI Products")
@Story("Products Search")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToProductPage")
public class ProductsSearchTests extends BaseTestClass {
    

    @Description("TC_SRCH_001_SearchProduct_All")
    @Test(priority = 1, groups =  {"invalid"}, dataProvider = "validSearchData1")
    public void TC_SRCH_001_SearchProduct_All(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .productGridsContainPricesInRange(Double.parseDouble(minPrice), Double.parseDouble(maxPrice)), "Unexpected Text");
    }

    @Description("TC_SRCH_002_SearchProduct_With_Electronics_Category")
    @Test(priority = 2, groups =  {"valid"}, dataProvider = "validSearchData2")
    public void TC_SRCH_002_SearchProduct_With_Electronics_Category(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .productGridsContainPricesInRange(Double.parseDouble(minPrice), Double.parseDouble(maxPrice)), "Unexpected Text")
                 .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }

    @Description("TC_SRCH_003_SearchProduct_With_Jeans")
    @Test(priority = 3, groups =  {"valid"}, dataProvider = "validSearchData3")
    public void TC_SRCH_003_SearchProduct_With_Jeans(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }

    @Description("TC_SRCH_004_SearchProduct_With_Python")
    @Test(priority = 4, groups =  {"valid"}, dataProvider = "validSearchData4")
    public void TC_SRCH_004_SearchProduct_With_Python(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertEqual(products
                 .getFirstProductCategoryName(), category, "Unexpected Category");
    }

    @Description("TC_SRCH_005_SearchProduct_With_NonExistProduct")
    @Test(priority = 5, groups =  {"invalid"}, dataProvider = "invalidSearchData1")
    public void TC_SRCH_005_SearchProduct_With_NonExistProduct(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .isNoProducts(), "Unexpected Text");
    }

    //negative test
    @Description("TC_SRCH_006_SearchProduct_With_NonExistProduct")
    @Test(priority = 6, groups =  {"invalid"}, dataProvider = "invalidSearchData2")
    public void TC_SRCH_006_SearchProduct_With_NonExistProduct(String search, String category, String minPrice, String maxPrice) {
    products     .performSearch(search, category, minPrice, maxPrice);
    Assertions   .assertTrue(products
                 .isNoProducts(), "Unexpected Text");
    }

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
