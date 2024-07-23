package Pageobject;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CIFverification extends Basepage {

	public CIFverification (WebDriver driver) {
		this.driver=driver; 
	}
	
	GenericMethod gm=new GenericMethod();
	IndividualCIF cf= new IndividualCIF(driver);
	
	public void verification() throws InterruptedException, IOException
	{
		String reference = cf.CIFcreation();	
		
		//login 
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get("http://192.168.2.9:9089/BrowserWeb/servlet/BrowserServlet");
			driver.manage().window().maximize();
			Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='signOnName']")).sendKeys("FTPUSER04");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");	  
		 Thread.sleep(3000);
		 WebElement submit = driver.findElement(By.xpath("//input[@id='sign-in']")); 
		 submit.click();
		 
		 
		//Switching frames :		
		gm.menuframes(driver);
		driver.findElement(By.xpath("//span[text()='User Menu']")).click();
		String originalWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("(//span[text()='Customer'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Authorise/Delete Customer ']")).click();
		Set<String> windows = driver.getWindowHandles();
		for(String CIFwindow :windows )
		{
			if(!CIFwindow.equals(originalWindow))
			{
				driver.switchTo().window(CIFwindow);
				}
		}
		
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='Selection Screen']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[starts-with(@id,'selectiondisplay')]/tbody/tr[3]/td[3]/input[starts-with(@id,'value')]")).sendKeys(reference);
		driver.findElement(By.xpath("//a[@alt='Run Selection']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Authorise']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Authorises a deal']")).click();
		driver.close();
		Thread.sleep(3000);
		driver.switchTo().window(originalWindow);
		WebElement firstframe=driver.findElement(By.xpath("//frame[contains(@id,'banner')]"));
		driver.switchTo().frame(firstframe);
		driver.findElement(By.xpath("//a[@title='Sign off']")).click();
		driver.close();
	}
	
	
        
       	public void logout() {
		driver.findElement(By.xpath("//a[@title='Sign off']")).click();
		}
}
