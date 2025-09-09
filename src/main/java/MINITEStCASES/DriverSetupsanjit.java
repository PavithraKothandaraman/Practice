package MINITEStCASES;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetupsanjit {

    public static String url;
    public static String browser;

    public DriverSetupsanjit() throws Exception {
        Properties p = new Properties();
        FileInputStream f = new FileInputStream("src/main/resources/config.properties");
        p.load(f);
        browser = p.getProperty("browser");
        url = p.getProperty("url");
    }

    public static WebDriver getDriver() {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        if (driver != null) {
            driver.manage().window().maximize();
            driver.get(url);
        }
        return driver;
    }
}
