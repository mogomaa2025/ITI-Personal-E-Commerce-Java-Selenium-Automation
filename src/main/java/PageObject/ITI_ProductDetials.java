package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import readers.JsonReader;
import utilActions.ElementActions;

public class ITI_ProductDetials {
      // attributes
    private static WebDriver driver;
    private static ElementActions actions;
    private JsonReader jsonReader;
  
     // locators
    private static final By PRODUCT_NAME_HEAD = By.id("breadcrumb-current");
    private static final By PRODUCT_NAME_DETAIL = By.id("product-detail-name");
    private static final By IN_STOCK = By.cssSelector(".badge.badge-success");
    private static final By PRODUCT_STOCK_BADGE = By.id("product-stock-badge");
    private static final By PRODUCT_DETAIL_CATEGORY = By.id("product-detail-category");
    private static final By PRODUCT_STARS = By.id("product-stars"); // ex. ★★★★☆
    private static final By PRODUCT_RATING_TEXT = By.id("product-rating-text"); //ex. (24 reviews)
    private static final By PRODUCT_DETAIL_PRICE = By.id("product-detail-price");
    private static final By PRODUCT_DETAIL_DESCRIPTION = By.id("product-detail-description");
    private static final By QUANTITY_INPUT = By.id("quantity-input");
    private static final String BUTTON_CONTAINS_XPATH = "//button[contains(normalize-space(),'%s')]";
    private static final By btnAddToCartDetail = By.id("btn-add-to-cart-detail");
    private static final By btnBuyNow = By.id("btn-buy-now");
    private static final By btnLikeProduct = By.id("btn-like-product");
    private static final By btnAddToWishlist = By.id("btn-add-to-wishlist");
    private static final By btnBackToProducts = By.id("btn-back-to-products");
    private static final By btnWriteReview = By.id("btn-write-review");       
    private static final By tabHeaderReviews = By.id("tab-header-reviews");
    private static final By tabHeaderSpecifications = By.id("tab-header-specifications");
    private static final By avgRatingValue = By.id("avg-rating-value");
    private static final By avgRatingStars = By.id("avg-rating-stars");
    private static final By totalReviewsCount = By.id("total-reviews-count");
    private static final By reviewHeader = By.cssSelector(".review-header");
    private static final By reviewComment = By.cssSelector(".review-comment");
    private static final By reviewDate = By.cssSelector(".review-date");
    private static final By productDetialsImage = By.id("product-detail-image");
    private static final By footer = By.cssSelector(".footer");


      // Constructor
    public ITI_ProductDetials(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

        //ClickButtonByText +,-
public ITI_ProductDetials ClickButtonByText(String buttonText) {
    String xpath = String.format(BUTTON_CONTAINS_XPATH, buttonText);
    By buttonLocator = By.xpath(xpath);
    actions.click(buttonLocator);
    return this;
}

//get text of QUANTITY_INPUT ?
public String getQuantityInputText() {
    return actions.getText(QUANTITY_INPUT);
}


//get product name text
public String getProductNameText() {
       try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
    actions.gomaaSmartWait3(footer);
    return actions.getText(PRODUCT_NAME_DETAIL);
}

//get product price text
public String getProductPriceText() {
        actions.gomaaSmartWait3(footer);
    return actions.getText(PRODUCT_DETAIL_PRICE);
}

}
