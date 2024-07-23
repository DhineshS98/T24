package Pageobject;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountCreation extends Basepage {
	
	public AccountCreation()
	{
		this.driver=driver;
	}
	GenericMethod gm=new GenericMethod();
	Logout lg=new Logout();
	public static String Arrangment = null;
	AccountVerification AV=new AccountVerification();
	
	
	public void accountcreation() throws InterruptedException
	{
		
		// for logging in :
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://192.168.2.9:9089/BrowserWeb/servlet/BrowserServlet");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='signOnName']")).sendKeys("ROOUSER01");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@123");
		Thread.sleep(3000);
		WebElement submit = driver.findElement(By.xpath("//input[@id='sign-in']"));
		submit.click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedCondition(By.xpath("//span[text()='User Menu']")));
		gm.menuframes(driver);
		driver.findElement(By.xpath("//span[text()='User Menu']")).click();
		String originalWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Product Catalog ']")).click();
		Set<String> windows = driver.getWindowHandles();
		for (String newwindow : windows) {
			if (!newwindow.equals(originalWindow)) {
				driver.switchTo().window(newwindow);
			}
		}
		driver.manage().window().maximize();
		String pcwindow=driver.getWindowHandle();
		WebElement productframe=driver.findElement(By.xpath("(//frame[contains(@id,ProductGroups)])[3]"));
		driver.switchTo().frame(productframe);
		driver.findElement(By.xpath("//*[@id='r7']/td/a/img")).click();
		driver.findElement(By.xpath("//*[@id='r3']/td[4]")).click();
		driver.switchTo().parentFrame();
		WebElement currentaccountframe=driver.findElement(By.xpath("(//frame[contains(@id,ProductGroups)])[5]"));
		driver.switchTo().frame(currentaccountframe);
		driver.findElement(By.xpath("(//img[@title='New Arrangement'])[1]")).click();
		Set<String> currentaccount = driver.getWindowHandles();
		for (String CIFwindow1 : currentaccount) {
			if (!CIFwindow1.equals(pcwindow)) {
				driver.switchTo().window(CIFwindow1);
			}
		}
		
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[contains(@id,'fieldName:CUSTOMER')]")).sendKeys("221459");
		driver.findElement(By.xpath("//input[contains(@id,'fieldName:CURRENCY')]")).sendKeys("USD");
		driver.findElement(By.xpath("//img[@alt='Validate a deal']")).click();
		String Arrangment=driver.findElement(By.id("disabled_ARRANGEMENT")).getText();
		driver.findElement(By.xpath("//img[@alt='Commit the deal']")).click();
		System.out.println("New Arrangement"+"  :"+Arrangment);
		WebElement Accept=driver.findElement(By.xpath("//a[text()='Accept Overrides']"));
	    if(Accept.isDisplayed()==true)
	    	Accept.click();
	    WebElement dropdown=driver.findElement(By.xpath("//select[contains(@name,'warningChooser:Have you')]"));
	    Select s=new Select(dropdown);
	    s.selectByValue("RECEIVED");
	    driver.findElement(By.xpath("//img[@alt='Commit the deal']")).click();
	    
	    lg.logout(driver);
	     //verification
	    AV.Accountverification(driver, Arrangment);
	    
	    
	    
	
	}
}