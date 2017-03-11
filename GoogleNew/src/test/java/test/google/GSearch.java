package test.google;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GSearch {

	public WebDriver driver;
	@Parameters({"browser","appurl"})	
	
	@BeforeClass
   // Passing Browser parameter from TestNG xml
   public void beforeTest(String browser, String appurl) {
   // If the browser is Firefox, then do this
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
	  // If browser is chrome, then do this	  
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  // Here I am setting up the path for my ChromeDriver
		  System.setProperty("webdriver.chrome.driver", "C:\\Tojo\\chromedriver.exe");
		  driver = new ChromeDriver();
	  } 
	//Open the application
	  driver.get(appurl);
	  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@Parameters({"search_data"})	
	@Test (priority = 1)
	public void GoogleSearch(String search_data) throws InterruptedException
	{
		System.out.println("Search Data : "+search_data);
		//Enter Search Data
		WebDriverWait wait = new WebDriverWait(driver, 10);	
		WebElement search_item = wait.until(ExpectedConditions.elementToBeClickable(By.id("lst-ib")));
		//WebElement search_item = driver.findElement(By.id("username"));
		search_item.sendKeys(search_data);

		//Click on search button
		WebElement srch_button = driver.findElement(By.id("_fZl"));
		srch_button.click();
		
		
		//Click on TestNG _ WElcome Link
		WebElement serched_item_link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("TestNG - Welcome")));
		serched_item_link.click();
	}
	@Test (priority = 2)
	public void VerifyResult()throws InterruptedException
	{
		//Verify the an item in the loaded page
		WebElement item_to_verify = driver.findElement(By.linkText("Welcome"));
		Assert.assertEquals("Welcome", item_to_verify.getText());		
	}

	@AfterClass public void afterTest(){
		WebDriverWait wait = new WebDriverWait(driver, 10);	
		driver.quit();
	}
	
	
}
