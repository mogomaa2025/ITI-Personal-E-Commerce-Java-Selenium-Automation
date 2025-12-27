package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilActions.ElementActions;

public class ITI_Register {

    // attributes
    private static WebDriver driver;
    private ElementActions actions;

    // locators
    public static final By registerName = By.id("register-name");
    public static final By registerEmail = By.id("register-email");
    public static final By password = By.id("register-password");
    public static final By phoneNumber = By.id("register-phone");
    public static final By registerAddress = By.id("register-address");
    private static final By registerBtn = By.id("btn-register-submit");
    private static final By alertContainer = By.id("alert-container");
    private static final By alertMessage = By.className("alert-message");

    // Constructor
    public ITI_Register(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

    // Methods
    public ITI_Products performRegister(String name, String email, String pass, String phone, String address) {
        actions.type(registerName, name);
        actions.type(registerEmail, email);
        actions.type(password, pass);
        actions.type(phoneNumber, phone);
        actions.type(registerAddress, address);
        actions.click(registerBtn);
        return new ITI_Products(driver);

    }

        public ITI_Register performInvalidRegister(String name, String email, String pass, String phone, String address) {
        actions.type(registerName, name);
        actions.type(registerEmail, email);
        actions.type(password, pass);
        actions.type(phoneNumber, phone);
        actions.type(registerAddress, address);
        actions.click(registerBtn);
        return this;

    }

    public ITI_Register invalidRegister(String invalidName, String invalidEmail, String invalidPassword,
            String invalidPhone, String invalidAddress) {
        actions.type(registerName, invalidName);
        actions.type(registerEmail, invalidEmail);
        actions.type(password, invalidPassword);
        actions.type(phoneNumber, invalidPhone);
        actions.type(registerAddress, invalidAddress);
        actions.click(registerBtn);
        return this;
    }

    public ITI_Register invalidRegisterBypassHTML5(String invalidName, String invalidEmail, String invalidPassword,
            String invalidPhone, String invalidAddress) {
        // Remove HTML5 validation to test application's custom validation
        actions.removeRequiredAttribute(registerName);
        actions.removeRequiredAttribute(registerEmail);
        actions.removeRequiredAttribute(password);
        actions.removeRequiredAttribute(phoneNumber);
        actions.removeRequiredAttribute(registerAddress);
        actions.type(registerName, invalidName);
        actions.type(registerEmail, invalidEmail);
        actions.type(password, invalidPassword);
        actions.type(phoneNumber, invalidPhone);
        actions.type(registerAddress, invalidAddress);
        actions.click(registerBtn);

        return this;
    }

    public ITI_Register ToolTipRegisterBypassHTML5(By locator, String invalidName, String invalidEmail, String invalidPassword,
            String invalidPhone, String invalidAddress) {
        // Remove HTML5 validation to test application's custom validation
        actions.type(registerName, invalidName);
        actions.type(registerEmail, invalidEmail);
        actions.type(password, invalidPassword);
        actions.type(phoneNumber, invalidPhone);
        actions.type(registerAddress, invalidAddress);
        actions.click(registerBtn);
        actions.toolTipToAlertContainer(locator);
        return this;
    }


    public String getAlertMessage() {
        return actions.getText(alertMessage);
    }

    public String getAlertContainer() {
        return actions.getText(alertContainer);
    }

    public String getAlertText() {
        return actions.getAlertText();
    }

}
