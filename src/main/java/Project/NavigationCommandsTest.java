package Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.Assert;

public class NavigationCommandsTest {

    WebDriver driver;
    Properties p = new Properties();
    String originalTab;

    @BeforeClass
    public void setup() throws Exception {
        FileInputStream f = new FileInputStream(".//src//main//resources//config.properties");
        p.load(f);
        DriverSetup1 cfg = new DriverSetup1();
        driver = DriverSetup1.getDriver();
        driver.manage().window().maximize();
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("Orange HRM demo");
        search.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    
    
    
    
    @Test(priority = 1)
    public void testNavigateToOrangeHRM() {
        driver.get("https://www.orangehrm.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm"), "Failed to navigate to OrangeHRM");
    }

    @Test(priority = 2)
    public void testBackNavigationToGoogle() {
        driver.navigate().to("https://google.com");
        Assert.assertTrue(driver.getTitle().contains("Google"), "Failed to navigate to Google");
    }

    @Test(priority = 3)
    public void testForwardNavigationToOrangeHRM() {
        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm"), "Failed to navigate back to OrangeHRM");
    }

    @Test(priority = 4)
    public void testContactSalesFormFill() throws InterruptedException, IOException {
        driver.findElement(By.linkText("Contact Sales")).click();

        driver.findElement(By.name("FullName")).sendKeys(p.getProperty("name"));
        driver.findElement(By.name("Contact")).sendKeys(p.getProperty("contact"));
        driver.findElement(By.name("Email")).sendKeys(p.getProperty("email"));

        new Select(driver.findElement(By.name("Country"))).selectByVisibleText(p.getProperty("country"));
        new Select(driver.findElement(By.name("NoOfEmployees"))).selectByVisibleText(p.getProperty("employee"));
        driver.findElement(By.name("JobTitle")).sendKeys(p.getProperty("jobTitle"));

        System.out.println("Please complete CAPTCHA manually within 30 seconds...");
        Thread.sleep(30000);

        driver.findElement(By.name("action_submitForm")).click();
        Thread.sleep(4000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("screenshot.png"));
        FileHandler.copy(screenshot, new File("C:\\Users\\2425270\\Downloads\\screenshot.png"));

        Assert.assertTrue(new File("screenshot.png").exists(), "Screenshot not saved");
    }

    @Test(priority = 5)
    public void testMessageSubmission() throws InterruptedException {
        WebElement messageBox = driver.findElement(By.id("Form_getForm_Comment"));
        messageBox.sendKeys("We are looking for an HRMS solution to manage employee records and leave tracking.");
        WebElement submitButton = driver.findElement(By.name("action_submitForm"));
        submitButton.click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("Thank you") || driver.getCurrentUrl().contains("orangehrm"), "Message submission failed");
    }

    @Test(priority = 6)
    public void testTabSwitching() {
        originalTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://example.com");
        driver.close();
        driver.switchTo().window(originalTab);
        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm"), "Failed to return to original tab");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser Closed");
    }
}
