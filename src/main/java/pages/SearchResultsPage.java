package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class SearchResultsPage extends BasePage {
    
    private static final Logger log = LogManager.getLogger();
    private String expectedUrlPart = "/s/";
    
    @FindBy(css = "ul li a h2")
    private List<WebElement> searchResultsList;
    
    @FindBy(css = "select[id='sort']")
    private WebElement sortByDropdown;
    
    @FindBy(css = "span[id='pagnNextString']")
    private WebElement nextPageLink;
    
    @FindBy(css = "p a[href^='/s/']")
    private List<WebElement> searchInDepartmentLink;
    
    @FindBy(css = "p a[href^='/s/']")
    private WebElement searchDepartmentLink;
    
    @FindBy(css = "h1 a[href^='/l/']")
    private List<WebElement> noResultsInDepartmentLink;
    
    @FindBy(css = "h1 a[href^='/l/']")
    private WebElement noResultsDepartmentLink;
    
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        
        if (!pageUrlContains(expectedUrlPart)) {
            log.error(getPageLoadError());
            log.error(getCurrentPageTitle());
            throw new IllegalStateException(getPageLoadError());
        }
    }
    
    public PageHeader header() {
        return pageFactoryObject(getDriver(), PageHeader.class);
    }
    
    public PageFooter footer() {
        return pageFactoryObject(getDriver(), PageFooter.class);
    }
    
    public PageLeftNav leftNav() {
        return pageFactoryObject(getDriver(), PageLeftNav.class);
    }
    
    public ProductPage goToProductPage(String product) {
        waitUntilAllElementsAreVisible(searchResultsList);
        clickListLink(searchResultsList, product);
        waitUntilTitleContains(product);
        return pageFactoryObject(getDriver(), ProductPage.class);
    }
    
    public ProductPage goToProductPageAtIndex(int index) {
        waitUntilAllElementsAreVisible(searchResultsList);
        WebElement productTitleLink = getElementAtIndex(searchResultsList, index);
        scrollIntoView(productTitleLink);
        click(productTitleLink);
        waitUntilUrlContains("p/");
        return pageFactoryObject(getDriver(), ProductPage.class);
    }
    
    public ProductPage goToRandomProductPage() {
        waitUntilAllElementsAreVisible(searchResultsList);
        WebElement productTitleLink = getElementAtRandomIndex(searchResultsList);
        scrollIntoView(productTitleLink);
        click(productTitleLink);
        waitUntilUrlContains("p/");
        return pageFactoryObject(getDriver(), ProductPage.class);
    }
    
    public SearchResultsPage sortBy(String option) {
        waitAndClick(sortByDropdown);
        selectDropdownOption(sortByDropdown, option);
        waitUntilAllElementsAreVisible(searchResultsList);
        return this;
    }
    
    public SearchResultsPage goToNextPage() {
        waitUntilElementIsVisible(nextPageLink);
        scrollIntoView(nextPageLink);
        click(nextPageLink);
        waitUntilAllElementsAreVisible(searchResultsList);
        return pageFactoryObject(getDriver(), SearchResultsPage.class);
    }
    
    public List<String> getSearchResults() {
        waitUntilAllElementsAreVisible(searchResultsList);
        return getElementsInnerText(searchResultsList);
    }
    
    public String getSearchResultAtIndex(int index) {
        waitUntilAllElementsAreVisible(searchResultsList);
        WebElement productTitleLink = getElementAtIndex(searchResultsList, index);
        return getElementInnerText(productTitleLink);
    }
    
    public boolean searchResultsContains(String product) {
        return getSearchResults().contains(product);
    }
    
    public String getDepartmentLinkText() {
        
        if (elementIsDisplayed(searchInDepartmentLink)) {
            return getElementInnerText(searchDepartmentLink);
        }
        else if (elementIsDisplayed(noResultsInDepartmentLink)) {
            return getElementInnerText(noResultsDepartmentLink);
        }
        else {
            return "Department link not displayed";
        }
    }
    
}
