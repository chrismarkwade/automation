package pages;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import static utils.DriverFactory.getDriver;
import static utils.ExecuteJavaScript.scrollIntoView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * @author Chris Wade
 */

public abstract class BasePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.builder = new Actions(driver);
    }
    
    static <T extends BasePage> T pageFactoryObject(WebDriver driver, Class<T> pageClassToProxy) {
        T page = PageFactory.initElements(driver, pageClassToProxy);
        return page;
    }
    
    public final String getPageUrl() {
        return driver.getCurrentUrl();
    }
    
    public final String getPageTitle() {
        return driver.getTitle();
    }
    
    public final String getCurrentPageTitle() {
        return "Current page: " + getPageTitle();
    }
    
    public final String getPageLoadError() {
        return getClass().getSimpleName() + " not displayed";
    }
    
    public final boolean pageTitleContains(String titleText) { 
        return getPageTitle().contains(titleText);
    }
    
    public final boolean pageTitleEquals(String expectedPageTitle) { 
        return getPageTitle().equals(expectedPageTitle);
    }
    
    public final boolean pageUrlContains(String urlPart) {
        return getPageUrl().contains(urlPart);
    }
    
    public final boolean pageUrlEquals(String expectedPageUrl) {
        return getPageUrl().equals(expectedPageUrl);
    }
    
    public void navigateBack() {
        driver.navigate().back();
    }
    
    public void navigateForward() {
        driver.navigate().forward();
    }
    
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
    static void load(String url) {
        getDriver().get(url);
    }
    
    void type(WebElement element, String keyword) {
        element.sendKeys(keyword);
    }
    
    void submit(WebElement element) {
        element.submit();
    }
    
    void click(WebElement element) {
        element.click();
    }
    
    void waitAndClick(WebElement element) {
        waitUntilElementIsVisible(element);
        click(element);
    }
    
    void clickRadioButton(List<WebElement> buttonList, String valueAttributeValue) {
        
        for (WebElement button : buttonList) {
            if (!button.isSelected() && button.getAttribute("value").equals(valueAttributeValue)) {
                scrollIntoView(button);
                click(button);
                break;
            }
        }
    }
    
    void clickCheckbox(List<WebElement> checkboxList, String checkboxOption) {
        
        for (WebElement checkbox : checkboxList) {
            if (checkbox.getText().equals(checkboxOption)) {
                scrollIntoView(checkbox);
                click(checkbox);
                break;
            }
        }
    }
    
    void clickListButton(List<WebElement> buttonList, String attribute, String text) {
        
        for (WebElement button : buttonList) {
            if (button.getAttribute(attribute).contains(text)) {
                scrollIntoView(button);
                click(button);
                break;
            }
        }
    }
    
    void clickListLink(List<WebElement> linkList, String linkText) {
        
        for (WebElement link : linkList) {
            if (link.getText().equals(linkText)) {
                scrollIntoView(link);
                click(link);
                break;
            }
        }
    }
    
    boolean elementIsDisplayed(List<WebElement> elementList) {
        return elementList.size() == 1;
    }
    
    boolean elementsAreDisplayed(List<WebElement> elementList) {
        return elementList.size() >= 1;
    }
    
    WebElement getElementAtIndex(List<WebElement> elementList, int index) {
        return elementList.get(index);
    }
    
    WebElement getElementAtRandomIndex(List<WebElement> elementList) {
        int randomIndex = new Random().nextInt(elementList.size());
        return elementList.get(randomIndex);
    }
    
    String getElementSize(WebElement element) {
        return element.getSize().toString();
    }
    
    String getElementLocation(WebElement element) {
        return element.getLocation().toString();
    }
    
    String getElementCssValue(WebElement element, String cssProperty) {
        return element.getCssValue(cssProperty);
    }
    
    String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }
    
    List<String> getElementsAttributeValue(List<WebElement> elementList, String attribute) {
        
        List<String> list = new ArrayList<>();
        
        for (WebElement element : elementList) {
            String attributeValue = element.getAttribute(attribute);      
            list.add(attributeValue);
        }
        return list;
    }
    
    String getElementInnerText(WebElement element) {
        return element.getText();
    }
    
    List<String> getElementsInnerText(List<WebElement> elementList) {
        
        List<String> list = new ArrayList<>();
        
        for (WebElement element : elementList) {
            String innerText = element.getText();
            list.add(innerText);
        }
        return list;
    }
    
    void waitUntilElementIsVisible(WebElement element) {
        wait.until(visibilityOf(element));
    }
    
    void waitUntilAllElementsAreVisible(List<WebElement> elementList) { 
        wait.until(visibilityOfAllElements(elementList));
    }
    
    void waitUntilTitleContains(String title) {
        wait.until(titleContains(title));
    }
    
    void waitUntilUrlContains(String urlPart) {
        wait.until(urlContains(urlPart));
    }
    
    void waitUntilElementInnerTextContains(WebElement element, String text) {
        wait.until(textToBePresentInElement(element, text));
    }
    
    void waitUntilRefreshedElementIsVisible(WebElement element) {
        wait.until(refreshed(visibilityOf(element)));
    }
    
    void selectDropdownOption(WebElement element, String option) {
        Select dropdown = new Select(element);
        
        if (!element.isSelected()) {
            dropdown.selectByVisibleText(option);
        }
    }
    
    String getSelectedDropdownOptionValue(WebElement element) {
        Select dropdown = new Select(element);
        WebElement selectedDropdownOption = dropdown.getFirstSelectedOption();
        return getElementAttributeValue(selectedDropdownOption, "value");
    }
    
    List<String> getDropdownOptions(WebElement element) {
        Select dropdown = new Select(element);
        List<WebElement> elementList = dropdown.getOptions();
        return getElementsInnerText(elementList);
    }
    
    void hoverOver(WebElement element) {
        builder.moveToElement(element).perform();
    }
    
    void hoverOverListCategory(List<WebElement> elementList, String category) {
        
        for (WebElement element : elementList) {
            if (element.getText().equals(category)) {
                hoverOver(element);
                break;
            }
        }
    }
    
    void clickAndDrag(WebElement element, int xOffset, int yOffset) {
        builder.clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }
    
    @SuppressWarnings("unchecked")          
    <T> T cast(Object obj) {
        return (T) obj;
    }
    
}
