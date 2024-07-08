package page.objects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.utils.SeleniumWrappers;

public class ProductPage extends SeleniumWrappers{

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button.single_add_to_cart_button")
	public WebElement addToCartButton;
	
	@FindBy(css="div.woocommerce-message")
	public WebElement addedToCartMessage;
	
	@FindBy(xpath="//a[@class=\"button wc-forward\"][@tabindex=\"1\"]")
	public WebElement viewCartButton;
	
	@FindBy(xpath="//i[@class=\"klbth-icon-plus\"]")
	public WebElement updateQtyButton;
	
	@FindBy(xpath="//a[@class=\"checkout-button button alt wc-forward\"]")
	public WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//input[@id=\"terms\"]")
	public WebElement termsCheckbox;
	
	@FindBy(xpath="//button[@id=\"place_order\"]")
	public WebElement placeOrderButton;
	
	@FindBy(xpath="//p[@class=\"woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received\"]")
	public WebElement orderSuccessMessage;
	
	@FindBy(xpath="//ul/li[@class='woocommerce-order-overview__order order']/strong")
	public WebElement orderNumber;
	
	@FindBy(xpath="//span[@class=\"woocommerce-Price-amount amount\"]")
	public List<WebElement> price;

	


}
