package MINITEStCASES;

import java.io.FileInputStream;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup1 {
	
	public static String url;
	public static String browser;
	public DriverSetup1() throws Exception{
	//Fetches the properties file to get the browser type and the website url.
		Properties  p=new Properties();
		FileInputStream f= new FileInputStream("C:\\Users\\2425270\\eclipse-workspace\\seleniumWebdriver\\src\\main\\resources\\config.properties");
		p.load(f);
		browser=p.getProperty("browser");
		url=p.getProperty("url");
		
	}
	public static WebDriver getDriver() {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriver driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
		}
		return null;

}
}
