package tests;

import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class SignInPageTest extends BaseTest {
    
    @Test
    public void testSignInFromHeader() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        
        String helloGreeting = signedInHomePage.header().getHelloGreeting();
        
        assertTrue(helloGreeting.contains(firstName));
        
    }
    
    @Test
    public void testSignInFromAddedToCartPage() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("cosmos");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        placeOrderPage = signInPage.signIn(email, password);
        
        String expectedPageTitle = placeOrderPage.getExpectedPageTitle();
        
        assertTrue(placeOrderPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void verifyEnterPasswordErrorMessage() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signInPage = signInPage.signInExpectingError(email);
        
        String actualErrorMessage = signInPage.getEnterPasswordErrorMsg();
        String expectedErrorMessage = signInPage.getExpectedEnterPasswordErrorMsg();
        
        assertEquals(actualErrorMessage, expectedErrorMessage);
        
    }
    
}
