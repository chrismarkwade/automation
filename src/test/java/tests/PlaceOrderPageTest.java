package tests;

import java.util.List;
import java.sql.ResultSet;
import enums.Product;
import static pages.HomePage.*;
import static utils.FileUtil.*;
import static utils.SqlJdbcUtil.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

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
    
    @Test
    public void testPlaceOrderBtn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPage(Product.COSMOS.getTitle());
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        thankYouPage = placeOrderPage.placeOrder();
        
        String expectedPageTitle = thankYouPage.getExpectedPageTitle();
        
        assertTrue(thankYouPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void verifyDeliveryOptionsForProductEligibleForFreeShipping() {
        
        homePage = open();
        
        String sql = "SELECT Title, OfferPrice, Stock " + 
                     "FROM AmazonCA.dbo.BookProducts " + 
                     "WHERE OfferPrice > 35 AND Stock >= 1";
        
        ResultSet resultSet = executeQuery(sql);
        
        String product = getRandomRowColumnValue(1, resultSet);
        
        searchResultsPage = homePage.header().search(product);   
        productPage = searchResultsPage.goToProductPage(product);
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        
        List<String> actualDeliveryOptions = placeOrderPage.getDeliveryOptions();
        List<String> expectedDeliveryOptions = getPlaceOrderDeliveryOptions();
        
        assertEquals(actualDeliveryOptions, expectedDeliveryOptions);
        
    }
    
    @Test
    public void verifyOrderSummaryContainsFreeShippingWhenSelected() {
        
        homePage = open();
        
        String sql = "SELECT * " + 
                     "FROM AmazonCA.dbo.BookProducts " + 
                     "WHERE OfferPrice > 35 AND Stock >= 1";
        
        ResultSet resultSet = executeQuery(sql);   
        
        String product = getRandomRowColumnValue(1, resultSet);
        
        searchResultsPage = homePage.header().search(product);
        productPage = searchResultsPage.goToProductPage(product);
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        placeOrderPage.selectFreeShipping();
        
        List<String> orderSummary = placeOrderPage.getOrderSummaryLines();
        String freeShipping = "FREE Shipping:";
        
        assertTrue(orderSummary.contains(freeShipping));
        
    }
    
    @Test
    public void verifyDeliveryPromiseTextForOneDayDelivery() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        searchResultsPage = signedInHomePage.header().search("cosmos");
        productPage = searchResultsPage.goToProductPage(Product.COSMOS.getTitle());
        addedToCartPage = productPage.addToCart();
        placeOrderPage = addedToCartPage.proceedToCheckout();
        placeOrderPage.selectOneDayDelivery();
        
        String actualDeliveryPromiseText = placeOrderPage.getDeliveryPromiseText();
        String expectedDeliveryPromiseText = "Guaranteed delivery date:";
        
        assertEquals(actualDeliveryPromiseText, expectedDeliveryPromiseText);
        
    }
    
}
