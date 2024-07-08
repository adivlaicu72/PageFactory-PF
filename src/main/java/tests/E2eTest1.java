package tests;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
	public void e2eTest() throws InterruptedException {
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
		assertTrue(myAccount.getElementText(product.addedToCartMessage).contains("“Rold Gold Tiny Twists Pretzels” has been added to your cart."));
		
		//go cart page and update qty
		product.click(product.viewCartButton);
		product.click(product.updateQtyButton);
		 Thread.sleep(3000);
         //Verify price is updated
         //assert
         //List<WebElement> priceList = product.getWebElementList(product.price);
                
         //citesc textul si scot $
         String firstItemPrice =  product.price.get(3).getText().substring(1);
         String lastItemPrice =  product.price.get(0).getText().substring(1);
         //transform textul in double
         Double firstPrice = Double.parseDouble(firstItemPrice);
         Double lastPrice = Double.parseDouble(lastItemPrice);
         System.out.println(firstItemPrice);
         System.out.println(lastItemPrice);
         //assertez intr-un ternary
         assertTrue((firstPrice < lastPrice) ? true : false);
		
		//Proceed to checkout
		product.click(product.proceedToCheckoutButton);
		//Place order
		product.click(product.termsCheckbox);
		product.click(product.placeOrderButton);
		
		//Verify order is placed and you are given an order nr
		assertEquals(product.getElementText(product.orderSuccessMessage), "Thank you. Your order has been received.");
		assertNotNull(product.getElementText(product.orderNumber));
		
		
		
		
		
		
	}
	
}
