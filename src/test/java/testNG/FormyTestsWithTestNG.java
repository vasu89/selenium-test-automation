package testNG;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FormyTestsWithTestNG {

	public FormyTestsWithTestNG() {
	}
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void enterText() {
		driver.get("https://formy-project.herokuapp.com/keypress");
		
		WebElement textBox = driver.findElement(By.id("name"));
		textBox.click();
		textBox.sendKeys("John Smith");
		
		WebElement btn = driver.findElement(By.id("button"));
		btn.click();
	}
	
	@Test
	public void scrollToEnd() {
		driver.get("https://formy-project.herokuapp.com/scroll");
		WebElement name = driver.findElement(By.id("name"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(name);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		name.sendKeys("John");
		
		WebElement date = driver.findElement(By.id("date"));
		date.sendKeys("01/01/2020");
		
	}
	
	@Test
	public void switchTab() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/switch-window");
		WebElement newTabBtn = driver.findElement(By.xpath("//button[contains(text(),'Open new tab')]"));
		newTabBtn.click();
		
		String orgHandle = driver.getWindowHandle();
		
		for(String handle: driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			Thread.sleep(1000);
		}
		
		driver.switchTo().window(orgHandle);
		Thread.sleep(1000);
	}
	@Test
	public void alertWindow() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/switch-window");
		
		WebElement alertBtn = driver.findElement(By.id("alert-button"));
		alertBtn.click();
		Thread.sleep(1000);
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
	}
	
	@Test
	public void javascriptModal() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/modal");
		
		WebElement modalBtn = driver.findElement(By.xpath("//button[contains(text(),'Open modal')]"));
		modalBtn.click();
		Thread.sleep(1000);
		
		Assert.assertEquals("Modal title", driver.findElement(By.id("exampleModalLabel")).getText());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebElement closeButton = driver.findElement(By.id("close-button"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", closeButton);
		Thread.sleep(1000);
	}
	
	@Test
	public void dragAndDrop(){
		driver.get("https://formy-project.herokuapp.com/dragdrop");
		WebElement img = driver.findElement(By.id("image"));
		WebElement box = driver.findElement(By.id("box"));
		Actions action = new Actions(driver);
		action.dragAndDrop(img, box).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean present = wait.until(ExpectedConditions.textToBePresentInElement(box,"Dropped"));
		
		Assert.assertEquals("Dropped!", box.getText());
		Assert.assertEquals(present, true);
	}
	@Test
	public void radioBtn() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/radiobutton");
		WebElement radio1 = driver.findElement(By.id("radio-button-1"));
		radio1.click();
		Thread.sleep(1000);
		WebElement radio2 = driver.findElement(By.xpath("//input[@value='option2']"));
		radio2.click();
		Thread.sleep(1000);
		WebElement radio3 = driver.findElement(By.xpath("//input[@value='option3']"));
		radio3.click();
		Thread.sleep(1000);
		
	}
	@Test
	public void checkbox() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/checkbox");
		WebElement chk1 = driver.findElement(By.id("checkbox-1"));
		chk1.click();
		Thread.sleep(1000);
	}
	@Test
	public void datepicker() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/datepicker");
		WebElement datepicker = driver.findElement(By.id("datepicker"));
		datepicker.sendKeys("01/14/2024");
		datepicker.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
	}
	@Test
	public void selectDropdown() {
		
	}
	@Test
	public void fileUpload() throws InterruptedException {
		driver.get("https://formy-project.herokuapp.com/fileupload");
		WebElement fileUpload = driver.findElement(By.id("file-upload-field"));
		fileUpload.sendKeys("fileToUpload.png");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Reset')]")));
				
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
