package utilActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import readers.Log;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class ElementActions {

    private WebDriver driver;
    private WaitHandler wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHandler(driver);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public ElementActions click(By locator) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                element.click();
                Log.info("Element clicked: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public ElementActions type(By locator, String text) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                element.clear();
                element.sendKeys(text);
                Log.info("Text entered in element: " + locator + " - Text: " + text);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public String getText(By locator) {

        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                String msg = element.getText();
                Log.info("Text retrieved from element: " + locator + " - Text: " + msg);
                return !msg.isEmpty() ? msg : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    public String getCertainAttribute(By locator, String attribute) {

        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                String value = element.getDomAttribute(attribute);
                Log.info("Attribute value retrieved from element: " + locator + " - Text: " + value);
                return !value.isEmpty() ? value : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    public ElementActions selectFromDropdown(By locator, String value) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                Select select = new Select(element);
                select.selectByVisibleText(value);
                Log.info("Selected value '" + value + "' from dropdown: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public String getSelectedOptionText(By locator) {
        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                Select select = new Select(element);
                String selectedText = select.getFirstSelectedOption().getText();
                Log.info("Selected option text retrieved from dropdown: " + locator + " - Text: " + selectedText);
                return !selectedText.isEmpty() ? selectedText : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    public void scrollToElementJS(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("""
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""",
                        driver.findElement(locator));
    }

    public ElementActions hover(By locator) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                new Actions(d).moveToElement(element).perform();
                Log.info("Hovered over element: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public List<WebElement> getWebElements(By locator) {
        return wait.fluentWait().until(driver -> {
            try {
                return driver.findElements(locator);
            } catch (Exception e) {
                return null;
            }
        });
    }

    public ElementActions removeRequiredAttribute(By locator) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].removeAttribute('required');", element);
                Log.info("Removed 'required' attribute from element: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public ElementActions toolTipToAlertContainer(By locator) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("var element = arguments[0];" +
                                "var validationMessage = element.validationMessage;" +
                                "element.removeAttribute('required');" +
                                "var alertContainer = document.getElementById('alert-container');" +
                                "if(alertContainer) { alertContainer.innerHTML = validationMessage; }",
                                element);
                Log.info("Tooltip message added to alert container: " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        return this;
    }

    public String getAlertText() {
        return wait.fluentWait().until(d -> {
            try {
                org.openqa.selenium.Alert alert = d.switchTo().alert();
                String alertText = alert.getText();
                Log.info("Alert text retrieved: " + alertText);
                alert.accept(); // Close the alert
                return alertText;
            } catch (Exception e) {
                return null;
            }
        });
    }

    //visiable
    public boolean isElementVisiable(By locator) {
        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                return element.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
    }

    //invisiable
    public boolean isElementInvisiable(By locator) {
        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJS(locator);
                return !element.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
    }
        public boolean isElementInvisiableNoScroll(By locator) {
        return wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                return !element.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void waitInvisibleOfElement(By locator) {
        wait.fluentWait().until(d -> {
            try {
                return !d.findElement(locator).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
    }

    
    public void gomaaSmartWait(By locator) {
        wait.fluentWait().until(d -> {
            try {
                scrollToElementJS(locator);
                return d.findElement(locator).isDisplayed() && d.findElement(locator).isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void gomaaSmartWait2(By locator) {
        wait.fluentWait().until(d -> {
            try {
                waitElementExpectedToBeClickable(locator);
                scrollToElementJS(locator);
                return d.findElement(locator).isDisplayed() && d.findElement(locator).isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
    }

    /**
     * Smart wait that combines multiple JavaScript-based conditions on a single element:
     * 1. Waits for animation-name to be 'none'
     * 2. Waits for transition-duration to be '0s'
     * This ensures the element has completed both its animation and transition before proceeding
     * 
     * @param locator By locator for the unstable element to wait for
     */
    public void gomaaSmartWait3(By locator) {
        wait.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                
                // Check if animation is complete
                Boolean animationComplete = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript(
                                "var el = arguments[0]; " +
                                "return el && getComputedStyle(el).getPropertyValue('animation-name') === 'none';",
                                element
                        );
                
                // Check if transition is complete
                Boolean transitionComplete = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript(
                                "var el = arguments[0]; " +
                                "return el && getComputedStyle(el).getPropertyValue('transition-duration') === '0s';",
                                element
                        );
                
                boolean bothComplete = (animationComplete != null && animationComplete) && 
                                      (transitionComplete != null && transitionComplete);
                
                if (bothComplete) {
                    Log.info("gomaaSmartWait3: Both animation and transition conditions met for element: " + locator);
                }
                
                return bothComplete;
            } catch (Exception e) {
                return false;
            }
        });
    }


    
    // wait locator text to be argument
    public void waitUntileTextOfLocatorToBe(By locator, String expectedText) {
        wait.fluentWait().until(d -> {
            try {
                return !d.findElement(locator).getText().equals(expectedText);
            } catch (Exception e) {
                return false;
            }
        });
    }
    


    public void waitElementExpectedToBeClickable(By locator) {
        wait.fluentWait().until(d -> {
            try {
                scrollToElementJS(locator);
                // Apply ExpectedCondition within the lambda
                WebElement element = ExpectedConditions
                        .elementToBeClickable(locator)
                        .apply(d); 
                return element != null;
            } catch (Exception e) {
                return false;
            }
        });
    }
    // Wait for element to become stale (removed from DOM)
    public void waitElementExpectedToBeStalenessOf(By locator) {
        wait.fluentWait().until(d -> {
            try {
                // First, find the element reference
                WebElement element = d.findElement(locator);
                // Then wait for it to become stale (returns Boolean)
                Boolean isStale = ExpectedConditions.stalenessOf(element)
                        .apply(d);
                return isStale != null && isStale;
            } catch (Exception e) {
                return false;
            }
        });
    }

    //numberOfElementsToBeMoreThan
    public void waitNumberOfElementsToBeMoreThan(By locator, int count) {
        wait.fluentWait().until(d -> {
            try {
                List<WebElement> elements = d.findElements(locator);
                return elements.size() > count;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //numberOfElementsToBeLessThan
    public void waitNumberOfElementsToBeLessThan(By locator, int count) {
        wait.fluentWait().until(d -> {
            try {
                List<WebElement> elements = d.findElements(locator);
                return elements.size() < count;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //numberOfElementsToBe
    public void waitNumberOfElementsToBe(By locator, int count) {
        wait.fluentWait().until(d -> {
            try {
                List<WebElement> elements = d.findElements(locator);
                return elements.size() == count;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //visibilityOfElementLocated
    public void waitVisibilityOfElementLocated(By locator) {
        wait.fluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //accept alert with typing address
    public void acceptAlertWithAddress(String address) {
        wait.fluentWait().until(d -> {
            try {
                org.openqa.selenium.Alert alert = d.switchTo().alert();
                alert.sendKeys(address);
                alert.accept();
                Log.info("Alert accepted with address: " + address);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //accept alert without message
    public void acceptAlert() {
        wait.fluentWait().until(d -> {
            try {
                org.openqa.selenium.Alert alert = d.switchTo().alert();
                alert.accept();
                Log.info("Alert accepted");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //reject alert
    public void rejectAlert() {
        wait.fluentWait().until(d -> {
            try {
                org.openqa.selenium.Alert alert = d.switchTo().alert();
                alert.dismiss();
                Log.info("Alert rejected");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }


}