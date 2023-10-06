package utils;

import java.net.URL;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
	
		public static WebDriver driver;
		static ChromeOptions chromeOptions;
		static URL url;
			public static WebDriver getDriver() {
				String browser = System.getProperty("browser");
				if (browser == null) {
					browser = TestDataReader.getProperty("browser_type");
				}
				if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
					switch(browser) {
					case "chrome-headless" :
					    chromeOptions = new ChromeOptions();
						chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
						chromeOptions.addArguments("--headless","--disable-gpu", "--window-size=1920,1080");
						driver = new ChromeDriver(chromeOptions);
						break;
					case "chrome" :
						driver = new ChromeDriver();
						break;
					case "firefox" :
						driver = new FirefoxDriver();
						break;
					case "firefox-headless" :
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.addArguments("--headless");
						firefoxOptions.addArguments("--window-size=1920,1080");
						driver = new FirefoxDriver(firefoxOptions);
						break;
					case "safari" :
						driver = new SafariDriver();
						break;
					case "edge" :
						driver = new EdgeDriver();
						break;
						//sauselab
						
						
					default:
						ChromeOptions Options = new ChromeOptions();
						Options.addArguments("--headless");
						driver = new ChromeDriver(Options);
						break;
						
					}
				}
				return driver;
			}
			
			public static void quitDriver() {
				if (driver != null) {
					driver.quit();
					driver = null;
				}
		    }

		}
