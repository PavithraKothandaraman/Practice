package MINITEStCASES;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Automation {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        new DriverSetup(); // Load config
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));// explicit wait
    }

    @Test(priority = 1)
    public void openSignupForm() {
        driver.findElement(By.linkText("Create new account")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));
        Assert.assertTrue(driver.findElement(By.name("firstname")).isDisplayed(), "Signup form did not open.");
    }

    @Test(priority = 2)
    public void fillBasicDetails() {
        driver.findElement(By.name("firstname")).sendKeys("Hari");
        driver.findElement(By.name("lastname")).sendKeys("Khatri");
        driver.findElement(By.name("reg_email__")).sendKeys("987654321");

        Assert.assertEquals(driver.findElement(By.name("firstname")).getAttribute("value"), "Hari", "First name not entered correctly.");
        Assert.assertEquals(driver.findElement(By.name("lastname")).getAttribute("value"), "Khatri", "Last name not entered correctly.");
    }

    @Test(priority = 3)
    public void selectDOBAndGender() {
        new Select(driver.findElement(By.id("day"))).selectByVisibleText("9");
        new Select(driver.findElement(By.id("month"))).selectByIndex(7);
        new Select(driver.findElement(By.id("year"))).selectByValue("1999");

        driver.findElement(By.xpath("//*[@name='sex' and @value='2']")).click();

        Assert.assertEquals(new Select(driver.findElement(By.id("day"))).getFirstSelectedOption().getText(), "9", "Day not selected correctly.");
        Assert.assertTrue(driver.findElement(By.xpath("//*[@name='sex' and @value='2']")).isSelected(), "Gender not selected.");
    }

    @Test(priority = 4)
    public void validateErrorMessages() throws InterruptedException {
        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(2000);

        driver.findElement(By.name("reg_passwd__")).click();
        String passwordError = driver.findElement(By.xpath("(//div[@class='_5633 _5634 _53ij'])[1]")).getText();
        System.out.println("Password Error Message: " + passwordError);
        Assert.assertFalse(passwordError.isEmpty(), "Password error message is missing");

        driver.findElement(By.name("reg_email__")).click();
        String emailError = driver.findElement(By.xpath("(//div[@class='_5633 _5634 _53ij'])[2]")).getText();
        Assert.assertTrue(emailError.contains("mobile number"), "Mobile/email error not shown");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
