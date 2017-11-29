package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class PageHeader extends BasePage {
    
    @FindBy(css = "a[class='nav-logo-link']")
    private WebElement headerLogoLink;
    
    @FindBy(css = "input[id='twotabsearchtextbox']")
    private WebElement searchTextBox;
    
    @FindBy(css = "select[id='searchDropdownBox']")
    private WebElement searchDropdownBox;
    
    @FindBy(css = "a[id='nav-link-shopall']")
    private WebElement shopByDepartmentLink;
    
    @FindBy(css = "span[role='navigation'] span[class='nav-text']")
    private List<WebElement> shopDropdownCategoryList;
    
    @FindBy(css = "a[href*='ref=nav_shopall_fullstore'] span")
    private WebElement shopDropdownFullStoreDirectoryLink;
    
    @FindBy(css = "div[class*='nav-subcats'] a[href] span[class='nav-text']")
    private List<WebElement> shopDropdownDepartmentList;
    
    @FindBy(xpath = "//span[contains(text(), 'Hello')]")
    private WebElement helloGreeting;
    
    @FindBy(css = "a[class*='nav-a nav-a-2'][data-nav-role='signin']")
    private WebElement accountLink;
    
    @FindBy(css = "a[data-nav-ref='nav_signin']")
    private WebElement signInBtn;
    
    @FindBy(css = "a[id='nav-item-signout']")
    private WebElement signOutLink;
    
    @FindBy(css = "a[id='nav-cart']")
    private WebElement cartLink;
    
    public PageHeader(WebDriver driver) {
        super(driver);
    }
    
    private PageHeader typeSearchKeyword(String keyword) {
        type(searchTextBox, keyword);
        return this;
    }
    
    private SearchResultsPage submitSearch() {
        submit(searchTextBox);
        waitUntilUrlContains("/s/");
        return pageFactoryObject(getDriver(), SearchResultsPage.class);
    }
    
    public SearchResultsPage search(String keyword) {
        typeSearchKeyword(keyword);
        return submitSearch();
    }
    
    public PageHeader selectSearchDepartment(String department) {
        click(searchDropdownBox);
        selectDropdownOption(searchDropdownBox, department);
        return this;
    }
    
    public HomePage goToHomePage() {
        waitAndClick(headerLogoLink);
        waitUntilUrlContains("logo");
        return pageFactoryObject(getDriver(), HomePage.class);
    }
    
    public DepartmentPage goToDepartmentPage(String category, String linkText) {
        hoverOver(shopByDepartmentLink);
        waitUntilAllElementsAreVisible(shopDropdownCategoryList);
        hoverOverListCategory(shopDropdownCategoryList, category);
        clickListLink(shopDropdownDepartmentList, linkText);
        waitUntilUrlContains("/b");
        return pageFactoryObject(getDriver(), DepartmentPage.class);
    }
    
    public StoreDirectoryPage goToStoreDirectoryPage() {
        hoverOver(shopByDepartmentLink);
        click(shopDropdownFullStoreDirectoryLink);
        waitUntilUrlContains("directory");
        return pageFactoryObject(getDriver(), StoreDirectoryPage.class);
    }
    
    public SignInPage goToSignInPage() {
        hoverOver(accountLink);
        waitAndClick(signInBtn);
        waitUntilTitleContains("Sign In");
        return pageFactoryObject(getDriver(), SignInPage.class);
    }
    
    public SignInPage signOut() {
        hoverOver(accountLink);
        click(signOutLink);
        waitUntilTitleContains("Sign In");
        return pageFactoryObject(getDriver(), SignInPage.class);  
    }
    
    public CartPage goToCartPage() {
        click(cartLink);
        waitUntilTitleContains("Cart");
        return pageFactoryObject(getDriver(), CartPage.class);
    }
    
    public List<String> getSearchDropdownDepartments() {
        click(searchDropdownBox);
        return getDropdownOptions(searchDropdownBox);
    }
    
    public List<String> getShopDropdownCategories() {
        hoverOver(shopByDepartmentLink);
        return getElementsInnerText(shopDropdownCategoryList);
    }
    
    public String getHelloGreeting() {
        return getElementInnerText(helloGreeting);
    }
    
}
