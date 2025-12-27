package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import readers.JsonReader;
import utilActions.ElementActions;

public class ITI_Products {
    // attributes
    private static WebDriver driver;
    private static ElementActions actions;
    private JsonReader jsonReader;


    // locators
    private static final By shopNow = By.id("btn-shop-now");
    private static final By loginTitle = By.id("login-title");
    private static final By alertMessage = By.className("alert-message");
    private static final By alertClose = By.cssSelector(".alert-close");
    private static final By productCategory = By.cssSelector(".product-category");
    private static String addtoCartByXpath = "//div[@id='products-grid']//div[.//h3[contains(text(), '%s')]]//button[contains(text(), 'Add to Cart')]";
    private static String ViewDetialsByXpath = "//div[@id='products-grid']//div[.//h3[contains(text(), '%s')]]//a[contains(text(), 'View Details')]";
    private static String LikeByXpath = "//div[@id='products-grid']//div[.//h3[contains(text(), '%s')]]//button[contains(text(), 'Like')]";
    private static String LikeCountByXpath = "//h3[contains(text(), '%s')]/ancestor::div[@id]//generic[contains(@id, 'like-count')]";
    private static final By productFilters = By.id("product-filters");
    private static final By searchInput = By.id("search-input");
    private static final By CategoryFilterDropDownlIst  = By.id("category-filter");
    private static final By minPrice = By.id("min-price");
    private static final By maxPrice = By.id("max-price");
    private static final By applyFilters = By.id("btn-apply-filters");
    private static final By clearFilters = By.id("btn-clear-filters");
    private static final By noProducts = By.id("no-products");
    private static final By productsGrid = By.id("products-grid");
    private static final By productCard = By.cssSelector(".product-card");
    private static final String productsPrices = "//div[@id='products-grid']//p[contains(@id, 'product-price')]";
    private static final By Loading = By.cssSelector(".loading"); //Loading products...

    // Constructor
    public ITI_Products(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

    //click product by name
    public ITI_Products clickProductByName(String productName) {
        By productLocator = By.xpath(String.format(addtoCartByXpath, productName));
        actions.click(productLocator);
        return this;
    }

       public ITI_Products clickProductByNameTriple(String productName) {
        By productLocator = By.xpath(String.format(addtoCartByXpath, productName));
        actions.click(productLocator);
          actions.click(alertClose);
        actions.click(productLocator);
        actions.click(alertClose);
         actions.click(productLocator);
        return this;
    }

    //add 3 different products
    public ITI_Products add3DifferentProducts() {
        jsonReader = new JsonReader("Products");
        By productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product1.Name")));
        actions.click(productLocator);
        actions.click(alertClose);
        productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product2.Name")));
        actions.click(productLocator);
        actions.click(alertClose);
        productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product3.Name")));
        actions.click(productLocator);
        actions.click(alertClose);
        return this;
    }


    public ITI_Products addAllProducts() {
       jsonReader = new JsonReader("Products");
       //from product1 to product12
       for (int i = 1; i <= 12; i++) {
           By productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product" + i + ".Name")));
           actions.click(productLocator);
           actions.click(alertClose);
       }
       return this;
    }


        public ITI_Products clickViewDetailsByName(String productName) {
       // waitInvisibleOfLoading();
        By productLocator = By.xpath(String.format(ViewDetialsByXpath, productName));
        actions.click(productLocator);
        return this;
    }




    public ITI_Products add2ofFirstProductAnd1of2ndProduct() {
        jsonReader = new JsonReader("Products");
        By productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product1.Name")));
        actions.click(productLocator);
        actions.click(alertClose);
        actions.click(productLocator);
        actions.click(alertClose);
        productLocator = By.xpath(String.format(addtoCartByXpath, jsonReader.getJsonData("product2.Name")));
        actions.click(productLocator);
        actions.click(alertClose);
        return this;
            }


            //get like count
            public int getLikeCountByName(String productName) {
                By productLocator = By.xpath(String.format(LikeCountByXpath, productName));
                return Integer.parseInt(actions.getText(productLocator));
            }


    private static By categoryLocator(String category) { //
        By categoryLocator = By.xpath(String.format("//div[@class='category-grid']/a[@href='/web/products?category=%s']", category));
        return categoryLocator;
    }

    private static By categoryDescriptionLocator(String category) { //
        By categoryLocator = By.xpath(String.format("//h3[.='%s']", category));
        return categoryLocator;
    }

    // Methods
    public boolean isProductPageLoadedByElement() { //s
        return actions.getText(applyFilters).contains("Apply Filters");
    }

    public boolean isHomePageLoaded() {
        return actions.getText(shopNow).contains("Shop Now");
    }

    public boolean isLoginPageLoaded() {
        return actions.getText(loginTitle).contains("Login");
    }

    public String getCategory(String category) {
        return actions.getText(categoryDescriptionLocator(category));
    }

    // get inline alert text
    public String getInlineAlertText() {
        return actions.getText(alertMessage);
    }

    //wait all inline alerts invsible
    public ITI_Products waitAllInlineAlertsInvisible() {
        actions.isElementInvisiable(alertMessage);
        return this;
    }


    //search
        public ITI_Products performSearch(String search, String Category, String minp, String maxp) {
        actions.type(searchInput, search);
        actions.selectFromDropdown(CategoryFilterDropDownlIst, Category);
        actions.type(minPrice, minp);
        actions.type(maxPrice, maxp);
        actions.click(applyFilters);
        actions.gomaaSmartWait(applyFilters); // this button disabled then enabled my method is check for enable

        // try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return this;
    }

    //clear
    public ITI_Products clickClearButton() {
        actions.gomaaSmartWait(applyFilters); // this button disabled then enabled my method is check for enable
        actions.click(clearFilters);
        return this;
    }
    

    //check not proudct not exist
    public boolean isNoProducts() {
        return actions.isElementVisiable(noProducts);
    }
    //exist
    public boolean isProductExist() {
        return actions.isElementVisiable(productCard);
    }

    //wait invisable of loading...
    public boolean isLoadingVisible()
    {
        return actions.isElementVisiable(Loading);
    }
    public static void waitInvisibleOfLoading()
    {
        actions.waitInvisibleOfElement(Loading);
    }
    public void waitStaleeOfLoading()
    {
        actions.waitElementExpectedToBeStalenessOf(Loading);
    }



    public boolean productGridsContainPricesInRange(double minPrice, double maxPrice) {
    List<WebElement> priceElements = driver.findElements(By.xpath(productsPrices));
    for (WebElement priceElement : priceElements) {
        String priceText = priceElement.getText();
        double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", "")); // get only numbers from priceText
        
        if (price < minPrice || price > maxPrice) {
            return false; // Price outside range
        }
    }
    return true;
    //usage : Assert.assertTrue(productsPage.productGridsContainPricesInRange(1, 1000)); 
}

//get product category name
public String getFirstProductCategoryName() {
    return actions.getText(productCategory);
}








}
