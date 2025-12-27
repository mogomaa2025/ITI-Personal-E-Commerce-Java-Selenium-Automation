package Tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;
import PageObject.*;

@Epic("ITI E-Commerce Website")
@Feature("UI Authentication Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToLoginPage")
public class LoginTest extends BaseTestClass {
    
    @Description("login using valid credentials")
    @Test(priority = 1, groups = "valid", dataProvider = "validUserLoginData")
    public void validLogin_TC(String username, String password) {
    login        .performLogin(username, password);
    Assertions   .assertTrue(products
                 .isProductPageLoadedByElement(), "ShopPageLoaded");
    }
    @Description("invalid mail and password")
    @Test(priority = 2, groups = "invalid", dataProvider = "invalidUserLoginData")
    public void invalidUserLogin_TC(String username, String password, String ExpectedError) {
    login       .invalidLogin(username, password);
    Assertions  .assertEqual(login
                .getAlertMessage(), ExpectedError,"Unexpected Text");
    }
    @Description("invalid password login")
    @Test(priority = 3, groups = "invalid", dataProvider = "invalidpassLoginData")
    public void invalidPasswordLogin_TC(String username, String password, String ExpectedError) {
    login       .invalidLogin(username, password);
    Assertions  .assertEqual(login
                .getAlertMessage(), ExpectedError,"Unexpected Text");
    }
    @Description("empty password login")
    @Test(priority = 4, groups = "invalid", dataProvider = "emptyPassLoginData")
    public void emptyPasswordLogin_TC(String username, String password, String ExpectedError) {
    login       .invalidLoginBypassHTML5(username, password);
    Assertions  .assertEqual(login
                .getAlertMessage(), ExpectedError,"Unexpected Text");
    }
    @Description("empty email login")
    @Test(priority = 5, groups = "invalid", dataProvider = "emptyMailLoginData")
    public void emptyMailLogin_TC(String username, String password, String ExpectedError) {
    login       .ToolTipLoginBypassHTML5(ITI_Login.email, username, password);
    Assertions  .assertTrue(login
                .getAlertContainer()
                .contains(ExpectedError),"Unexpected Text");
    }
    @Description("no @ email login")
    @Test(priority = 6, groups = "invalid", dataProvider = "noatinemailLoginData")
    public void noAtMailLogin_TC(String username, String password, String ExpectedError) {
    login       .ToolTipLoginBypassHTML5(ITI_Login.email, username, password);
    Assertions  .assertTrue(login
                .getAlertContainer()
                .contains(ExpectedError),"Unexpected Text");
    }
}