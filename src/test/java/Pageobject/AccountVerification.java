package Pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Test.Baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountVerification extends Basepage  {
	
	
	Loginpage lp=new Loginpage(driver);
	GenericMethod GM=new GenericMethod();
	public void Accountverification(WebDriver driver, String Arrangement) throws InterruptedException
	
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://192.168.2.9:9089/BrowserWeb/servlet/BrowserServlet");
		driver.manage().window().maximize();
		lp.loginPage2(driver);
		GM.menuframes(driver);
		driver.findElement(By.xpath("//span[text()='User Menu']")).click();
		String originalWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[text()='Retail Operations']")).click();
		driver.findElement(By.xpath("//a[text()='Find Account ']")).click();
		Set<String> findaccountwindow = driver.getWindowHandles();
		for(String newwindow:findaccountwindow)
		{
			if(!newwindow.equals(originalWindow))
				driver.switchTo().window(newwindow);
		}
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[text()='Unauthorised']")).click();
		String authorizationsearchwindow=driver.getWindowHandle();
		WebElement textbox = driver.findElement(By.xpath("//table[starts-with(@id,'selectiondisplay_workarea')]//tr//td[3]//input[@type='text'][1]"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(textbox));
		textbox.clear();
		textbox.sendKeys(Arrangement);
		driver.findElement(By.xpath("//a[@title='Run Selection']")).click();
		driver.findElement(By.xpath("//a[@title='Overview']")).click();
		Set<String> unauthaccountwindow = driver.getWindowHandles();
		for(String newwindow1:unauthaccountwindow)
		{
			if(!newwindow1.equals(originalWindow))
				if(!newwindow1.equals(authorizationsearchwindow))
				driver.switchTo().window(newwindow1);
		}
		driver.manage().window().maximize();
		String unauthorizedwindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[@title='Select Drilldown']")).click();
		Set<String> newwindow2=driver.getWindowHandles();
		List<String> windows=new ArrayList<>(newwindow2);//casting
		driver.switchTo().window(windows.get(3));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//img[@alt='Authorises a deal']")).click();
		String message = driver.findElement(By.xpath("//td[@class='message']")).getText();
		Assert.assertEquals(driver.findElements(By.xpath("//td[@class='message']")), message);
	
		
		
		
		
	}

}
