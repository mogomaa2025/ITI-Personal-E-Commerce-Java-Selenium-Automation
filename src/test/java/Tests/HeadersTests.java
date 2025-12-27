package Tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;

@Epic("ITI E-Commerce Website")
@Feature("UI Headers")
@Story("Headers Navigation Bar")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToHomePage")
public class HeadersTests extends BaseTestClass {
 
    @Description("Test visibility of guest user navBar Buttons")
    @Test(priority = 1, groups = {"needGuest", "valid"})
    public void TC_HD_001_Visibility_GuestUser_NavBar_Buttons() {
    Assertions   .assertTrue(Headers
                 .guestUserNavButtonsVisible(), "Guest User Nav Bar Buttons are not visible");
    }

    @Description("Test Home Button guest user")
    @Test(priority = 2, groups = {"needGuest", "valid"}, dataProvider = "homeHeaderData")
    public void TC_HD_002_HomeButton_Nav_Bar_GuestUser(String home) {
     
    Headers      .clickHomeButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(home), "Unexpected Text");
    }
    @Description("Test Products Button guest user")
    @Test(priority =  3, groups = {"needGuest", "valid"}, dataProvider = "productsHeaderData")
    public void TC_HD_003_ProductsButton_Nav_Bar_GuestUser(String products) {
     
    Headers      .clickProductsButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(products), "Unexpected Text");
    }

    @Description("Test Cart Button guest user")
    @Test(priority = 4, groups = {"needGuest", "valid"}, dataProvider = "loginHeaderData")
    public void TC_HD_004_CartButton_Nav_Bar_GuestUser(String login) {
     
    Headers      .clickCartButton();
    login2       .waitLoginButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(login), "Unexpected Text");

    }
    @Description("Test Orders Button guest user")
    @Test(priority = 5, groups = {"needGuest", "valid"}, dataProvider = "loginHeaderData")
    public void TC_HD_005_OrdersButton_Nav_Bar_GuestUser(String login) {
     
    Headers      .clickOrdersButton();
    login2       .waitLoginButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(login), "Unexpected Text");
    }
    @Description("Test Help Button guest user")
    @Test(priority = 6, groups = {"needGuest", "valid"}, dataProvider = "helpHeaderData")
    public void TC_HD_006_HelpButton_Nav_Bar_GuestUser(String help) {
     
    Headers      .clickHelpButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(help), "Unexpected Text");
    }
    @Description("Test Contact Button guest user")
    @Test(priority = 7, groups = {"needGuest", "valid"}, dataProvider = "contactHeaderData")
    public void TC_HD_007_ContactButton_Nav_Bar_GuestUser(String contact) {
     
    Headers      .clickContactButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(contact), "Unexpected Text");
    }
    @Description("Test Advanced Search Button guest user")
    @Test(priority = 8, groups = {"needGuest", "valid"}, dataProvider = "advancedSearchHeaderData")
    public void TC_HD_008_AdvancedSearchButton_Nav_Bar_GuestUser(String advancedSearch) {
     
    Headers      .clickAdvancedSearchButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(advancedSearch), "Unexpected Text");
    }
    @Description("Test Login Button guest user")
    @Test(priority = 9, groups = {"needGuest", "valid"}, dataProvider = "loginHeaderData")
    public void TC_HD_009_LoginButton_Nav_Bar_GuestUser(String login) {
     
    Headers      .clickLoginButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(login), "Unexpected Text");
    }
    @Description("Test Register Button guest user")
    @Test(priority = 10, groups = {"needGuest", "valid"}, dataProvider = "registerHeaderData")
    public void TC_HD_010_RegisterButton_Nav_Bar_GuestUser(String register) {
     
    Headers      .clickRegisterButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(register), "Unexpected Text");
    }

   
    @Description("Test visibility of user navBar Buttons")
    @Test(priority = 11, groups = {"needUser","valid"})
    public void TC_HD_011_visibility_of_user_navBar_Buttons() {

    Headers      .userNavButtonsVisible();
    Assertions   .assertTrue(Headers
                 .userNavButtonsVisible(), "User Nav Bar Buttons are not visible");
    }

    @Description("Test profile as user")
    @Test(priority = 12, groups = {"needUser", "valid"}, dataProvider = "profileHeaderData")
    public void TC_HD_012_profile_as_user(String profile) {
    Headers      .clickProfileButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(profile), "Unexpected Text")
                 .assertTrue(Profile
                 .getAccountType().equals("User"), "Unexpected Text");
    }

    // wishlist
    @Description("Test wishlist as user")
    @Test(priority = 13, groups = {"needUser", "valid"}, dataProvider = "wishlistHeaderData")
    public void TC_HD_013_wishlist_as_user(String wishlist) {
    Headers      .clickWishlistButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(wishlist), "Unexpected Text");
    }

    //notifications
    @Description("Test notifications as user")
    @Test(priority = 14, groups = {"needUser", "valid"}, dataProvider = "notificationsHeaderData")
    public void TC_HD_014_notifications_as_user(String notifications) {
    Headers      .clickNotificationsButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(notifications), "Unexpected Text");
    }

    //logout
    @Description("Test logout as user")
    @Test(priority = 15, groups = {"needUser", "valid"}, dataProvider = "homeHeaderData")
    public void TC_HD_015_logout_as_user(String home) {
    Headers      .clickLogoutButton()
                 .waitLogoutInvisible();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(home), "Unexpected Text");
    }
    
    //admin button
    @Description("Test admin button")
    @Test(priority = 16, groups = {"needAdmin", "valid"}, dataProvider = "adminHeaderData")
    public void TC_HD_016_admin_button(String admin) {
    Headers      .clickAdminButton();
    Assertions   .assertTrue(Headers
                 .isPageLoadedByTitle(admin), "Unexpected Text");
    Headers      .clickProfileButton();
    Assertions   .assertTrue(Profile
                 .getAccountType().equals("Admin"), "Unexpected Text");
    }





    
}
