package tests;

import org.testng.annotations.Test;

import framework.utils.PropertiesFileProcessor;
import page.objects.MenuPage;
import page.objects.MyAccountPage;
import page.objects.ProductPage;
import selenium.utils.BaseTest;

public class MultipleCategoriesTest extends BaseTest{
	
	String USER = PropertiesFileProcessor.readPropertiesFile("user", "credential.properties");
	String PASS = PropertiesFileProcessor.readPropertiesFile("pass", "credential.properties");

@Test
public void multipleCategoriesTest() throws InterruptedException {
	MenuPage menu =  new MenuPage(driver);
	MyAccountPage myAccount =  new MyAccountPage(driver);
	//login in app		
	menu.click(menu.myAccountLink);
	myAccount.loginInApp(USER, PASS);
	//search and add 4 products from Categories
	ProductPage product = new ProductPage(driver);
	product.click(product.allCategories);
	Thread.sleep(3000);
	product.hoverElement(product.fruitsVegetables);
	
}

}
