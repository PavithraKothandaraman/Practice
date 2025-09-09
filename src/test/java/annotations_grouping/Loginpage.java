package annotations_grouping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Loginpage {
	WebDriver driver;
	
     @Test(priority=1,groups="sanity")
     void testLoginHrm() {
    	 
    	 driver=new ChromeDriver();
    	 driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	 String title=driver.getTitle();
    	 Assert.assertEquals(title,"OrangeHRM");
    	 
     }
     @Test(priority=2,groups="sanity")
     void testEnteringdetails() throws InterruptedException {
    	 Thread.sleep(3000);
    	 WebElement e= driver.findElement(By.name("username"));
    	 e.sendKeys("Admin");
    	 driver.findElement(By.name("password")).sendKeys("admin123");
    	 driver.findElement(By.xpath("//button[text()=\" Login \"]")).click()
;    	 String url=driver.getCurrentUrl();
         Assert.assertEquals(url, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    	 
     }
     
     
     
     
}
