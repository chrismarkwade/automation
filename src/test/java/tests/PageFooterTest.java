package tests;

import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class PageFooterTest extends BaseTest {
    
    @Test
    public void testFooterLogoLink() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("history of mathematics");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        homePage = productPage.footer().goToHomePage();
        
        String expectedUrlPart = "ref=footer_logo";
        
        assertTrue(homePage.pageUrlContains(expectedUrlPart));
        
    }
    
    @Test
    public void testAmazonPrimeLink() {
        
        homePage = open();
        primeLandingPage = homePage.footer().goToPrimeLandingPage();
        
        String expectedPageTitle = primeLandingPage.getExpectedPageTitle();
        
        assertTrue(primeLandingPage.pageTitleEquals(expectedPageTitle));
        
    }
    
}
