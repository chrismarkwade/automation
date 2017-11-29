package tests;

import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class AddedToCartPageTest extends BaseTest {
    
    @Test
    public void testCartBtn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPageAtIndex(1);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        
        String expectedPageTitle = cartPage.getExpectedPageTitle();
        
        assertTrue(cartPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testProceedToCheckoutBtnNotSignedIn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("cosmology");
        productPage = searchResultsPage.goToProductPageAtIndex(1);
        addedToCartPage = productPage.addToCart();
        signInPage = addedToCartPage.proceedToCheckout();
        
        String expectedPageTitle = signInPage.getExpectedPageTitle();
        
        assertTrue(signInPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testProceedToCheckoutBtnSignedIn() {
       
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        searchResultsPage = signedInHomePage.header().search("cosmos");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        addedToCartPage = productPage.addToCart();
        placeOrderPage = addedToCartPage.proceedToCheckout();
        
        String expectedPageTitle = placeOrderPage.getExpectedPageTitle();
        
        assertTrue(placeOrderPage.pageTitleEquals(expectedPageTitle));
        
    }
    
}
