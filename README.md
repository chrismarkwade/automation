# Amazon Automation

A test automation framework for Amazon.ca.

Written in Java, using Selenium WebDriver, TestNG, Maven, and the Page Object Model.

An automation portfolio project by [Chris Wade](https://ca.linkedin.com/in/chrismarkwade "LinkedIn").


## Features

- Use of the PageFactory class and the FindBy annotation to create Page Objects

- CSS and XPath selectors to find HTML elements on a page

- Methods for automating links, buttons, textboxes, dropdowns, radio buttons and checkboxes

- Explicit waits

- Enums to store and get Amazon category, department, and product names for tests

- Take screenshot on test failure

- TestNG DataProviders to create data-driven tests

- TestNG XML test suites to run parallel cross-browser tests

- TestNG Listeners

- Log4j Loggers


## Utilities

- A DriverFactory utility class for encapsulating driver object creation in a static factory method

- A FileUtil class for reading text files using Apache Commons IO

- An ExcelUtil class for reading data from MS Excel files using Apache POI

- A PropertyUtil class for reading property values from a properties file

- An ExecuteJavaScript utility class for executing JavaScript in the browser window

- A TakeScreenshot utility class for taking screenshots

- A SqlJdbcUtil class for executing SQL statements and retrieving database result set values


## Maven

### Selenium-Java dependency

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.6.0</version>
</dependency>
```

### TestNG dependency

```xml
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>6.11</version>
    <scope>test</scope>
</dependency>
```


## Test Examples

### Test Place Order

```java
public class PlaceOrderPageTest extends BaseTest {
    
    @Test
    public void testPlaceOrder() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPage(Product.ASTROPHYSICS.getTitle());
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        thankYouPage = placeOrderPage.placeOrder();
        
        assertTrue(thankYouPage.orderNumberIsDisplayed());
        
    }
}
```

Video: [Test Place Order](https://youtu.be/O8Cs4V_rNWI "YouTube Video")

### Verify Product Offer Price

```java
public class ProductPageTest extends BaseTest {
    
    @Test
    public void verifyProductOfferPrice() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPage(Product.ASTROPHYSICS.getTitle());
        
        double actualOfferPrice = productPage.getProductOfferPrice();
        
        String sql = "SELECT Title, ProdID, OfferPrice " + 
                     "FROM AmazonCA.dbo.BookProducts " + 
                     "WHERE ProdID = '0393609391'";
        
        ResultSet resultSet = executeQuery(sql);
        
        double expectedOfferPrice = getRowColumnDoubleValue(1, 3, resultSet);
        
        assertEquals(actualOfferPrice, expectedOfferPrice);
        
    }
}
```

Video: [Verify Product Offer Price](https://youtu.be/5ZeZS52_sk4 "YouTube Video")

***

Test videos can be found here:
[Amazon Automation Videos](https://www.youtube.com/playlist?list=PLg5BxwBsa3IrFS8LBK7yussh3NsnKeZyV "YouTube Playlist")
