package PageObject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import readers.JsonReader;
import utilActions.ElementActions;

public class ITI_Orders {
      // attributes
    private static WebDriver driver;
    private static ElementActions actions;
    private JsonReader jsonReader;
  
     // locators
    private static final By orderFilters = By.className("order-filters");
    private static final By filterAll = By.id("filter-all");
    private static final By filterPending = By.id("filter-pending");
    private static final By filterProcessing = By.id("filter-processing");
    private static final By filterShipped = By.id("filter-shipped");
    private static final By filterDelivered = By.id("filter-delivered");
    private static final By filterActive = By.className("btn btn-outline active");
    private static final By ordersTitle = By.id("orders-title");
    private static final By orderStatus = By.className("order-status");
    private static final By statusPending = By.className("status-pending");
    private static final By statusProcessing = By.className("status-processing");
    private static final By statusShipped = By.className("status-shipped");
    private static final By statusDelivered = By.className("status-delivered");
    private static final By orderDetails = By.className("order-details");
    private static final By orderItems = By.className("order-items");
    private static final By ordersList = By.id("orders-list");

      // Constructor
    public ITI_Orders(WebDriver driver) {
        this.driver = driver;
        actions = new ElementActions(driver);
    }

public boolean isFilterAllButtonActive() {
    try {
        WebElement button = driver.findElement(
            By.xpath("//button[@id='" + filterAll + "' and contains(@class, 'active')]")
        );
        return button.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}
public boolean isFilterPendingButtonActive() {
    try {
        WebElement button = driver.findElement(
            By.xpath("//button[@id='" + filterPending + "' and contains(@class, 'active')]")
        );
        return button.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}
public boolean isFilterProcessingButtonActive() {
    try {
        WebElement button = driver.findElement(
            By.xpath("//button[@id='" + filterProcessing + "' and contains(@class, 'active')]")
        );
        return button.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}
public boolean isFilterShippedButtonActive() {
    try {
        WebElement button = driver.findElement(
            By.xpath("//button[@id='" + filterShipped + "' and contains(@class, 'active')]")
        );
        return button.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}
public boolean isFilterDeliveredButtonActive() {
    try {
        WebElement button = driver.findElement(
            By.xpath("//button[@id='" + filterDelivered + "' and contains(@class, 'active')]")
        );
        return button.isDisplayed();
    } catch (NoSuchElementException e) {
        return false;
    }
}


public boolean isOrderDetailsContainsTexts(String TotalPrice, String Items) {
    try {
        WebElement orderDetailsElement = driver.findElement(orderDetails);
        String detailsText = orderDetailsElement.getText();
        return detailsText.contains(TotalPrice) && detailsText.contains(Items);
    } catch (NoSuchElementException e) {
        return false;
    }
}
public boolean isOrderItemsContainsTexts(String productName, String Qty, String price) {
    try {
        WebElement orderItemsElement = driver.findElement(orderItems);
        String itemsText = orderItemsElement.getText();
        return itemsText.contains(productName) && itemsText.contains(Qty) && itemsText.contains(price);
    } catch (NoSuchElementException e) {
        return false;
    }
}

public boolean isOrderOnlyPending(String pending, String processing, String shipped, String delivered) {
    try {
        WebElement orderListElement = driver.findElement(ordersList);
        String listText = orderListElement.getText();
        return listText.contains(pending) && !listText.contains(processing) && !listText.contains(shipped) && !listText.contains(delivered);
    } catch (NoSuchElementException e) {
        return false;
    }
}

//isOrderOnlyProcessing
public boolean isOrderOnlyProcessing(String pending, String processing, String shipped, String delivered) {
    try {
        WebElement orderListElement = driver.findElement(ordersList);
        String listText = orderListElement.getText();
        return listText.contains(processing) && !listText.contains(pending) && !listText.contains(shipped) && !listText.contains(delivered);
    } catch (NoSuchElementException e) {
        return false;
    }
}
//isOrderOnlyShipped
public boolean isOrderOnlyShipped(String pending, String processing, String shipped, String delivered) {
    try {
        WebElement orderListElement = driver.findElement(ordersList);
        String listText = orderListElement.getText();
        return listText.contains(shipped) && !listText.contains(pending) && !listText.contains(processing) && !listText.contains(delivered);
    } catch (NoSuchElementException e) {
        return false;
    }
}
//isOrderOnlyDelivered
public boolean isOrderOnlyDelivered(String pending, String processing, String shipped, String delivered) {
    try {
        WebElement orderListElement = driver.findElement(ordersList);
        String listText = orderListElement.getText();
        return listText.contains(delivered) && !listText.contains(pending) && !listText.contains(processing) && !listText.contains(shipped);
    } catch (NoSuchElementException e) {
        return false;
    }
}








}
