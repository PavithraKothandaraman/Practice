package annotations_grouping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Index_page{
	WebDriver driver;
	public Index_page(WebDriver driver) {
		this.driver=driver;                                    
	}
	
	@Test(priority=1,groups="regression")
	void chooseAdmin() {
		driver.findElement(By.xpath("//a[text()='Admin']")).click();
		
	}

}
