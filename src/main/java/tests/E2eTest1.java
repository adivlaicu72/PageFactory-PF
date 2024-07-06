package tests;



import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import framework.utils.PropertiesFileProcessor;
import page.objects.MenuPage;
import page.objects.MyAccountPage;
import page.objects.ProductPage;
import selenium.utils.BaseTest;

public class E2eTest1 extends BaseTest{

	String USER = PropertiesFileProcessor.readPropertiesFile("user", "credential.properties");
	String PASS = PropertiesFileProcessor.readPropertiesFile("pass", "credential.properties");


	@Test
	public void e2eTest() {
		MenuPage menu =  new MenuPage(driver);
		MyAccountPage myAccount =  new MyAccountPage(driver);
		//login in app		
		menu.click(menu.myAccountLink);
		myAccount.loginInApp(USER, PASS);
		//search product from homepage
		myAccount.click(myAccount.searchForm);
		myAccount.sendKeys(myAccount.searchField,"Pretzels");
		//find and navigate to product
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
		//add product to basket
		ProductPage product = new ProductPage(driver);
		product.click(product.addToCartButton);
		//Verify added to cart message
		//assertEquals(myAccount.getElementText(myAccount.addedToCartMessage), "View cart \"Rold Gold Tiny Twists Pretzels\" has been added to your cart.");
		
		//go cart page and update qty
		product.click(product.viewCartButton);
		product.click(product.updateQtyButton);
		
		
		//Verify price is updated
		//assert
		
		//Proceed to checkout
		product.click(product.proceedToCheckoutButton);
		//Place order
		product.click(product.termsCheckbox);
		product.click(product.placeOrderButton);
		
		//Verify order is placed and you are given an order nr
		assertEquals(product.getElementText(product.orderSuccessMessage), "Thank you. Your order has been received.");
		//assertEquals(product.getElementText(product.orderNumber), "Order number:");
		
		
		
		
		
		
	}
	
}
