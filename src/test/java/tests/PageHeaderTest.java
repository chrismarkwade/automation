package tests;

import java.util.List;
import enums.*;
import static pages.HomePage.*;
import static utils.FileUtil.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

/**
 * @author Chris Wade
 */

public class PageHeaderTest extends BaseTest {
    
    @Test(dataProvider = "keywordData")
    public void testSearchInAllDepartments(String keyword) {
        
        homePage = open();
        searchResultsPage = homePage.header().search(keyword);
        
        String product = Product.COSMOS.getTitle();
        
        assertTrue(searchResultsPage.searchResultsContains(product));
        
    }
    
    @DataProvider(name = "keywordData")
    public Object[][] keywordData() {
        
        Object[][] data = new Object[3][1];
        data[0][0] = "cosmos";
        data[1][0] = "carl sagan";
        data[2][0] = "astrophysics";
        
        return data;
    }
    
    @Test
    public void testSearchInDepartment() {
        
        homePage = open();
        
        String department = Department.MUSIC.getName();
        
        pageHeader = homePage.header().selectSearchDepartment(department);
        searchResultsPage = pageHeader.search("The Clash");
        
        String departmentLinkText = searchResultsPage.getDepartmentLinkText();
        
        boolean pageTitleContainsDepartment = searchResultsPage.pageTitleContains(department);
        boolean departmentLinkTextEqualsDepartment = departmentLinkText.equals(department);
        
        assertTrue(pageTitleContainsDepartment || departmentLinkTextEqualsDepartment);
        
    }
    
    @Test
    public void verifySearchDropdownDepartments() {
        
        homePage = open();
        
        List<String> actualDropdownList = homePage.header().getSearchDropdownDepartments();
        List<String> expectedDropdownList = getHeaderSearchDepartmentList();
        
        assertEquals(actualDropdownList, expectedDropdownList);
        
    }
    
    @Test
    public void testHeaderLogoLink() {
        
        homePage = open();
        searchResultsPage = homePage.header().search("calculus");
        productPage = searchResultsPage.goToProductPageAtIndex(0);
        homePage = productPage.header().goToHomePage();
        
        String expectedUrlPart = "ref=nav_logo";
        
        assertTrue(homePage.pageUrlContains(expectedUrlPart));
        
    }
    
    @Test
    public void verifyShopDropdownCategories() {
        
        homePage = open();
        
        List<String> actualDropdownList = homePage.header().getShopDropdownCategories();
        List<String> expectedDropdownList = getHeaderShopCategoryList();
        
        assertEquals(actualDropdownList, expectedDropdownList);
        
    }
    
    @Test
    public void testShopDropdownAllComputersLink() {
        
        homePage = open();
        
        String category = Category.ELECTRONICS.getCategory();
        String departmentLink = Department.COMPUTERS.getLinkText();
        
        departmentPage = homePage.header().goToDepartmentPage(category, departmentLink);
        
        String department = Department.COMPUTERS.getName();
        
        assertTrue(departmentPage.pageTitleContains(department));
        
    }
    
    @Test
    public void testSignInBtn() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        
        String expectedPageTitle = signInPage.getExpectedPageTitle();
        
        assertTrue(signInPage.pageTitleEquals(expectedPageTitle));
        
    }
    
    @Test
    public void testSignOut() {
        
        homePage = open();
        signInPage = homePage.header().goToSignInPage();
        signedInHomePage = signInPage.signIn(email, password);
        signInPage = signedInHomePage.header().signOut();
        homePage = signInPage.goToHomePage();
        
        String helloGreeting = homePage.header().getHelloGreeting();
        
        assertTrue(!helloGreeting.contains(firstName));
        
    }
    
}
