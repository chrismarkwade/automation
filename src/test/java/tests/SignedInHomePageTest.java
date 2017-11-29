package tests;

import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class SignedInHomePageTest extends BaseTest {
    
    @Test
    public void testFirstCarouselProductImageLink() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        signedInHomePage.moveFirstCarouselScrollbarBy(320);
        productPage = signedInHomePage.goToFirstCarouselProductPageAtIndex(9);
        
        String productTitle = productPage.getProductTitle();
        
        assertTrue(productPage.pageTitleContains(productTitle));
        
    }
    
}
