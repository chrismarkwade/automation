package tests;

import enums.Product;
import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

/**
 * @author Chris Wade
 */

public class SearchResultsPageTest extends BaseTest {
    
    @Test
    public void testProductTitleLink() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("astrophysics");
        productPage = searchResultsPage.goToProductPage(Product.DEATH_BY_BLACK_HOLE.getTitle());
        
        String productTitle = productPage.getProductTitle();
        
        assertTrue(productPage.pageTitleContains(productTitle));
        
    }
    
    @Test(dataProvider = "keywordData")
    public void testRandomProductTitleLink(String keyword) {
        
        homePage = open();
        searchResultsPage = homePage.header().search(keyword);
        productPage = searchResultsPage.goToRandomProductPage();
        
        String productTitle = productPage.getProductTitle();
        
        assertTrue(productPage.pageTitleContains(productTitle));
        
    }
    
    @DataProvider(name = "keywordData")
    public Object[][] keywordData() {
        
        Object[][] data = new Object[3][1];
        data[0][0] = "lamp";
        data[1][0] = "table";
        data[2][0] = "laptop";
        
        return data;
    }
    
    @Test
    public void testSortByAvgCustomerReview() {
        
        homePage = open();
        pageHeader = homePage.header().selectSearchDepartment("Books");
        searchResultsPage = pageHeader.search("apollo moon");
        searchResultsPage.sortBy("Avg. Customer Review");
        
        String actualResultAtIndexZero = searchResultsPage.getSearchResultAtIndex(0);
        String expectedResultAtIndexZero = Product.A_MAN_ON_THE_MOON.getTitle();
        
        assertEquals(actualResultAtIndexZero, expectedResultAtIndexZero);
        
    }
    
    @Test
    public void testRefineByVideoFormat() {
        
        homePage = open();
        pageHeader = homePage.header().selectSearchDepartment("Movies & TV");
        searchResultsPage = pageHeader.search("blade runner");
        
        String dvd = "DVD";
        
        searchResultsPage = searchResultsPage.leftNav().refineBy(dvd);
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        
        String videoFormat = productPage.getVideoFormat();
        
        assertTrue(videoFormat.equals(dvd));
        
    }
    
    @Test
    public void testNextPageLink() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("quantum mechanics");
        searchResultsPage = searchResultsPage.goToNextPage().goToNextPage();
        
        String expectedUrlPart = "ref=sr_pg_3";
        
        assertTrue(searchResultsPage.pageUrlContains(expectedUrlPart));
        
    }
    
}
