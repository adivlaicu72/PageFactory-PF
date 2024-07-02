package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import page.objects.ContactPage;
import page.objects.MenuPage;
import selenium.utils.BaseTest;
import selenium.utils.TestNgListener;

@Listeners(TestNgListener.class)
public class ContactFormTest extends BaseTest{

	@Test(priority=1)
	public void sendValidMessage() {
		MenuPage menuPage =  new MenuPage(driver);
		menuPage.click(menuPage.contactPageLink);
		
		ContactPage contactPage = new ContactPage(driver);
		contactPage.sendMessage
				("Ion", 
				"ion@ion.com", 
				"Salut", 
				"Salut! Eu sunt Ion");		
		//1
		assertEquals(contactPage.getElementText
					(contactPage.sentMsgTxt),
					"Thank you for your message. It has been sent.");	
		//2
		assertEquals(contactPage.sentMsgTxt.getText(),
				"Thank you for your message. It has been sent.");	
			
	}
	
	@Test(priority=2)
	public void sendInvalidMessage() {
		MenuPage menuPage =  new MenuPage(driver);
		menuPage.click(menuPage.contactPageLink);
		
		ContactPage contactPage = new ContactPage(driver);
		contactPage.sendMessage
				("Ion", 
				"", 
				"Salut", 
				"Salut! Eu sunt Ion");		
		//1
		assertEquals(contactPage.getElementText
					(contactPage.sentMsgTxt),
					"Thank you for your message. It has been sent.");	
		//2
		assertEquals(contactPage.sentMsgTxt.getText(),
				"Thank you for your message. It has been sent.");	
			
	}
	
	
}