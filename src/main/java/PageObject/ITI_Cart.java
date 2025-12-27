package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import readers.JsonReader;
import utilActions.ElementActions;

public class ITI_Cart {
    // attributes
    private static WebDriver driver;
    private static ElementActions actions;
    private JsonReader jsonReader;


    // locators
    private static final By alertMessage = By.className("alert-message");
    private static final String cartItemsProductsName = "//*[@id='cart-items']//*[contains(text(), '%s')]";
    private static final By cartItemsProductsNameXpath = By.xpath("//*[@id='cart-items']//*[contains(text(), '%s')]");
    private static final String cartItemsProductsNameXpathString = "//*[@id='cart-items']//*[contains(text(), '%s')]";
    private static final String CART_ITEM_PRICE_XPATH = "//h2[contains(text(), '%s')]/ancestor::div[@id]//p[contains(@id, 'cart-item-price')]";
    private static final String CART_ITEM_QUANTITY_PRICE_XPATH = "//*[contains(text(), '%s')]/ancestor::div[@id]//p[contains(@id, 'cart-item-total')]";
    private static final String BUTTON_CONTAINS_XPATH = "//button[contains(normalize-space(),'%s')]";
    private static final By FirstQUANTITY_INPUT = By.cssSelector(".qty-input:nth-of-type(1)");
    private static final By QUANTITY_INPUT = By.cssSelector(".qty-input");
    private static final By cartTotal = By.id("cart-total");
    private static final By cartContainer = By.id("cart-container");
    private static final By btnCheckout = By.id("btn-checkout");
    private static final By btnClearCart = By.id("btn-clear-cart");
    private static final By firstProductName = By.xpath("//h4");
    private static final By footer = By.cssSelector(".footer");
    private static final By firstProductPrice = By.cssSelector(".item-total");
    private static final By emptyCart = By.id("empty-cart");
    
