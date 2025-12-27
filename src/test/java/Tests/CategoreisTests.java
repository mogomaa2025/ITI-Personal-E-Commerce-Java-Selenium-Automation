package Tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;

@Listeners({Listners.TestNGListners.class, AllureTestNg.class})
@Epic("ITI E-Commerce Website")
@Feature("UI Categoreis")
@Story("User Can Categories Products")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToHomePage")
public class CategoreisTests extends BaseTestClass{

    @Description("Test Electronics Category")
    @Test(priority = 1, groups =  {"valid"}, dataProvider = "elecCategoryData")
    public void TC_CAT_001_ElectronicsCategory(String categ, String categProduct) {
     
    category     .clickCategory(categ);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ProductPageLoaded")
                 .assertTrue(category
                 .isProductUrlContains(categ), "Unexpected Text")
                 .assertTrue(category
                 .getProductNameText().contains(categProduct), "Unexpected Text");
    }

    @Description("Test Clothing Category")
    @Test(priority = 2, groups =  {"valid"}, dataProvider = "clothCategoryData")
    public void TC_CAT_002_ClothingCategory(String categ, String categProduct) {
    category     .clickCategory(categ);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ProductPageLoaded")
                 .assertTrue(category
                 .isProductUrlContains(categ), "Unexpected Text") 
                 .assertTrue(category
                 .getProductNameText().contains(categProduct), "Unexpected Text");
    }

    @Description("Test Books Category")
    @Test(priority = 3, groups =  {"valid"}, dataProvider = "bookCategoryData")
    public void TC_CAT_003_BooksCategory(String categ, String categProduct) {
    category     .clickCategory(categ);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ProductPageLoaded")
                 .assertTrue(category
                 .isProductUrlContains(categ), "Unexpected Text") 
                 .assertTrue(category
                 .getProductNameText().contains(categProduct), "Unexpected Text");
    }

    @Description("Test Home & Garden Category")
    @Test(priority = 4, groups =  {"valid"}, dataProvider = "homeGardenCategoryData")
    public void TC_CAT_004_HomeGardenCategory(String categ, String containHome) {
    category     .clickCategory(categ);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ProductPageLoaded")
                 .assertTrue(category
                 .isProductUrlContains(containHome), "Unexpected Text");
            //  known bug by developer : it has electornics product not validation for now
    }

    @Description("Test Accessories Category")
    @Test(priority = 5, groups =  {"valid"}, dataProvider = "accessoriesCategoryData")
    public void TC_CAT_005_AccessoriesCategory(String categ, String accessoriesNoProducts) {
    
    category     .clickCategory(categ);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ProductPageLoaded")
                 .assertTrue(category
                 .isProductUrlContains(categ), "Unexpected Text")
                 .assertTrue(category
                 .getNoProductText().contains(accessoriesNoProducts), "Unexpected Text");
    }
    



    
}
