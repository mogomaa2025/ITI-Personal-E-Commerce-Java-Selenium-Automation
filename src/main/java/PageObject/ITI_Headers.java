package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilActions.ElementActions;

public class ITI_Headers {

    // attributes
    private static WebDriver driver;
    private ElementActions actions;

    // locators
    private static final By inlineAlertMessage = By.className("alert-message");
    private static final By homeButton = By.id("nav-home");
    private static final By productsButton = By.id("nav-products");
    private static final By cartButton = By.id("nav-cart");
    private static final By cartBadge = By.id("cart-count");
    private static final By ordersButton = By.id("nav-orders");
    private static final By helpButton = By.id("nav-help");
    private static final By contactButton = By.id("nav-contact");
    private static final By advancedSearchButton = By.id("nav-advanced-search");
    private static final By loginButton = By.id("nav-login");
    private static final By registerButton = By.id("nav-register");
    //user
    private static final By logoutButton = By.id("btn-logout");
    private static final By notificationsButton = By.id("nav-notifications");
    private static final By profileButton = By.id("nav-profile");
    private static final By wishlistButton = By.id("nav-wishlist");
    //admin
    private static final By adminButton = By.id("nav-admin");


    // Constructor
    public ITI_Headers(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

    public ITI_Headers clickHomeButton() {
        actions.click(homeButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickProductsButton() {
        actions.click(productsButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickCartButton() {
        actions.click(cartButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickOrdersButton() {
        actions.click(ordersButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickHelpButton() {
        actions.click(helpButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickContactButton() {
        actions.click(contactButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickAdvancedSearchButton() {
        actions.click(advancedSearchButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickLoginButton() {
        actions.click(loginButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickRegisterButton() {
        actions.click(registerButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickLogoutButton() {
        actions.click(logoutButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickNotificationsButton() {
        actions.click(notificationsButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickProfileButton() {
        actions.click(profileButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickWishlistButton() {
        actions.click(wishlistButton);
        return new ITI_Headers(driver);
    }

    public ITI_Headers clickAdminButton() {
        actions.click(adminButton);
        return new ITI_Headers(driver);
    }


    //current url contains category name
    public boolean isPageUrlContains(String endPoint) {
        return driver.getCurrentUrl().contains(endPoint);
    }

    //is home loaded
    public boolean isPageLoadedByTitle(String title) {
        return driver.getTitle().contains(title);
    }

    public ITI_Cart waitUntileQuantityInputTextToBe(int quantity) {
        actions.waitUntileTextOfLocatorToBe(cartBadge, String.valueOf(quantity));
        return new ITI_Cart(driver);
    }

//isElementVisiable

    public boolean guestUserNavButtonsVisible() {
        if (actions.isElementVisiable(homeButton) &&
                actions.isElementVisiable(productsButton) &&
                actions.isElementVisiable(cartButton) &&
                actions.isElementVisiable(ordersButton) &&
                actions.isElementVisiable(helpButton) &&
                actions.isElementVisiable(contactButton) &&
                actions.isElementVisiable(advancedSearchButton) &&
                actions.isElementVisiable(loginButton) &&
                actions.isElementVisiable(registerButton) &&
                actions.isElementInvisiable(logoutButton) &&
                actions.isElementInvisiable(notificationsButton) &&
                actions.isElementInvisiable(profileButton) &&
                actions.isElementInvisiable(wishlistButton)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userNavButtonsVisible() {
        if (actions.isElementVisiable(homeButton) &&
                actions.isElementVisiable(productsButton) &&
                actions.isElementVisiable(cartButton) &&
                actions.isElementVisiable(ordersButton) &&
                actions.isElementVisiable(helpButton) &&
                actions.isElementVisiable(contactButton) &&
                actions.isElementVisiable(advancedSearchButton) &&
                actions.isElementVisiable(logoutButton) &&
                actions.isElementVisiable(notificationsButton) &&
                actions.isElementVisiable(profileButton) &&
                actions.isElementVisiable(wishlistButton) &&
                actions.isElementInvisiable(loginButton) &&
                actions.isElementInvisiable(registerButton) &&
                actions.isElementInvisiable(adminButton)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean adminNavButtonsVisible() {
        if (actions.isElementVisiable(homeButton) &&
                actions.isElementVisiable(productsButton) &&
                actions.isElementVisiable(cartButton) &&
                actions.isElementVisiable(ordersButton) &&
                actions.isElementVisiable(helpButton) &&
                actions.isElementVisiable(contactButton) &&
                actions.isElementVisiable(advancedSearchButton) &&
                actions.isElementVisiable(adminButton) &&
                actions.isElementVisiable(logoutButton) &&
                actions.isElementVisiable(notificationsButton) &&
                actions.isElementVisiable(profileButton) &&
                actions.isElementVisiable(wishlistButton) &&
                actions.isElementInvisiable(loginButton) &&
                actions.isElementInvisiable(registerButton)) {
            return true;
        } else {
            return false;
        }
    }

    public void navgiateToHome() {
        actions.click(homeButton);
    }


    // get cartBadgeCount
    public int getCartBadgeCount() {
        // try{Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        actions.gomaaSmartWait3(cartBadge);
        return Integer.parseInt(actions.getText(cartBadge));
    }

    public void waitBadgeAnimationStable()
    {
        actions.gomaaSmartWait3(cartBadge);

    }


    // wait logoutButton invsible
    public void waitLogoutInvisible()
    {
        actions.waitInvisibleOfElement(logoutButton);

    }

    
    //get text
    public String getInlineAlertMessageText() {
        return actions.getText(inlineAlertMessage);
    }






}
