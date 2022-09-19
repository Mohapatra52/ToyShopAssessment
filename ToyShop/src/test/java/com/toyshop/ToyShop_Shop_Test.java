package com.toyshop;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.toyshop.generic.lib.Configuration;
import com.toyshop.generic.lib.WebdriverCommonUtils;
import com.toyshop.pageobjects.lib.CartPage;
import com.toyshop.pageobjects.lib.ContactPage;


public class ToyShop_Shop_Test extends Configuration{
	
	public ContactPage contactPageElements; 
	public CartPage cartPageElements;
	
	
	@Test 
	  public void TC003() throws InterruptedException 
	  	{ 
			int noOf_stuffedFrog=2; 
			int noOf_fluffyBunny=5; 
			int noOf_valentineBear=3;
			cartPageElements = new CartPage(driver);
		  
			cartPageElements.click_menuShop();
			cartPageElements.buyItems("Stuffed Frog", noOf_stuffedFrog); 
			cartPageElements.buyItems("Fluffy Bunny",noOf_fluffyBunny); 
			cartPageElements.buyItems("Valentine Bear", noOf_valentineBear);
	  
	  	  	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	  	  	cartPageElements.click_menuCart();
		  
		  	cartPageElements.calculateSubTotal("Stuffed Frog", noOf_stuffedFrog); 
		  	cartPageElements.calculateSubTotal("Fluffy Bunny", noOf_fluffyBunny);
		  	cartPageElements.calculateSubTotal("Valentine Bear", noOf_valentineBear);
		  	cartPageElements.matchTotalAndSubTotal();
	  	}	

}
