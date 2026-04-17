package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	
WebDriver driver;
	
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy (css=".user__address div.form-group input")
	WebElement selectCountryInputBox;
	
	@FindBy (css=".ta-results button:last-of-type")
	WebElement selectCountryDropDown;

	@FindBy (css=".action__submit")
	WebElement placeOrderBtn;
	
	public void clickOnCountry(String country) {
		selectCountryInputBox.sendKeys(country);
		selectCountryDropDown.click();
	}
	
	
	public OrderConfirmationPage clickOnPlaceOrderBtn() {
		placeOrderBtn.click();
		return new OrderConfirmationPage(driver);
	}
}
