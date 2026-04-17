package rahulshettyacademy.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;
import rahulshettyacademy.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	public LandingPage landingPage;
	public BaseTest() throws IOException {
		FileInputStream fis=new FileInputStream(".//src/test/resources/config.properties");
		// OR FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop=new Properties();
		
			prop.load(fis);
			fis.close();
		
	}
	
	public WebDriver intializeDriver() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--start-maximized");
		
		String browserName = System.getProperty("browserName") != null 
                ? System.getProperty("browserName") 
                : prop.getProperty("browserName");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	
	@BeforeMethod
	public LandingPage launchApplication() {
		driver=intializeDriver();
		 landingPage=new LandingPage(driver);
		
		return landingPage;
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
	public String getScreenShot(String testcaseName,WebDriver driver ) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File file=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//Screenshots//"+testcaseName+".png"));
		return System.getProperty("user.dir")+"//Screenshots//"+testcaseName+".png";
	}

}
