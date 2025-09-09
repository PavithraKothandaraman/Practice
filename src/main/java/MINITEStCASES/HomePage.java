package MINITEStCASES;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class HomePage {
    private WebDriver driver;
    private By searchBox = By.id("inputbar");
 
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }	

 
    public void searchFor(String bookName) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.sendKeys(bookName + Keys.ENTER);
    }
}