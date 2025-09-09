package MINITEStCASES;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HARi {

    WebDriver driver;
    String mainWindow;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().window().maximize();
        driver.get("https://www.yatra.com/");
        Assert.assertTrue(driver.getTitle().contains("Yatra"), "Yatra homepage did not load correctly.");
    }

    @Test(priority = 1)
    public void closeLoginPopup() {
        WebElement closeloginwindow = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div[1]/div[2]/div/section[2]/span/img"));
        Assert.assertTrue(closeloginwindow.isDisplayed(), "Login popup close button not displayed.");
        closeloginwindow.click();
    }

    @Test(priority = 2)
    public void validateOffersPageTitleAndScreenshot() throws IOException {
        WebElement offer = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[1]/div/div[2]/div[2]/div"));
        Assert.assertTrue(offer.isDisplayed(), "Offers element not found.");
        offer.click();

        mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String child : windows) {
            if (!child.equals(mainWindow)) {
                driver.switchTo().window(child);
                String title = driver.getTitle();
                Assert.assertTrue(title.contains("Domestic Flights Offers"), "Offers page title mismatch.");

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileHandler.copy(screenshot, new File("Great_Offers_And_Amazing_Deals.png"));
                driver.close();
            }
        }

        driver.switchTo().window(mainWindow);
    }

    @Test(priority = 3)
    public void exploreHolidayPackages() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[1]/div/ul/li[4]/span[1]")).click();
        WebElement offers = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div[1]/div/div[2]/div[2]/div"));
        offers.click();

        Set<String> windows1 = driver.getWindowHandles();

        for (String child : windows1) {
            if (!child.equals(mainWindow)) {
                driver.switchTo().window(child);
                System.out.println("child: " + child);
                System.out.println(driver.getTitle());

                WebElement firstPackage = driver.findElement(By.xpath("//*[@id=\"collapsibleSection\"]/section[1]/div[2]/div/section/div/div/ul/li[1]/a/span/span/span[3]/span"));
                Assert.assertTrue(firstPackage.isDisplayed(), "First holiday package not found.");
                firstPackage.click();

                Set<String> windows2 = driver.getWindowHandles();

                for (String child01 : windows2) {
                    if (!child01.equals(child) && !child01.equals(mainWindow)) {
                        driver.switchTo().window(child01);
                        System.out.println("child1: " + child01);
                        System.out.println(driver.getTitle());

                        // Wait for page to load
                        Thread.sleep(3000);

                        List<WebElement> bookingTabs = driver.findElements(By.xpath("//*[@id=\"booking_engine_modues\"]/li[1]/span[2]"));
                        Assert.assertTrue(!bookingTabs.isEmpty(), "Booking tab element not found in DOM.");

                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                        wait.until(ExpectedConditions.visibilityOf(bookingTabs.get(0)));
                        Assert.assertTrue(bookingTabs.get(0).isDisplayed(), "Booking tab is not visible.");
                        bookingTabs.get(0).click();

                        List<WebElement> offerList = driver.findElements(By.xpath("//div[1]/div[7]/div[2]/section/div[2]/table/tbody/tr/td/a"));
                        List<WebElement> priceList = driver.findElements(By.xpath("//div[1]/div[7]/div[2]/section/div[2]/table/tbody/tr/td[2]/span"));

                        Assert.assertTrue(!offerList.isEmpty(), "No holiday packages found.");
                        Assert.assertTrue(!priceList.isEmpty(), "No prices found.");

                        System.out.println("Holiday Packages:");
                        for (WebElement offerName : offerList) {
                            System.out.println(offerName.getText());
                        }

                        System.out.println("Prices:");
                        for (WebElement price : priceList) {
                            System.out.println(price.getText());
                        }
                    }
                }
            }
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
