package Tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;

@Epic("ITI E-Commerce Website")
@Feature("UI Cart")
@Story("User Can View Cart Data Correctly")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToProductPage")
public class CartTest extends BaseTestClass {

    @Description("Test add product to cart as user and check if the product is added to the cart")
    @Test(priority = 1, groups =  {"needUser", "valid"}, dataProvider = "addToCartData")
    public void TC_APC_001_AddProductToCartAsUser_With_ChechCart(String productName, String productPrice, String addToCartSuccess) {
    base            .pre_condition_clear_cart();
    products        .clickProductByName(productName);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(addToCartSuccess), "Unexpected Text")
                    .assertEqual(Headers
                    .getCartBadgeCount(), 1, "Unexpected Badge Count");
    Headers         .clickCartButton();
    Assertions      .assertContains(cart
                    .getFirstProductName(), productName, "Unexpected Product Name")
                    .assertContains(cart
                    .getFirstProductPrice(), productPrice, "Unexpected Product Price");
    }

    //cart calculations increasemen and check price
    @Description("Test increase product quantity and check price")
    @Test(priority = 2, groups =  {"needUser", "valid"}, dataProvider = "cartQuantityData", dependsOnMethods = "TC_APC_001_AddProductToCartAsUser_With_ChechCart")
    public void TC_APC_002_IncreaseProductQuantityAndCheckPrice(String productName, String Price, String priceDoubled, String priceTriple, String addToCartSuccess) {
    base            .pre_condition_clear_cart();
    products        .clickProductByName(productName);
    Assertions      .assertTrue(products
                    .getInlineAlertText()
                    .contains(addToCartSuccess), "Unexpected Text")
                    .assertEqual(Headers
                    .getCartBadgeCount(), 1, "Unexpected Badge Count");
    Headers         .clickCartButton();
    Assertions      .assertEqual(cart
                    .getQuantityByProductName(productName), 1, "Unexpected Quantity Input Text");
    cart            .ClickButtonByText("+") // [1] intial price saved here
                    .waitUntileQuantityInputTextToBe(2)
                    .waitUntileTextOfQuantityPriceChanged(); // [2] price changed here
    Assertions      .assertEqual(cart
                    .getQuantityByProductName(productName), 2, "Unexpected Quantity Input Text")
                    .assertEqual(cart
                    .getQuantityPriceAsDouble(productName), Double.parseDouble(priceDoubled), "Unexpected Quantity Price");
    }

    //cart calculations decrease and check price
    @Description("Test decrease product quantity and check price")
    @Test(priority = 3, groups =  {"needUser", "valid"}, dataProvider = "cartQuantityData", dependsOnMethods = "TC_APC_002_IncreaseProductQuantityAndCheckPrice")
    public void TC_APC_003_DecreaseProductQuantityAndCheckPrice(String productName, String Price, String priceDoubled, String priceTriple, String addToCartSuccess) {
    base            .pre_condition_clear_cart();
    products        .clickProductByNameTriple(productName);
    Assertions      .assertTrue(products     
                    .getInlineAlertText()
                    .contains(addToCartSuccess), "Unexpected Text")
                    .assertEqual(Headers
                    .getCartBadgeCount(), 1, "Unexpected Badge Count");
    Headers         .clickCartButton();
    Assertions      .assertEqual(cart
                    .getQuantityByProductName(productName), 3, "Unexpected Quantity Input Text")
                    .assertEqual(cart
                    .getQuantityPriceAsDouble(productName), Double.parseDouble(priceTriple), "Unexpected Quantity Price");
    cart            .ClickButtonByText("-")
                    .waitUntileQuantityInputTextToBe(2)
                    .ClickButtonByText("-") // [1] save intial price first
                    .waitUntileQuantityInputTextToBe(1)
                    .waitUntileTextOfQuantityPriceChanged(); // fix: false postive price bug **By Gomaa** [2]
    Assertions      .assertEqual(cart
                    .getQuantityByProductName(productName), 1, "Unexpected Quantity Input Text")
                    .assertEqual(cart
                    .getQuantityPrice(productName), Price, "Unexpected Quantity Price");
    }

    @Description("Test cart totals for 3 different products")
    @Test(priority = 4, groups =  {"needUser", "valid"}, dataProvider = "totalPriceData", dependsOnMethods = "TC_APC_003_DecreaseProductQuantityAndCheckPrice")
    public void TC_APC_004_CartTotalsFor3DifferentProducts(String expectedTotalPrice) {
    base            .pre_condition_clear_cart();
    products        .add3DifferentProducts();
    Assertions      .assertEqual(Headers
                    .getCartBadgeCount(), 3, "Unexpected Badge Count");
    Headers         .clickCartButton();
    Assertions      .assertEqual(cart
                    .getCartTotal(), expectedTotalPrice, "Unexpected Total Price")
                    .assertEqual(cart
                    .getCartTotalAsDouble(), cart
                    .sumFirst3Products(), "Unexpected Total Price");
    }

    @Description("Test price after clear cart")
    @Test(priority = 5, groups =  {"needUser","valid"}, dataProvider = "clearCartData", dependsOnMethods = "TC_APC_004_CartTotalsFor3DifferentProducts")
    public void TC_APC_005_PriceAfterClearCart(String clearCartSuccess) {
    products        .add3DifferentProducts();
    Assertions      .assertEqual(Headers
                    .getCartBadgeCount(), 3, "Unexpected Badge Count");
    Headers         .clickCartButton();
    cart            .clearCart()
                    .acceptAlert();
    Assertions      .assertTrue(cart
                    .getInlineAlertText()
                    .contains(clearCartSuccess), "Unexpected Text")
                    .assertEqual(cart
                    .getCartTotalAsDouble(), 0.00, "Unexpected Total Price")
                    .assertTrue(cart.isCartEmpty(), "Cart is not empty");
    }
}
