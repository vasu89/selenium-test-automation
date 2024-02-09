package pageObjectModel;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTestForFormy {
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://formy-project.herokuapp.com/form");
		
		FormPage formPage = new FormPage();
		formPage.submitForm(driver);
	}
	
	@Test
	public void testConfirmation() {
		
		ConfirmationPage confirmPage = new ConfirmationPage();
		Assert.assertEquals(confirmPage.confirmed(driver), "The form was successfully submitted!");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
