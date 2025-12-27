package Tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import BaseTest.BaseTestClass;

@Epic("ITI E-Commerce Website")
@Feature("UI Orders")
@Story("User Can View Orders Data Correctly")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToProductPage")
public class OrdersTests extends BaseTestClass {

    @Description("make order as user")
    @Test(priority = 1, groups =  {"needUser", "valid"}, dataProvider = "ordersData")
    public void TC_MO_001_MakeOrderAsUser(String productName, String addToCartSuccess, String orderSuccess, String cartTitle) {
    products        .clickProductByName(productName);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(addToCartSuccess), "Unexpected Text");
    Headers         .clickCartButton();
    Assertions      .assertContains(cart
                    .getFirstProductName(), productName, "Unexpected Product Name");
    cart            .clickCheckout()
                    .acceptAlertWithAddress("123 valid address");
    Assertions      .assertContains(cart
                    .getInlineAlertText(), orderSuccess, "Unexpected Text")
                    .assertTrue(Headers
                    .isPageLoadedByTitle(cartTitle), "Unexpected Text");
    }
    
    @Description("make order with empty address")
    @Test(priority = 2, groups =  {"needUser", "clearCart", "invalid"}, dataProvider = "ordersData")
    public void TC_MO_002_MakeOrderAsUser(String productName, String addToCartSuccess, String orderSuccess, String cartTitle) {
    products        .clickProductByName(productName);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(addToCartSuccess), "Unexpected Text");
    Headers         .clickCartButton();
    Assertions      .assertContains(cart
                    .getFirstProductName(), productName, "Unexpected Product Name");
    cart            .clickCheckout()
                    .acceptAlertWithAddress("");
    Assertions      .assertTrue(Headers
                    .isPageLoadedByTitle(cartTitle), "Unexpected Text")
                    .assertTrue(cart
                    .checkCartProductNameExist(productName), "Unexpected Text");
    }
}
