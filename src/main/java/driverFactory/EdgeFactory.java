package driverFactory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import readers.Log;
import readers.PropertyReader;

import java.net.URI;

public class EdgeFactory extends AbstractDriver {

    private EdgeOptions getOptions(){
        EdgeOptions options=new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.setAcceptInsecureCerts(true);
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
        WebDriver driver;
        
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") )
        {
            driver = new EdgeDriver(getOptions());
        }
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                driver = new RemoteWebDriver(
                        new URI("http://"+ remoteHost+ ":" +remotePort).toURL(), getOptions()
                );
            }
            catch (Exception e) {
                Log.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        }
        else {
            Log.error("Invalid execution type: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.getProperty("executionType"));
        }
        
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
        
        Log.info("Driver created with timeouts: pageLoad=" + pageLoadTimeout + "s, script=" + scriptTimeout + "s, implicit=" + implicitWait + "s");
        return driver;
    }

}
