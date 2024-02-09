package pageObjectModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
	
	public void submitForm(WebDriver driver) throws InterruptedException {
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys("John");
		
		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys("Michael");
		
		WebElement jobTitle = driver.findElement(By.id("job-title"));
		jobTitle.sendKeys("SDET");
		
		WebElement education = driver.findElement(By.xpath("//input[@value='radio-button-2']"));
		education.click();
		
		WebElement gender = driver.findElement(By.xpath("//input[@value='checkbox-2']"));
		gender.click();
		
		WebElement drpdown = driver.findElement(By.id("select-menu"));
		Select select = new Select(drpdown); 
		select.selectByValue("3");
		Thread.sleep(1000);
		select.selectByVisibleText("10+");
		Thread.sleep(1000);
		select.selectByIndex(1);
		Thread.sleep(1000);
		
		WebElement datePicker = driver.findElement(By.id("datepicker"));
		datePicker.sendKeys("01/01/2010");
		datePicker.sendKeys(Keys.RETURN);
		
		Thread.sleep(1000);
		
		WebElement submit = driver.findElement(By.xpath("//a[contains(text(),'Submit')]"));
		submit.click();
	}

}
