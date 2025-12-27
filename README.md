# 🛒 ITI E-Commerce UI Automation Framework

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.39.0-green.svg)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.29.1-blue.svg)](https://docs.qameta.io/allure/)

A comprehensive **Selenium WebDriver** automation framework for E-Commerce web application testing, built as part of the **ITI Graduation Project**. This framework implements modern design patterns with emphasis on **Fluent Wait**, **DRY principles**, and **Data-Driven Testing**.

---

### 🛍️ Screenshotss
| | |
|:---:|:---:|
| ![Home/Nav](https://i.ibb.co/G3dw1CLz/image.png) | ![Product Grid](https://i.ibb.co/g8W2cHq/image.png) |
| ![Details](https://i.ibb.co/G326vMkq/image.png) | ![Cart View](https://i.ibb.co/Rk0VTPFM/image.png) |
| ![Checkout](https://i.ibb.co/JRjL1b7n/image.png) | ![Login/Auth](https://i.ibb.co/21Qp29ny/image.png) |
| ![Registration](https://i.ibb.co/2J9zKHv/image.png) | ![User Profile](https://i.ibb.co/q3VFKkn7/image.png) |
| ![Admin Panel](https://i.ibb.co/DPy089W1/image.png) | ![Product Mgmt](https://i.ibb.co/fd3Sm9xN/image.png) |
| ![Inventory](https://i.ibb.co/9ky0vZyF/image.png) | ![Edit Product](https://i.ibb.co/LzJRNQLh/image.png) |
| ![Add Item](https://i.ibb.co/mVXxyHkX/image.png) | ![Database View](https://i.ibb.co/J9RWthZ/image.png) |
| ![Settings](https://i.ibb.co/ksmNQHN7/image.png) | ![Logs](https://i.ibb.co/LzTGq2jC/image.png) |
| ![Workflow A](https://i.ibb.co/8nQSJkg4/image.png) | ![Workflow B](https://i.ibb.co/HDNQDYMZ/image.png) |
| ![Workflow C](https://i.ibb.co/k2j9BhZV/image.png) | ![Workflow D](https://i.ibb.co/whDgvg5j/image.png) |
## 🎯 Design Patterns & Architecture

### 1. Fluent API Pattern (Method Chaining)

The framework uses **Fluent API** pattern in `ElementActions` class, allowing method chaining for cleaner, more readable test code:

```java
// Fluent API - Methods return 'this' for chaining
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
    return this;  // Returns self for chaining
}

// Usage in tests - Clean, readable chain
cart.clearCart()
    .acceptAlert()
    .clickCheckout();
```

---

### 2. FluentWait with Smart Exception Handling

The `WaitHandler` class provides configurable **FluentWait** with automatic exception ignoring:

```java
public class WaitHandler {
    private WebDriver driver;

    public FluentWait<WebDriver> fluentWait() {
        // Configurable timeouts from properties file
        int defaultWait = 15;
        int pollingInterval = 100;
        
        try {
            String waitProperty = PropertyReader.getProperty("DEFAULT_WAIT");
            if (waitProperty != null) defaultWait = Integer.parseInt(waitProperty);
        } catch (Exception e) { /* Use defaults */ }
        
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(defaultWait))
                .pollingEvery(Duration.ofMillis(pollingInterval))
                .ignoreAll(getExceptions());  // Auto-ignore common exceptions
    }

    private ArrayList<Class<? extends Exception>> getExceptions() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementClickInterceptedException.class);
        exceptions.add(ElementNotInteractableException.class);
        return exceptions;
    }
}
```

---

### 3. Three Smart Wait Strategies

The framework provides **3 custom smart wait methods** for handling different synchronization scenarios:

#### `gomaaSmartWait` - Basic Visibility + Enabled Check
```java
public void gomaaSmartWait(By locator) {
    wait.fluentWait().until(d -> {
        try {
            scrollToElementJS(locator);
            return d.findElement(locator).isDisplayed() 
                && d.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    });
}
```

#### `gomaaSmartWait2` - Clickability + Visibility + Enabled
```java
public void gomaaSmartWait2(By locator) {
    wait.fluentWait().until(d -> {
        try {
            waitElementExpectedToBeClickable(locator);
            scrollToElementJS(locator);
            return d.findElement(locator).isDisplayed() 
                && d.findElement(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    });
}
```

#### `gomaaSmartWait3` - Animation & Transition Complete (JavaScript-based)
```java
/**
 * Smart wait that combines multiple JavaScript-based conditions:
 * 1. Waits for animation-name to be 'none'
 * 2. Waits for transition-duration to be '0s'
 * Ensures element completed all CSS animations/transitions before proceeding
 */
public void gomaaSmartWait3(By locator) {
    wait.fluentWait().until(d -> {
        try {
            WebElement element = d.findElement(locator);
            
            // Check animation complete
            Boolean animationComplete = (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                    "var el = arguments[0]; " +
                    "return el && getComputedStyle(el).getPropertyValue('animation-name') === 'none';",
                    element
                );
            
            // Check transition complete
            Boolean transitionComplete = (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                    "var el = arguments[0]; " +
                    "return el && getComputedStyle(el).getPropertyValue('transition-duration') === '0s';",
                    element
                );
            
            return (animationComplete != null && animationComplete) && 
                   (transitionComplete != null && transitionComplete);
        } catch (Exception e) {
            return false;
        }
    });
}
```

---

### 4. DRY Design with BaseTestClass

The `BaseTestClass` implements **DRY (Don't Repeat Yourself)** principles using TestNG's `@BeforeMethod` with groups:

```java
@Listeners({TestNGListners.class, AllureTestNg.class})
@Test(dataProviderClass = TestDataProviders.class)
public class BaseTestClass {
    protected WebDriver driver;
    protected ITI_Headers Headers;
    protected ITI_Login login;
    protected ITI_Products products;
    protected ITI_Cart cart;
    protected Assertions Assertions;

    // 🔄 Setup - Runs for ALL tests
    @BeforeMethod(alwaysRun = true, groups = "baseSetup")
    public void Setup() {
        driver = WebDriverFactory.initDriver();
        Headers = new ITI_Headers(driver);
        login = new ITI_Login(driver);
        products = new ITI_Products(driver);
        cart = new ITI_Cart(driver);
    }

    // 👤 Login as User - Only for tests in "needUser" group
    @BeforeMethod(onlyForGroups = "needUser", dependsOnGroups = "baseSetup")
    public void pre_condition_login_as_User() {
        JsonReader loginJsonReader = new JsonReader("Login");
        String username = loginJsonReader.getJsonData("valid.username", "");
        String password = loginJsonReader.getJsonData("valid.password", "");
        Headers.clickLoginButton();
        login.performLogin(username, password);
        Headers.clickProductsButton();
    }

    // 🔑 Login as Admin - Only for tests in "needAdmin" group
    @BeforeMethod(onlyForGroups = "needAdmin", dependsOnGroups = "baseSetup")
    public void pre_requist_login_as_admin() {
        JsonReader loginJsonReader = new JsonReader("Login");
        String username = loginJsonReader.getJsonData("admin.username", "");
        String password = loginJsonReader.getJsonData("admin.password", "");
        Headers.clickLoginButton();
        login.performLogin(username, password);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }
}
```

**Usage in Tests:**
```java
@Test(groups = {"needUser"})  // Auto-login as user before test
public void testAddToCart() { ... }

@Test(groups = {"needAdmin"})  // Auto-login as admin before test
public void testAdminDashboard() { ... }
```

---

### 5. Data-Driven Testing with DataProviders

Centralized `TestDataProviders` class with 40+ DataProviders for comprehensive test coverage:

```java
public class TestDataProviders {
    
    @DataProvider(name = "validUserLoginData")
    public static Object[][] getUserLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("valid.username", ""),
                reader.getJsonData("valid.password", "")
            }
        };
    }

    @DataProvider(name = "cartQuantityData")
    public static Object[][] getCartQuantityData() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
            {
                reader.getJsonData("product1.Name", ""),
                reader.getJsonData("product1.Price", ""),
                reader.getJsonData("product1.PriceDoubled", ""),
                reader.getJsonData("product1.PriceTriple", ""),
                reader.getJsonData("productsAlert.addToCartSuccess", "")
            }
        };
    }
}
```

**Usage in Tests:**
```java
@Test(dataProvider = "cartQuantityData", dataProviderClass = TestDataProviders.class)
public void testCartQuantity(String name, String price, String doubled, 
                             String tripled, String successMsg) {
    // Test data injected automatically from JSON
}
```

---

### 6. Property Reader (Configuration Management)

Loads ALL `.properties` files from resources automatically:

```java
public class PropertyReader {
    public static Properties loadProperties() {
        Properties properties = new Properties();
        // Auto-discover all .properties files
        Collection<File> propertiesFiles = FileUtils.listFiles(
            new File("src/main/resources"),
            new String[]{"properties"}, true
        );
        
        propertiesFiles.forEach(file -> {
            try {
                properties.load(FileUtils.openInputStream(file));
            } catch (Exception e) {
                Log.error("Exception loading: ", file.getName());
            }
        });
        
        // Merge with system properties
        properties.putAll(System.getProperties());
        System.getProperties().putAll(properties);
        return properties;
    }

    public static String getProperty(String key) {
        return System.getProperty(key);
    }
}
```

**waits.properties:**
```properties
DEFAULT_WAIT=15
POLLING_INTERVAL=100
PAGE_LOAD_TIMEOUT=30
SCRIPT_TIMEOUT=30
```

---

### 7. JSON Reader (Test Data Management)

Uses **JSONPath** for flexible data extraction from JSON files:

```java
public class JsonReader {
    private static final String TEST_DATA_PATH = "src/test/java/testData/";
    String jsonReader;

    public JsonReader(String jsonFileName) {
        try {
            JSONObject data = (JSONObject) new JSONParser()
                .parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            jsonReader = "{}";
        }
    }

    // JSONPath query: "valid.username" -> $.valid.username
    public String getJsonData(String jsonPath) {
        return JsonPath.read(jsonReader, jsonPath);
    }

    // Overload with default value
    public String getJsonData(String key, String defaultValue) {
        String value = getJsonData(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }
}
```

**Login.json:**
```json
{
  "valid": {
    "username": "user@example.com",
    "password": "SecurePass123"
  },
  "invaliduser": {
    "username": "wrong@email.com",
    "password": "pass",
    "ExpectedError": "Invalid credentials"
  }
}
```

---

## 🏛️ Project Architecture

```
src/
├── main/java/
│   ├── PageObject/              # Page Object classes (9 pages)
│   │   ├── ITI_Cart.java
│   │   ├── ITI_Login.java
│   │   ├── ITI_Products.java
│   │   └── ...
│   ├── utilActions/             # Core utilities
│   │   ├── ElementActions.java  # 32+ methods with Fluent API
│   │   └── WaitHandler.java     # FluentWait configuration
│   ├── readers/                 # Data readers
│   │   ├── JsonReader.java      # JSONPath data extraction
│   │   └── PropertyReader.java  # Properties loader
│   └── driverFactory/           # WebDriver setup
│
└── test/java/
    ├── BaseTest/                # BaseTestClass (DRY setup)
    ├── Tests/                   # 8 test classes
    ├── DataProviders/           # 40+ DataProviders
    └── testData/                # JSON test data files
```

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Selenium WebDriver | 4.39.0 | Browser Automation |
| TestNG | 7.11.0 | Test Framework |
| Allure | 2.29.1 | Test Reporting |
| Log4j2 | 2.24.3 | Logging |
| Jackson | 2.17.0 | JSON Processing |
| JsonPath | 2.9.0 | JSON Data Extraction |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 21+
- Maven 3.8+
- Chrome/Firefox/Edge browser

### Installation
```bash
git clone https://github.com/mogomaa2025/ITI-Personal-E-Commerce-Java-Selenium-Automation.git
cd ITI-Personal-E-Commerce-Java-Selenium-Automation
mvn clean install -DskipTests
```

### Running Tests
```bash
mvn test                          # Run all tests
mvn test -Dtest=LoginTest         # Run specific test class
mvn test -Dbrowser=chrome         # Run with specific browser
```

### Generate Allure Report
```bash
allure serve test-output/allure-results
```

---

## 📋 Test Coverage

| Module | Description |
|--------|-------------|
| 🔐 Login | Authentication, validation, error handling |
| 📝 Register | Registration flows, field validation |
| 🛒 Cart | Add/remove, quantity, price calculations |
| 📦 Orders | Order placement, history |
| 🔍 Products | Search, filter, categories |
| 🎯 Headers | Navigation, UI verification |

---

## 📚 Documentation

- [`TIMEOUT_CONFIGURATION.md`](./TIMEOUT_CONFIGURATION.md) - Timeout strategy guide
- [`DataProvider_Usage_Guide.md`](./DataProvider_Usage_Guide.md) - Data-driven testing guide
- [`TIMEOUT_QUICK_REFERENCE.md`](./TIMEOUT_QUICK_REFERENCE.md) - Quick timeout reference

---

## 👨‍💻 Author

**Mohamed Gomaa**  
ITI Graduate - Software Testing Track

---

<p align="center">Made with ❤️ for ITI Graduation Project</p>