    // Constructor
    public ITI_Cart(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

    //checkProductNameExist visibility
    public boolean checkCartProductNameExist(String productNameArg) {
        String xpath = String.format(cartItemsProductsNameXpathString, productNameArg);
        By ProductLocator = By.xpath(xpath);
        return actions.isElementVisiable(ProductLocator);
    }

    //ClickButtonByText +,-,×
public ITI_Cart ClickButtonByText(String buttonText) {
  IntialTextOfQuantityPrice();
    String xpath = String.format(BUTTON_CONTAINS_XPATH, buttonText);
    By buttonLocator = By.xpath(xpath);
    actions.click(buttonLocator);
    return this;
}

//wait invisible of button checkout
    public void waitInvisibleCheckoutButton()
    {
        actions.waitInvisibleOfElement(btnCheckout);
    }




// price changing waiting **By Gomaa** important
public String IntialTextOfQuantityPrice(){
    return actions.getText(firstProductPrice);
}
public String FinalTextOfQuantityPrice(){
    return actions.getText(firstProductPrice);
}
public boolean isTextOfQuantityPriceChanged(){
    String intialText = IntialTextOfQuantityPrice();
    String finalText = FinalTextOfQuantityPrice();
    return !intialText.equals(finalText);
}
public ITI_Cart waitUntileTextOfQuantityPriceChanged(){
    while (!isTextOfQuantityPriceChanged()) {
        try {Thread.sleep(50);  } catch (InterruptedException e) {e.printStackTrace();} // tiny waiting 50ms
    }
    return this;
}

// total price changing waiting **By Gomaa** important
public String IntialTextOfTotalPrice(){
    return actions.getText(cartTotal);
}
public String FinalTextOfTotalPrice(){
    return actions.getText(cartTotal);
}
public boolean isTextOfTotalPriceChanged(){
    String intialText = IntialTextOfTotalPrice();
    String finalText = FinalTextOfTotalPrice();
    return !intialText.equals(finalText);
}
public ITI_Cart waitUntileTextOfTotalPriceChanged(){
    while (!isTextOfTotalPriceChanged()) {
        try {Thread.sleep(50);  } catch (InterruptedException e) {e.printStackTrace();} // tiny waiting 50ms
    }
    return this;
}




//get text of QUANTITY_INPUT
public int getQuantityInputText() {
    return Integer.parseInt(actions.getText(QUANTITY_INPUT));
}



public int getQuantityByProductName(String productName) {
    // try{Thread.sleep(2000);}  catch(InterruptedException e){}
    actions.gomaaSmartWait2(QUANTITY_INPUT);
  
    By locator = By.xpath(
        "//h4[contains(text(), '" + productName + "')]" +
        "/ancestor::div[@id]//input[@type='number']"
    );
    WebElement input = driver.findElement(locator);
    String value = input.getDomAttribute("value");
    return Integer.parseInt(value);
}
// wait quantity input text to be
public ITI_Cart waitUntileQuantityInputTextToBe(int quantity) {
   actions.waitUntileTextOfLocatorToBe(QUANTITY_INPUT, String.valueOf(quantity));
   return this;
}



//clear entire Cart
public ITI_Cart clearCart() {
    actions.click(btnClearCart);
    return this;
}
public ITI_Cart savePriceAndClearCart() {
    IntialTextOfTotalPrice();
    actions.click(btnClearCart);
    return this;
}
  

//click checkout
public ITI_Cart clickCheckout() {
    actions.click(btnCheckout);
    return this;
}
//accept alert


//getCartProductName
public String getCartProductName(String productName) {
    String xpath = String.format(cartItemsProductsName, productName);
    return actions.getText(By.xpath(xpath));
}

//get first product name
public String getFirstProductName() {
    return actions.getText(firstProductName);
}
public String getFirstProductPrice() {
    return actions.getText(firstProductPrice);
}


public ITI_Cart acceptAlertWithAddress(String address) {
    actions.acceptAlertWithAddress(address);
    return this;
}

//accept alert without message
public ITI_Cart acceptAlert() {
    actions.acceptAlert();
    return this;
}

public ITI_Cart rejectAlert() {
    actions.rejectAlert();
    return this;
}
 
public String getProductPrice(String productName) {
    String xpath = String.format(CART_ITEM_PRICE_XPATH, productName);
    WebElement priceElement = driver.findElement(By.xpath(xpath));
    return priceElement.getText();
}
public double getProductPriceAsDouble(String productName) {
    String xpath = String.format(CART_ITEM_PRICE_XPATH, productName);
    String priceText = driver.findElement(By.xpath(xpath)).getText();
    return Double.parseDouble(priceText.replace("$", ""));
}


public String getQuantityPrice(String productName) {
    actions.gomaaSmartWait2(QUANTITY_INPUT);
    String xpath = String.format(CART_ITEM_QUANTITY_PRICE_XPATH, productName);
    WebElement priceElement = driver.findElement(By.xpath(xpath));
    return priceElement.getText();
}
public double getQuantityPriceAsDouble(String productName) {
    String xpath = String.format(CART_ITEM_QUANTITY_PRICE_XPATH, productName);
    String priceText = driver.findElement(By.xpath(xpath)).getText();
    return Double.parseDouble(priceText.replace("$", ""));
}

public String getCartTotal() {
    actions.gomaaSmartWait(cartTotal);// fix false postive price  #GOMAA
  //  try {Thread.sleep(2000); } catch (InterruptedException e) {e.printStackTrace();}
    return actions.getText(cartTotal);
}
public double getCartTotalAsDouble() {
     actions.gomaaSmartWait(cartTotal);
    String cartTotalText = actions.getText(cartTotal);
    return Double.parseDouble(cartTotalText.replace("$", ""));
}


public boolean compareAllProductsQuantityPriceWithCartTotal() {
    jsonReader = new JsonReader("Products");
    double totalQuantityPrice = 0;
    for (int i = 1; i <= 12; i++) {
        String productName = jsonReader.getJsonData("product" + i + ".Name");
        totalQuantityPrice += getQuantityPriceAsDouble(productName);
    }
    return totalQuantityPrice == getCartTotalAsDouble(); //should be true
}

public boolean compare2ofFirstProductAnd1ofProduct2WithCartTotal() {
    jsonReader = new JsonReader("Products");
    double totalQuantityPrice = 0;
    totalQuantityPrice += getProductPriceAsDouble(jsonReader.getJsonData("product1.Name")) * 2;
    totalQuantityPrice += getProductPriceAsDouble(jsonReader.getJsonData("product2.Name"));
    return totalQuantityPrice == getCartTotalAsDouble(); //should be true
}

public boolean isCartEmpty() {
    return driver.findElement(emptyCart).isDisplayed();
}

    // get inline alert text
    public String getInlineAlertText() {
        return actions.getText(alertMessage);
    }


String pricenth1 = "(//p[contains(@id, 'cart-item-total')])[1]";
String pricenth2 = "(//p[contains(@id, 'cart-item-total')])[2]";
String pricenth3 = "(//p[contains(@id, 'cart-item-total')])[3]";

    public double sumFirst3Products() {
        String price1 = actions.getText(By.xpath(pricenth1)).replace("$", "");
        String price2 = actions.getText(By.xpath(pricenth2)).replace("$", "");
        String price3 = actions.getText(By.xpath(pricenth3)).replace("$", "");
        return Double.parseDouble(price1) + Double.parseDouble(price2) + Double.parseDouble(price3);
    }





    

}
