package Pageobject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Logout extends Basepage{
	
	
	public void logout(WebDriver driver)
	{
		driver.close();
		
		Set<String> windows = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(windows);
		    // Switch to the window
		    driver.switchTo().window(windowHandlesList.get(1));
		    driver.close();

		    // Perform necessary actions, e.g., close the window or find an element
		    try {
		    	driver.switchTo().window(windowHandlesList.get(0));
		    	WebElement topframe = driver.findElement(By.xpath("//frame[contains(@id,'banner')]"));
		    	driver.switchTo().frame(topframe);
		        driver.findElement(By.xpath("//a[@title='Sign off']")).click();
		       // driver.close();
		    } catch (NoSuchElementException e) {
		        // Handle exception if the element is not found in the window
		        System.out.println("Sign off element not found in window: ");
		    }
	}
}
