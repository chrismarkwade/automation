package pages;

import java.util.List;
import static utils.DriverFactory.getDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Chris Wade
 */

public class PageLeftNav extends BasePage {
    
    @FindBy(css = "span[class*='a-checkbox-label'] span")
    private List<WebElement> checkboxList;
    
    @FindBy(css = "ul li a h2")
    private List<WebElement> searchResultsList;
    
    public PageLeftNav(WebDriver driver) {
        super(driver);
    }
    
    public SearchResultsPage refineBy(String checkboxOption) {
        waitUntilAllElementsAreVisible(checkboxList);
        clickCheckbox(checkboxList, checkboxOption);
        waitUntilAllElementsAreVisible(searchResultsList);
        return pageFactoryObject(getDriver(), SearchResultsPage.class);     
    }
    
}
