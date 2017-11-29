package tests;

import enums.Product;
import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class ThankYouPageTest extends BaseTest {
    
    @Test
    public void testContiuneShoppingBtn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPage(Product.COSMOS.getTitle());
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        thankYouPage = placeOrderPage.placeOrder();
        signedInHomePage = thankYouPage.goToSignedInHomePage();
        
        String expectedUrlPart = "ref=co_typ";
        
        assertTrue(signedInHomePage.pageUrlContains(expectedUrlPart));
        
    }
    
    @Test
    public void verifyThankYouMessage() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("cosmos");
        productPage = searchResultsPage.goToProductPage(Product.DEATH_BY_BLACK_HOLE.getTitle());
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        thankYouPage = placeOrderPage.placeOrder();
        
        String actualThankYouMessage = thankYouPage.getThankYouMsg();
        String expectedThankYouMessage = thankYouPage.getExpectedThankYouMsg();
        
        assertEquals(actualThankYouMessage, expectedThankYouMessage);
        
    }
    
}
