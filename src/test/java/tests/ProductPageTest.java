package tests;

import java.sql.ResultSet;
import enums.*;
import static pages.HomePage.*;
import static utils.SqlJdbcUtil.*;
import static utils.ExcelUtil.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class ProductPageTest extends BaseTest {
    
    @Test
    public void testAddToCart() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        
        String product = Product.ASTROPHYSICS.getTitle();
        
        productPage = searchResultsPage.goToProductPage(product);
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        
        assertTrue(cartPage.cartContains(product));
        
    }
    
    @Test
    public void testAddToCartMultipleQuantities() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        
        String product = Product.DEATH_BY_BLACK_HOLE.getTitle();
        
        productPage = searchResultsPage.goToProductPage(product);
        productPage = productPage.selectQuantity("3");
        addedToCartPage = productPage.addToCart();
        cartPage = addedToCartPage.goToCart();
        
        String selectedProductQuantity = cartPage.getSelectedQuantity(product);
        
        assertTrue(selectedProductQuantity.equals("3"));
        
    }
    
    @Test
    public void testAddToCartBtn() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("calculus");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        addedToCartPage = productPage.addToCart();
        
        String expectedPageTitle = addedToCartPage.getExpectedPageTitle();
        
        assertTrue(addedToCartPage.pageTitleEquals(expectedPageTitle));  
        
    }
    
    @Test
    public void testAuthorLink() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("A Brief History of Time");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        
        String author = productPage.getAuthor();
        
        searchResultsPage = productPage.goToSearchResultsPageForAuthor();
        
        assertTrue(searchResultsPage.pageTitleContains(author));
        
    }
    
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
    
    @Test
    public void verifyAuthorLinkTextColor() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("gravity general relativity");
        productPage = searchResultsPage.goToProductPage(Product.GRAVITY.getTitle());
        
        String color = CssProperty.COLOR.getProperty();
        
        String actualLinkTextColor = productPage.getAuthorLinkCssValue(color);
        String expectedLinkTextColor = getProductPageElementCssValueAt(2, 2);
        
        assertEquals(actualLinkTextColor, expectedLinkTextColor);
        
    }
    
    @Test
    public void verifyAuthorLinkTextFontSize() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("gravity general relativity");
        productPage = searchResultsPage.goToProductPage(Product.GRAVITY.getTitle());
        
        String fontSize = CssProperty.FONT_SIZE.getProperty();
        
        String actualLinkTextFontSize = productPage.getAuthorLinkCssValue(fontSize);
        String expectedLinkTextFontSize = getProductPageElementCssValueAt(2, 5);
        
        assertEquals(actualLinkTextFontSize, expectedLinkTextFontSize);
        
    }
    
}
