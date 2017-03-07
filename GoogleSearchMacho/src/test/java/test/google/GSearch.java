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
   // If the browser is Firefox, then do this code
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
		System.out.println("First Git test push 2");
		//Enter login name
		//WebDriverWait wait = new WebDriverWait(driver, 10);	
		
		//WebElement user_nm = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		////WebElement user_nm = driver.findElement(By.id("username"));
		//user_nm.sendKeys(username_data);

		
	}

	
	@AfterClass public void afterTest(){
		driver.quit();
	}
	
	
}
