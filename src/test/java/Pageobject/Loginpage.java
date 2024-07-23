package Pageobject;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Test.Baseclass;

public class Loginpage extends Basepage {
	
	 WebDriver driver;
	
	 public void loginPage() throws InterruptedException {
		
		 driver.findElement(By.xpath("//input[@id='signOnName']")).sendKeys("ROOUSER01");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");	  
		 Thread.sleep(3000);
		 WebElement submit = driver.findElement(By.xpath("//input[@id='sign-in']")); 
		 submit.click();

}
	 public void loginPage2(WebDriver driver) throws InterruptedException {
			
		 driver.findElement(By.xpath("//input[@id='signOnName']")).sendKeys("FTPUSER04");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");	  
		 Thread.sleep(3000);
		 WebElement submit = driver.findElement(By.xpath("//input[@id='sign-in']")); 
		 submit.click();

}
	
	public Loginpage(WebDriver driver) {
		this.driver=driver; 
	}


}
