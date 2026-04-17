package rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import rahulshettyacademy.AbstractComponent.AbstractComponent;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.OrderConfirmationPage;
import rahulshettyacademy.PageObjects.PaymentPage;
import rahulshettyacademy.PageObjects.ProductPage;
import rahulshettyacademy.Test.BaseTest;
import rahulshettyacademy.Test.Retry;


public class StandAloneTest extends BaseTest {
	
	ProductPage productPage;

	public StandAloneTest() throws IOException {
		super();
	}
	@Test
	public void verifyLogin() {
		productPage=landingPage.loginApplication(prop.getProperty("userEmail"),prop.getProperty("password"));
		Boolean loginCheck=productPage.verifyLoginSuccessfull();
		Assert.assertTrue(loginCheck);
	}
	
		@Test(retryAnalyzer=Retry.class)
	public void login() throws InterruptedException {
		String productName="ZARA COAT 3";
		 productPage=landingPage.loginApplication(prop.getProperty("userEmail"),prop.getProperty("password"));
		List<WebElement>products=productPage.getProductList();
		productPage.getProductByName(productName);
		productPage.addProductToCart(productName);
		WebElement successmessage=productPage.verifySuccessMessage();
		Assert.assertTrue(successmessage.isDisplayed());
		Thread.sleep(3000);
		CheckoutPage checkoutPage=productPage.clickOnCart();
		List<WebElement> cartProducts=checkoutPage.getCartProduct();
		Boolean match=checkoutPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		PaymentPage paymentPage=checkoutPage.clickOnCheckOut();
		paymentPage.clickOnCountry("India");

		OrderConfirmationPage orderConfirmationPage=paymentPage.clickOnPlaceOrderBtn();

		String orderPlacedmessage=orderConfirmationPage.verifyOrderConfirmationMessage();
		Assert.assertEquals(orderPlacedmessage,"THANKYOU FOR THE ORDER.");

		//	driver.close();

	}
		

}
