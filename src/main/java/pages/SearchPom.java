package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPom {
	WebDriver driver;
	WebDriverWait wait;
	
// Web elements
    @FindBy(xpath = "//a[@class='active']")
    WebElement Flights;
    
	@FindBy(xpath = "//input[@id='origin']")
    WebElement leavingFromField;

    @FindBy(xpath = "//input[@id='destination']")
    WebElement goingToField;

    @FindBy(xpath = "//input[@id='dep_dt']")
    WebElement depdatefield;
    
	@FindBy(xpath = "//input[@id='ret_dt']")
	WebElement retdatefield;
	
	@FindBy(xpath = "//select[@id='cabin']")
    WebElement cabinclass;
	
    @FindBy(xpath = "//button[@id='fdimgbutton']")
    WebElement Search;
    
    

// Constructor
    public SearchPom(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
// Methods to implement the Webelements.
    public void clickFlightsTab() {
    	wait.until(ExpectedConditions.elementToBeClickable(Flights));
    	Flights.click();
    }

   
    public void enterLeavingFrom(String departureLocation) {
    	wait.until(ExpectedConditions.visibilityOf(leavingFromField));
        leavingFromField.click(); 
        leavingFromField.clear();
        leavingFromField.sendKeys(departureLocation);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        leavingFromField.sendKeys(Keys.ENTER);;
    }

 
    public void enterGoingTo(String destination) {
        goingToField.click(); // Open the location picker
        goingToField.sendKeys(destination);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        goingToField.sendKeys(Keys.ENTER);
    }

    // Method to interact with the Dates field
   public void depdate(String date) {
	   depdatefield.click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
	   
	   String xpath = String.format("//a[@aria-label='%s']",date );
	   driver.findElement(By.xpath(xpath)).click();
	   
    }
   
   public void retdate(String date) {
	   retdatefield.click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-datepicker-div\"]")));
	   
	   String xpath = String.format("//a[@aria-label='%s']",date );
	   driver.findElement(By.xpath(xpath)).click();
   }
    // Method to interact with the Travelers field
    public void cabinclass(String cabin) {
//    	cabinclass.click();
    	 WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='cabin']"));
         Select dropdown = new Select(dropdownElement);
         dropdown.selectByVisibleText(cabin);
    }

    public void clickSearchButton() {
    
        Search.click();
    }
    
    
}

   
