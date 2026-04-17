package rahulshettyacademy.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo{
	 
	
	public static ExtentReports config() {
	String path=System.getProperty("user.dir")+".//Screenshots/index.html";
	
	ExtentSparkReporter reporter= new ExtentSparkReporter(path);
	reporter.config().setReportName("Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Hema");
	return extent;
	
	}
}
