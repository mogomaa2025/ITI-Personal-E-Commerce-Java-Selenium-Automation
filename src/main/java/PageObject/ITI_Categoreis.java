package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilActions.ElementActions;

public class ITI_Categoreis {

        // attributes
    private static WebDriver driver;
    private ElementActions actions;

    // locators
    private static final By alertContainer = By.id("alert-container");
    private static final By successLogin = By.cssSelector(".alert.alert-success");
    private static final By errorLogin = By.cssSelector(".alert.alert-error");
    private static final By alertMessage = By.className("alert-message");
    private static final By alertClose = By.className("alert-close");
    private static final String categoryDynamicLocator = "//a[contains(normalize-space(),'%s')]";
    private static final By productName = By.cssSelector(".product-name");
    private static final By noProducts = By.cssSelector("#no-products");


    // Constructor
    public ITI_Categoreis(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }



        public ITI_Categoreis clickCategory(String categoryName) {
        actions.click(By.xpath(String.format(categoryDynamicLocator,categoryName)));
        return new ITI_Categoreis(driver);
    }

    //current url contains category name
    public boolean isProductUrlContains(String categoryName) {
        return driver.getCurrentUrl().contains(categoryName);
    }

    //get productnametext
    public String getProductNameText() {
        return actions.getText(productName);
    }
        public String getNoProductText() {
        return actions.getText(noProducts);
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



        // private static By getCategoryLocator(String caregoryName) {
    //     return By.xpath(String.format("//a[contains(normalize-space(),'%s')]", caregoryName));
    // }
    // public QA_Categoreis clickCategory(String categoryName) {
    //     actions.click(getCategoryLocator(categoryName));
    //     return new QA_Categoreis(driver);
    // }

}
