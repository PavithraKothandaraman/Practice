package MINITEStCASES;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.List;
 
public class SearchResultsPage {

    private WebDriver driver;

;
 
    public SearchResultsPage(WebDriver driver) {

        this.driver = driver;

    }
 
    public void waitForResults() {

//    	explicit wait----condition 

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".title")));

    }
 
    public void sortByPriceLowToHigh() {

        Select sort = new Select(driver.findElement(By.name("Sort")));

        sort.selectByVisibleText("Price - Low to High");

        waitForResults(); // Wait for the page to refresh with sorted results

    }
 
    public void printTopResults(int count) {

        List<WebElement> titles = driver.findElements(By.cssSelector(".title"));

        List<WebElement> prices = driver.findElements(By.cssSelector(".sell"));

        System.out.println("\n First " + count + " Books Sorted by Price (Low to High):");

        for (int i = 0; i < Math.min(count, titles.size()); i++) {

            String title = titles.get(i+1).getText();

            String price = (i < prices.size()) ? prices.get(i).getText() : "Price not available";

            System.out.println((i + 1) + ". " + title + " - " + price);

        }

    }

}
 
