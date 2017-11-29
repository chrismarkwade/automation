package tests;

import static pages.HomePage.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Chris Wade
 */

public class StoreDirectoryPageTest extends BaseTest {
    
    @Test
    public void testAllMoviesAndTvShowsLinks() {
        
        homePage = open();
        storeDirectoryPage = homePage.header().goToStoreDirectoryPage();
        
        assertTrue(storeDirectoryPage.allMoviesAndTvShowsLinksOk());
        
    }
    
    @Test
    public void testAllElectronicsLinks() {
        
        homePage = open();
        storeDirectoryPage = homePage.header().goToStoreDirectoryPage();
        
        assertTrue(storeDirectoryPage.allElectronicsLinksOk());
        
    }
    
}
