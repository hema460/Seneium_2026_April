package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent{
	WebDriver driver;
	
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	

	
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	By addTocart= By.cssSelector(".card-body button:last-of-type");
	@FindBy (css="#toast-container")
	WebElement successMessage;
	@FindBy (css="button[routerlink*='cart']")
	WebElement cart;
	@FindBy (xpath="//ul/li[1]//button[normalize-space(.='HOME')]")
	WebElement homeHeader;
	//
	
	
	public Boolean verifyLoginSuccessfull() {
		Boolean header=homeHeader.isDisplayed();
		return header;
		
	}
	public List<WebElement> getProductList() {
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement product=getProductList().stream().filter(f->f.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		
	 return product;
	}
	
	public void addProductToCart(String productName) {
		WebElement product=getProductByName(productName);
	product.findElement(addTocart).click();
		
	}

public WebElement verifySuccessMessage() {
	WebElement successmessage=successMessage;
	waitForElementToBeVisible(successMessage);
	
	return successmessage;
	}

public CheckoutPage clickOnCart() {
	waitForElementToBeVisible(cart);
	cart.click();
	return new CheckoutPage(driver);
}
}
