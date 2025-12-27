package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilActions.ElementActions;

public class ITI_Login {

    // attributes
    private static WebDriver driver;
    private ElementActions actions;

    // locators
    public static final By email = By.id("login-email");
    public static final By password = By.id("login-password");
    private static final By loginBtn = By.id("btn-login-submit");
    private static final By alertContainer = By.id("alert-container");
    private static final By successLogin = By.cssSelector(".alert.alert-success");
    private static final By errorLogin = By.cssSelector(".alert.alert-error");
    private static final By alertMessage = By.className("alert-message");
    private static final By alertClose = By.className("alert-close");
    private static final By loginButtonFromNavBar = By.id("nav-login");

    // Constructor
    public ITI_Login(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);

    }

    // Methods
    public ITI_Products performLogin(String userName, String pass) {
        actions.type(email, userName);
        actions.type(password, pass);
        actions.click(loginBtn);
        return new ITI_Products(driver);

    }

    public ITI_Login clickLoginBtn() {
        actions.click(loginButtonFromNavBar);
        return this;
    }

    public ITI_Login invalidLogin(String invalidUserName, String invalidPassword) {
        actions.type(email, invalidUserName);
        actions.type(password, invalidPassword);
        actions.click(loginBtn);
        return this;
    }

    public ITI_Login invalidLoginBypassHTML5(String invalidUserName, String invalidPassword) {
        // Remove HTML5 validation to test application's custom validation
        actions.removeRequiredAttribute(email);
        actions.removeRequiredAttribute(password);
        actions.type(email, invalidUserName);
        actions.type(password, invalidPassword);
        actions.click(loginBtn);

        return this;
    }

    public ITI_Login ToolTipLoginBypassHTML5(By locator, String invalidUserName, String invalidPassword) {
        // Remove HTML5 validation to test application's custom validation
        actions.type(email, invalidUserName);
        actions.type(password, invalidPassword);
        actions.click(loginBtn);
        actions.toolTipToAlertContainer(locator);
        return this;
    }

    // wait for login visisble
    public void waitLoginButton()
    {
        actions.waitVisibilityOfElementLocated(loginBtn);
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
