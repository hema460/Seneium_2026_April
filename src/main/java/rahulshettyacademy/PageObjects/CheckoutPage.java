package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
WebDriver driver;
	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> cartProducts=driver.findElements(By.cssSelector(".infoWrap h3"));
	//driver.findElement(By.cssSelector(".subtotal button")).click();

//Boolean flag=cartProducts.
	
	
	@FindBy (css=".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".subtotal button")
	WebElement checkOutBtn;

	public List<WebElement> getCartProduct() {
		return cartProducts;
	}
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean flag=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	 return flag;
	}
	public PaymentPage clickOnCheckOut() {
		checkOutBtn.click();
		
		return new PaymentPage(driver);
	}
}
