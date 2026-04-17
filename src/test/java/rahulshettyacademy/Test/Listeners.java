package rahulshettyacademy.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports extent=ExtentReportDemo.config();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTestThread=new ThreadLocal<ExtentTest>();
	public Listeners() throws IOException {
		super();
		
	}
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTestThread.set(test);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "test is successfully passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		extentTestThread.get().fail(result.getThrowable());
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver = (WebDriver) result.getTestClass().getRealClass()
			        .getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			String path = null;
			try {
				path = getScreenShot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		extentTestThread.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	  }
	
	

	
}
