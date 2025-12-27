package Tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import net.bytebuddy.utility.RandomString;
import org.testng.annotations.*;
import BaseTest.BaseTestClass;

@Listeners({Listners.TestNGListners.class, AllureTestNg.class})
@Epic("ITI E-Commerce Website")
@Feature("UI Authentication Management")
@Story("User Register")
@Severity(SeverityLevel.CRITICAL)
@Owner("Gomaa")
@Test(groups = "needNavigationToRegisterPage")
public class RegisterTest extends BaseTestClass {
        
        @Description("register valid credentials")
        @Test(priority = 1, groups = "valid", dataProvider = "validRegistrationData")
        public void validRegister_TC(String name, String email, String password, String phone, String address) {
        register    .performRegister(name,RandomString.make(6) + email,password,phone, address);
        Assertions  .assertTrue(products
                    .isLoginPageLoaded(), "LoginPageLoaded");
        }

        @Description("register with small password")
        @Test(priority = 2, groups = "invalid", dataProvider = "shortPasswordData")
        public void invalidRegisterSmallPassword_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .ToolTipRegisterBypassHTML5(register.password, name, RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertContainer()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with empty password")
        @Test(priority = 3, groups = "invalid", dataProvider = "emptyPasswordData")
        public void invalidRegisterEmptyPassword_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .ToolTipRegisterBypassHTML5(register.password, name, RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertContainer()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with password without number")
        @Test(priority = 4, groups = "invalid", dataProvider = "passwordWithoutNumberData")
        public void invalidRegisterPasswordWithoutNumber_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with empty name")
        @Test(priority = 5, groups = "invalid", dataProvider = "emptyNameData")
        public void invalidRegisterEmptyName_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .ToolTipRegisterBypassHTML5(register.registerName, name, RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertContainer()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with short name")
        @Test(priority = 6, groups = "invalid", dataProvider = "shortNameData")
        public void invalidRegisterShortName_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with existing email")
        @Test(priority = 7, groups = "invalid", dataProvider = "existingEmailData")
        public void invalidRegisterExistingEmail_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with no @ email")
        @Test(priority = 8, groups = "invalid", dataProvider = "noAtEmailData")
        public void invalidRegisterNoAtEmail_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .ToolTipRegisterBypassHTML5(register.registerEmail, name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertContainer()
                      .contains(expectedMessage),"Unexpected Text");
        }
        
        @Description("register with symbols not @ email")
        @Test(priority = 9, groups = "invalid", dataProvider = "symbolsNotAtEmailData")
        public void invalidRegisterSymbolsNotAtEmail_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .ToolTipRegisterBypassHTML5(register.registerEmail, name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertContainer()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with empty phone number")
        @Test(priority = 10, groups = "invalid", dataProvider = "emptyPhoneData")
        public void invalidRegisterEmptyPhone_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with short phone number")
        @Test(priority = 11, groups = "invalid", dataProvider = "shortPhoneData")
        public void invalidRegisterShortPhone_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with strange symbol number")
        @Test(priority = 12, groups = "invalid", dataProvider = "strangeSymbolPhoneData")
        public void invalidRegisterStrangeSymbolPhone_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }

        @Description("register with empty address")
        @Test(priority = 13, groups = "invalid", dataProvider = "emptyAddressData")
        public void invalidRegisterEmptyAddress_TC(String name, String email, String password, String phone, String address, String expectedMessage) {
        register      .performInvalidRegister(name,RandomString.make(6) + email, password, phone, address);
        Assertions    .assertTrue(register
                      .getAlertMessage()
                      .contains(expectedMessage),"Unexpected Text");
        }
    
}
