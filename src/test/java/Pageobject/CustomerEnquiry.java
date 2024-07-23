package Pageobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

public class CustomerEnquiry extends Basepage {
	
	Loginpage lp=new Loginpage(driver);
	GenericMethod gm=new GenericMethod();
	Logout lg=new Logout();
	public void Enquiry() throws InterruptedException
	{
		lp.loginPage(driver);
		gm.menuframes(driver);
		driver.getWindowHandle();
		driver.findElement(By.xpath("//span[text()='Customer']")).click();
		driver.findElement(By.xpath("//li[2]//span[text()='Enquiries']")).click();
		driver.findElement(By.xpath("//a[text()='Customer Details ']")).click();
		Set<String> windows = driver.getWindowHandles();
		List<String> windows1=new ArrayList<>(windows);
		driver.switchTo().window(windows1.get(1));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"value:1:1:1\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"value:1:1:1\"]")).sendKeys("214051");
		driver.findElement(By.xpath("//*[@title=\"Run Selection\"]")).click();
		driver.findElement(By.xpath("//img[@title='Single Customer View']")).click();
		lg.logout(driver);
		
	}

}
