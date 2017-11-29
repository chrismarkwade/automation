package tests;

import enums.Product;
import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class CartPageTest extends BaseTest {
    
    @Test
    public void testProceedToCheckoutBtnNotSignedIn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        signInPage = cartPage.proceedToCheckout();
        
        String expectedPageTitle = signInPage.getExpectedPageTitle();
        
        assertTrue(signInPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testProceedToCheckoutBtnSignedIn() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        searchResultsPage = signedInHomePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        placeOrderPage = cartPage.proceedToCheckout();
        
        String expectedPageTitle = placeOrderPage.getExpectedPageTitle();
        
        assertTrue(placeOrderPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testDeleteFromCart() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        
        String productOne = Product.ASTROPHYSICS.getTitle();
        
        productPage = searchResultsPage.goToProductPage(productOne);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        searchResultsPage = cartPage.header().search("cosmos");
        
        String productTwo = Product.COSMOS.getTitle();
        
        productPage = searchResultsPage.goToProductPage(productTwo);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        cartPage = cartPage.deleteFromCart(productOne);
        
        assertTrue(!cartPage.cartContains(productOne));
        
    }
    
    @Test
    public void testUpdateProductQuantity() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("pale blue dot");
        
        String productOne = Product.PALE_BLUE_DOT.getTitle();
        
        productPage = searchResultsPage.goToProductPage(productOne);
        addedToCartPage = productPage.selectQuantity("2").addToCart();
        cartPage = addedToCartPage.goToCart();
        searchResultsPage = cartPage.header().search("cosmos");
        
        String productTwo = Product.COSMOS.getTitle();
        
        productPage = searchResultsPage.goToProductPage(productTwo);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        cartPage = cartPage.updateQuantity(productOne, "3");
        
        String selectedProductQuantity = cartPage.getSelectedQuantity(productOne);
        
        assertTrue(selectedProductQuantity.equals("3"));
        
    }
    
}
