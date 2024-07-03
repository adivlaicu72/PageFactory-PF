package selenium.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrappers extends BaseTest{
	

	public SeleniumWrappers(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//WebElement element  =  driver.findElement(locator)
	//element.click();
	
	/**
	 * Wrapper method over selenium default click() enhanced with:
	 * 1.waitForElementToBeVisible
	 * 2.retry mechanism for NoSuchElement 
	 * @param locator
	 */
	
	public void click(WebElement element) { //clasa By se importa in cazul PageFactory
		Log.info("called method <click> on element " + element);
		try {
			Log.info("called method Wait for visibility of on element " + element);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			//WebElement element  =  driver.findElement(locator);
			element.click();
			Log.info("<click()> performed on element" + element);
			
		}catch(NoSuchElementException e) {
			Log.error("catch in <click()> with error " + e.getMessage());
		}	
	}
	
	public void sendKeys(WebElement element, String text) {
		Log.info("called method <sendKeys()> on element " + element);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			//WebElement element  =  driver.findElement(locator);
			Log.info("called method <clear()> on element" + element);
			element.clear();
			element.sendKeys(text);	
			Log.info("method <sendKeys()> executed on element" + element);
			
		}catch(NoSuchElementException e) {
			
		}	
		
	}
	
	public String getElementText(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));		
		return element.getText();
		
	}
	
	public void hoverElement(By locator) {
		WebElement element = driver.findElement(locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		
	}
	
	
	public void dragAndDrop(By locator, int x, int y) {
		WebElement element = driver.findElement(locator);
		Actions action = new Actions(driver);
		action
			.moveToElement(element)
			.clickAndHold(element)
			.moveByOffset(x, y)
			.release()
			.perform();
	}
	
	
	public void scrollVertically(int y) {
		Actions action = new Actions(driver);
		action.scrollByAmount(0, y).perform();
		
	}
	
	public void scrollHorizontally(int x) {
		Actions action = new Actions(driver);
		action.scrollByAmount(x, 0).perform();
	}
	
	
	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}
	
	public boolean checkElementIsDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
   }
	
	public void selectDropDownValue(By locator, Object selection) {
        
        Select select = new Select(getWebElement(locator));
        
        if(selection.getClass() == Integer.class) {
            select.selectByIndex((int) selection);
        }else if(selection.getClass() == String.class){
            select.selectByValue((String) selection);
        }
   }
	
	 public String getFirstSelectedOption(By locator) {
         
         Select select = new Select(getWebElement(locator));
         return select.getFirstSelectedOption().getText();
    }
	 
	 public List<WebElement> getWebElementList(By locator) {
         return driver.findElements(locator);
    }
	
	
	
	
}