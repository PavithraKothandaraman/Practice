package MINITEStCASES;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class sanjitProject {
	WebDriver driver;

	@BeforeClass
    public void setup() throws Exception {

        DriverSetupsanjit cfg = new DriverSetupsanjit();
        driver = DriverSetupsanjit.getDriver();
        driver.manage().window().maximize();
        WebElement searchInput = driver.findElement(By.id("inputbar"));
        searchInput.sendKeys("Selenium Webdriver" + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
	}   
        
	@Test(priority = 1)
	public void testSortByPriceLowToHigh() {
	    // Wait until the dropdown is present
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement sortDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Sort")));

	    // Select the sorting option
	    Select sort = new Select(sortDropdown);
	    sort.selectByVisibleText("Price - Low to High");

	    // Wait until the page updates (e.g., wait for titles to be visible)
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".title")));

	    // Re-locate the dropdown to avoid stale reference
	    sortDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Sort")));
	    sort = new Select(sortDropdown);

	    // Assert the selected option
	    String selectedOption = sort.getFirstSelectedOption().getText();
	    Assert.assertEquals(selectedOption, "Price - Low to High", "Sorting option not correctly selected.");

	    
	}
    @Test(priority = 2)
    public void testPrintTopResults() {
        List<WebElement> titles = driver.findElements(By.cssSelector(".title"));
        List<WebElement> prices = driver.findElements(By.cssSelector(".sell"));

        // Assert that titles and prices are found
        Assert.assertTrue(titles.size() > 0, "No book titles found.");
        Assert.assertTrue(prices.size() > 0, "No book prices found.");

        System.out.println("Number of Titles: " + titles.size());
        System.out.println("Number of Prices: " + prices.size());

        System.out.println("\nFirst " + Math.min(10, titles.size()) + " Books Sorted by Price (Low to High):");

        for (int i = 0; i < Math.min(10, titles.size()); i++) {
            String title = titles.get(i).getText();
            String price = (i < prices.size()) ? prices.get(i).getText() : "Price not available";

            // Assert that title is not empty
            Assert.assertTrue(!title.trim().isEmpty(), "Title is empty at index " + i);

            // Optional: Assert that price is not empty
            Assert.assertTrue(!price.trim().isEmpty(), "Price is empty at index " + i);

            System.out.println((i + 1) + ". " + title + " - " + price);
        }
    }

    @AfterClass
    public void tearDown() {
    	driver.quit();
    	System.out.println("Browser Closed");
    		
    	}
    }
