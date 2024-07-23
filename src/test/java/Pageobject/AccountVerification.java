package Pageobject;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		driver.findElement(By.xpath("//a[text()='Find Account ']")).click();
		Set<String> findaccountwindow = driver.getWindowHandles();
		for(String newwindow:findaccountwindow)
		{
			if(!newwindow.equals(originalWindow))
				driver.switchTo().window(newwindow);
		}
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[text()='Unauthorised']")).click();		
		driver.findElement(By.xpath("//table[starts-with(@id,'selectiondisplay_workarea')]//tr[2]//td[3]")).sendKeys(Arrangement);
		driver.findElement(By.xpath("//a[@title='Overview']")).click();
		Set<String> unauthaccountwindow = driver.getWindowHandles();
		for(String newwindow1:unauthaccountwindow)
		{
			if(!newwindow1.equals(originalWindow))
				driver.switchTo().window(newwindow1);
		}
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@title='Select Drilldown']")).click();
		
		
		
	}

}
