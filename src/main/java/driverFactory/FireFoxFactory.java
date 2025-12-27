package driverFactory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import readers.PropertyReader;

public class FireFoxFactory extends AbstractDriver {

    private FirefoxOptions getOptions(){
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        switch (PropertyReader.getProperty("executionType"))
        {
            case "LocalHeadless", "Remote" ->{
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
        }
        return options;
    }
    @Override
    public WebDriver createDriver() {
        WebDriver driver = new FirefoxDriver(getOptions());
        
        // Configure timeouts to prevent hanging
        // Read from properties with fallback defaults
        int pageLoadTimeout = Integer.parseInt(PropertyReader.getProperty("PAGE_LOAD_TIMEOUT") != null ? 
                PropertyReader.getProperty("PAGE_LOAD_TIMEOUT") : "30");
        int scriptTimeout = Integer.parseInt(PropertyReader.getProperty("SCRIPT_TIMEOUT") != null ? 
                PropertyReader.getProperty("SCRIPT_TIMEOUT") : "30");
        int implicitWait = Integer.parseInt(PropertyReader.getProperty("IMPLICIT_WAIT") != null ? 
                PropertyReader.getProperty("IMPLICIT_WAIT") : "10");
        
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().scriptTimeout(java.time.Duration.ofSeconds(scriptTimeout));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(implicitWait));
        
        readers.Log.info("Driver created with timeouts: pageLoad=" + pageLoadTimeout + "s, script=" + scriptTimeout + "s, implicit=" + implicitWait + "s");
        return driver;
    }

}
