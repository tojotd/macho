package test.enroll;

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

public class Enroll_rule {

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
	
	@Parameters({"username_data","password_data"})	
	@Test (priority = 1)
	public void LoginEnrollment(String username_data, String password_data ) throws InterruptedException
	{
		System.out.println("Just to make sure i am in Test");
		//Enter login name
		WebDriverWait wait = new WebDriverWait(driver, 10);		 
		WebElement user_nm = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		//WebElement user_nm = driver.findElement(By.id("username"));
		user_nm.sendKeys(username_data);
		//Enter Password
		WebElement pswd = driver.findElement(By.id("password"));
		pswd.sendKeys(password_data);
		//Click on Login Button
		WebElement sign_in = driver.findElement(By.id("validateUser"));
		sign_in.click();
				
		System.out.println("User Name : "+ user_nm.getText()+"  Password : "+pswd.getText());
		System.out.println("Parameter User Name : "+ username_data+" Parameter Password : "+password_data);
		
	}

	@Parameters("Cli_Num")
	@Test (priority = 2) public void NavigateToRule(String Cli_Num)throws InterruptedException{
		
		//The following 2 lines are to make sure the page loads fully		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("clientVal")));

		//Assert statement to make sure the Validate button is enabled
		String text1 = driver.findElement(By.id("validateClientBtn")).getText();
		Assert.assertTrue(text1.contains("Validate"), "Validate");	

		//Enter Client number to search 
		WebElement cli_no = driver.findElement(By.id("clientVal"));
		cli_no.sendKeys(Cli_Num);
		//Click on Validate
		WebElement validate = driver.findElement(By.id("validateClientBtn"));
		validate.click();
	}	
	
	@Parameters("rule_name")
	@Test (priority = 3) public void ValidateRule(String rule_name)throws InterruptedException{
		
		System.out.println("I am in the Rule Validation from Webtable area - need to complete this section later");
		System.out.println("Rule Name : "+rule_name);
		//Split this test into 2 , one for searching, another for Validating
		//Write code to verify an expected Rule is available in the displayed Web table.
		//Write another test for logout.
		
		//Then think about GIthub and execution from Jenkins.
		
		//Options to validate the data from DB - can explore later.
	}
	@Test (priority = 4) public void LogoutEnrollment()throws InterruptedException{
		
		WebElement drop_dwn = driver.findElement(By.className("dropdown-toggle"));
		drop_dwn.click();
		WebElement log_out = driver.findElement(By.linkText("Log out"));
		log_out.click();
	}
	
	@AfterClass public void afterTest(){
		driver.quit();
	}
	
	
}
