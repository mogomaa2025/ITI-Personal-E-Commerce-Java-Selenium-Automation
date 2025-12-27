package Tests;

import PageObject.ITI_Register;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import BaseTest.BaseTestClass;

@Epic("ITI E-Commerce Website")
@Feature("UI Products")
@Story("Add to Carts")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToProductPage")
public class AddToCartTests extends BaseTestClass {


    //add product to cart guest
    @Description("Test add product to cart as guest")
    @Test(priority = 1, groups =  {"invalid"}, dataProvider = "addToCartGuestData")
    public void TC_APC_002_AddProductToCartAsGuest(String name, String price, String expectedMessage) {
    products        .clickProductByName(name);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(expectedMessage), "Unexpected Text");
    }

    @Description("Test add product to cart as user")
    @Test(priority = 2, groups =  {"needUser", "valid"}, dataProvider = "addToCartData")
    public void TC_APC_001_AddProductToCart(String name, String price, String expectedMessage) {
    base            .pre_condition_clear_cart();
    products        .clickProductByName(name);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(expectedMessage), "Unexpected Text");
    }

    @Description("Test add product to cart badge increment")
    @Test(priority = 3, groups =  {"needUser", "valid"}, dataProvider = "addToCartData")
    public void TC_APC_003_AddProductToCartBadgeIncrement(String name, String price, String expectedMessage) {
    base            .pre_condition_clear_cart();
    products        .clickProductByName(name);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(expectedMessage), "Unexpected Text")
                    .assertEqual(Headers
                    .getCartBadgeCount(), 1, "Unexpected Badge Count");
    }

    @Description("Test add first page all products and see badge count")
    @Test(priority = 4, groups =  {"needUser", "valid"})
    public void TC_APC_004_AddFirstPageAllProductsAndSeeBadgeCount() {
    base            .pre_condition_clear_cart();
    products        .addAllProducts();
    Headers         .waitBadgeAnimationStable();
    Assertions      .assertEqual(Headers
                    .getCartBadgeCount(), 12, "Unexpected Badge Count");
    }

    @Description("Test add product to cart from productDetials")
    @Test(priority = 5, groups =  {"needUser", "valid"}, dataProvider = "addToCartData")
    public void TC_APC_005_AddProductToCartFromProductDetials(String name, String price, String expectedMessage) {
    products        .clickViewDetailsByName(name);
    Assertions      .assertContains(productsD
                    .getProductNameText(), name, "Unexpected Product Name")
                    .assertContains(productsD
                    .getProductPriceText(), price, "Unexpected Product Price");
    }
    
}
