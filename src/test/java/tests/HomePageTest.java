package tests;

import enums.*;
import static pages.HomePage.*;
import static utils.ExcelUtil.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class HomePageTest extends BaseTest {
    
    @Test
    public void testSignInSecurelyBtn() {
        
        homePage = open();
        signInPage = homePage.goToSignInPage();
        
        String expectedPageTitle = signInPage.getExpectedPageTitle();
        
        assertTrue(signInPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testStartHereLink() {
        
        homePage = open();
        createAccountPage = homePage.goToCreateAccountPage();
        
        String expectedPageTitle = createAccountPage.getExpectedPageTitle();
        
        assertTrue(createAccountPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void verifyStartHereLinkTextColor() {
        
        homePage = open();
        
        String color = CssProperty.COLOR.getProperty();
        
        String actualLinkTextColor = homePage.getStartHereLinkCssValue(color);
        String expectedLinkTextColor = getHomePageElementCssValueAt(2, 2);
        
        assertEquals(actualLinkTextColor, expectedLinkTextColor);
        
    }
    
    @Test
    public void verifyStartHereLinkTextFontSize() {
        
        homePage = open();
        
        String fontSize = CssProperty.FONT_SIZE.getProperty();
        
        String actualLinkTextFontSize = homePage.getStartHereLinkCssValue(fontSize);
        String expectedLinkTextFontSize = getHomePageElementCssValueAt(2, 5);
        
        assertEquals(actualLinkTextFontSize, expectedLinkTextFontSize);
        
    }
    
}
