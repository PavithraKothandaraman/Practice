package annotations_grouping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class hardvsSoft {
  @Test
  void testLoginpage(){
	  WebDriver driver=new ChromeDriver();
	  driver.get("https://www.makemytrip.com/");
	  String title=driver.getTitle();
	  Assert.assertEquals(title,"makeTrip");
	  System.out.println(title);
	  
  }
  @Test(priority=1)
  void testAnotherPage() {
	  SoftAssert sa=new SoftAssert();
	  sa.assertEquals(100,120);
	  System.out.println("succesfull");
  sa.assertAll();
  }
   
	
	
	

}
