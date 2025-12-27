package BaseTest;

import assertions.Assertions;
import driverFactory.WebDriverFactory;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import DataProviders.TestDataProviders;
import PageObject.*;
import readers.*;

@Listeners({Listners.TestNGListners.class, AllureTestNg.class})
@Test(dataProviderClass = TestDataProviders.class)
public class BaseTestClass {
    protected WebDriver driver;
    // protected JsonReader jsonReader;
    // protected JsonReader loginJsonReader;
    // protected JsonReader registerJsonReader;
    // protected JsonReader inlineAlertJsonReader;
    // protected JsonReader headerJsonReader;
    // protected JsonReader searchJsonReader;
    //
    protected BaseTestClass base;
    protected ITI_Headers Headers;
    protected ITI_Login login;
    protected ITI_Login login2;
    protected ITI_Register register;
    protected ITI_Products products;
    protected ITI_ProductDetials productsD;
    protected ITI_Cart cart;
    protected ITI_Profile Profile;
    protected ITI_Categoreis category;
    protected Assertions Assertions;

//    @BeforeClass
//    public void PreSetup() {
//        // jsonReader = new JsonReader("Products");
//        // loginJsonReader = new JsonReader("Login");
//        // registerJsonReader = new JsonReader("Register");
//        // inlineAlertJsonReader = new JsonReader("InlineAlert");
//        // headerJsonReader = new JsonReader("Headers");
//        // searchJsonReader = new JsonReader("Search");
//        // categoryJsonReader = new JsonReader("Categories");
//    }

    @BeforeMethod(alwaysRun = true, groups = "baseSetup")
    public void Setup() {
        driver = WebDriverFactory.initDriver();
        Assertions = new Assertions();
        Headers = new ITI_Headers(driver);
        login = new ITI_Login(driver);
        login2 = new ITI_Login(driver);
        register = new ITI_Register(driver);
        products = new ITI_Products(driver);
        productsD = new ITI_ProductDetials(driver);
        cart = new ITI_Cart(driver);
        Profile = new ITI_Profile(driver);
        category = new ITI_Categoreis(driver);
        base = this; 
    }

    @BeforeMethod(onlyForGroups = "needUser", groups = "userLogin", dependsOnGroups = "baseSetup" ,alwaysRun = true)
    public void pre_condition_login_as_User() {
        JsonReader loginJsonReader = new JsonReader("Login");
        String username = loginJsonReader.getJsonData("valid.username", "");
        String password = loginJsonReader.getJsonData("valid.password", "");
        Headers         .clickLoginButton();
        login           .performLogin(username, password);
        Headers         .clickProductsButton();
    }

    @BeforeMethod(onlyForGroups = "needAdmin", groups = "adminLogin", dependsOnGroups = "baseSetup", alwaysRun = true)
    public void pre_requist_login_as_admin() {
        JsonReader loginJsonReader = new JsonReader("Login");
        String username = loginJsonReader.getJsonData("admin.username", "");
        String password = loginJsonReader.getJsonData("admin.password", "");
        Headers        .clickLoginButton();
        login          .performLogin(username, password);
        Headers        .navgiateToHome();
    }


 //  @BeforeMethod(onlyForGroups = "clearCart", dependsOnGroups = "userLogin")
    public void pre_condition_clear_cart() {
        Headers         .clickCartButton();
        cart            .clearCart()
                        .acceptAlert();
        Headers         .clickProductsButton();
    }

    @BeforeMethod(onlyForGroups = "needNavigationToProductPage", dependsOnGroups = "baseSetup", alwaysRun = true)
    public void NavigationToCart() {
        driver.get(PropertyReader.getProperty("baseUrlWeb")+PropertyReader.getProperty("productsUrl"));
    }

    @BeforeMethod(onlyForGroups = "needNavigationToLoginPage", dependsOnGroups = "baseSetup", alwaysRun = true)
    public void NavigationToLogin() {
        driver.get(PropertyReader.getProperty("baseUrlWeb")+PropertyReader.getProperty("loginUrl"));
    }

    @BeforeMethod(onlyForGroups = "needNavigationToHomePage", dependsOnGroups = "baseSetup", alwaysRun = true)
    public void NavigationToHome() {
        driver.get(PropertyReader.getProperty("baseUrlWeb")+PropertyReader.getProperty("homeUrl"));
    }

    @BeforeMethod(onlyForGroups = "needNavigationToRegisterPage", dependsOnGroups = "baseSetup", alwaysRun = true)
    public void NavigationToRegister() {
            driver.get(PropertyReader.getProperty("baseUrlWeb")+PropertyReader.getProperty("registerUrl"));
    }
    
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }
}
