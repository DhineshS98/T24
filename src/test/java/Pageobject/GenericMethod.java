package Pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericMethod {
	
	FileInputStream fis;
	Properties p;
	
	public void menuframes(WebDriver driver)
	{
		WebElement menuframe=driver.findElement(By.xpath("//frame[contains(@id,'menu')]"));
		if(menuframe.isDisplayed()) {
			driver.switchTo().frame(menuframe);
			
		}
	}
	
	public String datas(WebDriver driver) throws IOException
	{
		 fis=new FileInputStream("C:\\Users\\Yethi\\eclipse-workspace\\T24\\src\\main\\resources\\Data");
		p.load(fis);
		String firstname=p.getProperty("GivenName");
		String FullName=p.getProperty("GBfullName");
		String sector=p.getProperty("Sector");
		String Mnemonic=p.getProperty("Mnemonic");
		return Mnemonic;		
		
		
	}
	


}
