package com.toyshop;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.toyshop.generic.lib.Configuration;
import com.toyshop.generic.lib.WebdriverCommonUtils;
import com.toyshop.pageobjects.lib.CartPage;
import com.toyshop.pageobjects.lib.ContactPage;

public class ToyShop_Contact_Test extends Configuration{
	
	public ContactPage contactPageElements;
	public WebdriverCommonUtils wdcLib;
	
	//Array List to contain all error messages 
	ArrayList<String> errMsg = new ArrayList<String>(Arrays.asList("Forename is required", 
			"Email is required", 
			"Message is required", 
			"We welcome your feedback - but we won't get it unless you complete the form correctly."));
	
	public ToyShop_Contact_Test() {
		super();
	}

	@Test
	public void TC001() throws InterruptedException {
		contactPageElements= new ContactPage(driver);
		wdcLib = new WebdriverCommonUtils(driver);
		contactPageElements.loadContactPage();
		contactPageElements.click_btnSubmit();
		wdcLib.checkElementExists(errMsg,true);
		contactPageElements.enterValuesInContactPage();		
		wdcLib.checkElementExists(errMsg,false);
		WebdriverCommonUtils.assertEqual(contactPageElements.getSuccessMsg_Verification(), "We welcome your feedback - tell it how it is.", "Unable to Find the Expected Message");
	}
	
	@Test
	public void TC002() { 
		contactPageElements = new ContactPage(driver);
		wdcLib = new WebdriverCommonUtils(driver);
		contactPageElements.loadHomePage();
		contactPageElements.loadContactPage();
		contactPageElements.enterValuesInContactPage();
		contactPageElements.click_btnSubmit();
		wdcLib.explicitwait(contactPageElements.getBtn_Back());
		WebdriverCommonUtils.elementExists(contactPageElements.getBtn_Back());
		Assert.assertTrue(contactPageElements.getSuccessMsg_FeedbackSent().getText().contains("we appreciate your feedback."), "Feedback found");
	}
}
