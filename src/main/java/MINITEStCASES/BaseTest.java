package MINITEStCASES;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
 
//public class BaseTest {
////    @BeforeClass
////        WebDriver driver;
//        
// 
//        try {
//            driver.get("https://www.bookswagon.com");
//            // Create Page Objects
//            HomePage homePage = new HomePage(driver);
//
//            // Perform actions using Page Objects
//            homePage.searchFor("Selenium Webdriver");
//            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
//            searchResultsPage.waitForResults();
//            searchResultsPage.sortByPriceLowToHigh();
//            searchResultsPage.printTopResults(10);
// 
//        } catch (Exception e) {
//            System.out.println("Error occurred: " + e.getMessage());
//        } 
//        
//        finally {
//            driver.quit();
//        }
//    }
//}