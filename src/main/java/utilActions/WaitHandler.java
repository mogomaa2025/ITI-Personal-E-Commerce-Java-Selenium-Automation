package utilActions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;

public class WaitHandler {
    private WebDriver driver;

    public WaitHandler(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> fluentWait(){
        // Read timeout from properties with fallback default
        int defaultWait = 15; // fallback default
        int pollingInterval = 100; // fallback default in milliseconds
        
        try {
            String waitProperty = readers.PropertyReader.getProperty("DEFAULT_WAIT");
            if (waitProperty != null) {
                defaultWait = Integer.parseInt(waitProperty);
            }
            
            String pollingProperty = readers.PropertyReader.getProperty("POLLING_INTERVAL");
            if (pollingProperty != null) {
                pollingInterval = Integer.parseInt(pollingProperty);
            }
        } catch (Exception e) {
            // Use fallback defaults if properties not found or invalid
        }
        
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(defaultWait))
                .pollingEvery(Duration.ofMillis(pollingInterval))
                //.ignoring(Exception.class);
                .ignoreAll(getExceptions());
    }

    private ArrayList<Class<? extends Exception>>getExceptions(){
        //ArrayList<Exception> exceptions=new ArrayList<>();
        ArrayList<Class<? extends Exception>>exceptions=new ArrayList<>();
        exceptions.add(NoSuchElementException.class);
        //exceptions.add(ElementNotVisibleException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementClickInterceptedException.class);
        exceptions.add(ElementNotInteractableException.class);
        return exceptions;
    }
}
