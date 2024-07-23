package Pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.Baseclass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IndividualCIF extends Basepage {
	
	public IndividualCIF(WebDriver driver) {
		this.driver=driver; 
	}
	Baseclass bc=new Baseclass();
	GenericMethod gm=new GenericMethod();
	FileInputStream fis;
	Properties p;
	public static String txnNumber=null;
	Matcher matcher;
	private static String referenceValue;
	
	
	public String CIFcreation() throws IOException, InterruptedException
	
	{   
		
		//for logging in :
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://192.168.2.9:9089/BrowserWeb/servlet/BrowserServlet");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='signOnName']")).sendKeys("ROOUSER01");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");	  
		 Thread.sleep(3000);
		 WebElement submit = driver.findElement(By.xpath("//input[@id='sign-in']")); 
		 submit.click();
		 
		 
		 //Switching frames :
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedCondition(By.xpath("//span[text()='User Menu']")));
		gm.menuframes(driver);
		driver.findElement(By.xpath("//span[text()='User Menu']")).click();// to be written in generic method.
		driver.findElement(By.xpath("(//span[text()='Customer'])[1]")).click();
		String originalWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Individual Customer')]")).click();
		Set<String> windows = driver.getWindowHandles();
		for(String CIFwindow :windows )
		{
			if(!CIFwindow.equals(originalWindow))
			{
				driver.switchTo().window(CIFwindow);
				}
		}
		driver.manage().window().maximize();
		
		//Entering CIF creating datas.
		
		WebElement title = driver.findElement(By.id("fieldName:TITLE"));
		Select s= new Select(title);
		s.selectByValue("MR");
		//driver.findElement(By.xpath("//option[@value='MR']"));
		fis=new FileInputStream("C:\\Users\\Yethi\\eclipse-workspace\\T24\\src\\main\\resources\\Data");
		p=new Properties();
		p.load(fis);
		
		driver.findElement(By.id("fieldName:GIVEN.NAMES")).sendKeys(p.getProperty("GivenName"));
		driver.findElement(By.id("fieldName:NAME.1:1")).sendKeys(p.getProperty("GBFullName"));
		driver.findElement(By.id("fieldName:SHORT.NAME:1")).sendKeys(p.getProperty("GBShortName"));
		driver.findElement(By.xpath("//input[@value='MALE']")).click();
		driver.findElement(By.id("fieldName:MNEMONIC")).sendKeys(p.getProperty("Mnemonic"));
		driver.findElement(By.id("fieldName:SECTOR")).sendKeys(p.getProperty("Sector"));
		driver.findElement(By.xpath("//img[@title='Validate a deal']")).click();
		WebElement commit = driver.findElement(By.xpath("//img[@title='Commit the deal']"));
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(5));
		waits.until(ExpectedConditions.visibilityOf(commit));
		driver.findElement(By.xpath("//img[@title='Commit the deal']")).click();
		String custID=driver.findElement(By.xpath("//td[@class='message']")).getText();
		Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
		matcher = pattern.matcher(custID);
		if (matcher.find()) {
            txnNumber = matcher.group();
            System.out.println("Transaction number: " + txnNumber);
        } else {
            System.out.println("Transaction number not generated.");
        }
		
		driver.close();
		Thread.sleep(3000);
		driver.switchTo().window(originalWindow);
		WebElement firstframe=driver.findElement(By.xpath("//frame[contains(@id,'banner')]"));
		driver.switchTo().frame(firstframe);
		driver.findElement(By.xpath("//a[@title='Sign off']")).click();
		driver.close();
		return txnNumber;
		

		
	}
	
	
	/*public void closeDriver() {
        driver.close();
        }
	public void logout() {
		driver.findElement(By.xpath("//a[@title='Sign off']")).click();
	}
	*/
}
