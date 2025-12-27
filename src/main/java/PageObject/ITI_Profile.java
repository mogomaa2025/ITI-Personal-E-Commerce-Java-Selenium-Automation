package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilActions.ElementActions;

public class ITI_Profile {

    // attributes
    private WebDriver driver;
    private ElementActions actions;

    // locators
    public static final By accountTypeLocator = By.id("profile-display-role");

    // Constructor
    public ITI_Profile(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);

    }

    // Methods
    public String getAccountType() {
        return actions.getText(accountTypeLocator);
    }

}
