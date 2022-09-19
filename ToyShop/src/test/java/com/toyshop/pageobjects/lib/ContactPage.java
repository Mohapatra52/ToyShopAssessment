package com.toyshop.pageobjects.lib;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.toyshop.generic.lib.Configuration;
import com.toyshop.generic.lib.WebdriverCommonUtils;

public class ContactPage extends Configuration{
	
	public WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebdriverCommonUtils wdcLib = new WebdriverCommonUtils(driver);
	
	@FindBy(id="nav-contact")
	private WebElement menu_Contacts;
	@FindBy(id="nav-home")
	private WebElement menu_Home;
	@FindBy(id="forename")
	private WebElement txt_Forename;
	@FindBy(id="surname")
	private WebElement txt_Surname;
	@FindBy(id="email")
	private WebElement txt_email;
	@FindBy(id="telephone")
	private WebElement txt_telephone;
	@FindBy(id="message")
	private WebElement txt_Message;
	
	
	@FindBy(xpath="//div[@class='alert alert-error ng-scope']")
	private WebElement errorMsg_Contact_Welcome;
	@FindBy(id="forename-err")
	private WebElement errorMsg_Contact_Forename;
	@FindBy(id="email-err")
	private WebElement errorMsg_Contact_Email;
	@FindBy(id="message-err")
	private WebElement errorMsg_Contact_Message;
	@FindBy(xpath="//div[@class='alert alert-info ng-scope']")
	private WebElement successMsg_Verification;
	
	@FindBy(xpath="//a[@class='btn-contact btn btn-primary']")
	private WebElement btn_Submit;
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement successMsg_FeedbackSent;
	
	@FindBy(xpath="//a[@ng-click='goBack()']")
	private WebElement btn_Back;
	
	
	public WebElement getBtn_Back() {
		return btn_Back;
	}
	public WebElement getSuccessMsg_FeedbackSent() {
		return successMsg_FeedbackSent;
	}
	public WebElement getErrorMsg_Contact_Welcome() {
		return errorMsg_Contact_Welcome;
	}
	public WebElement getErrorMsg_Contact_Forename() {
		return errorMsg_Contact_Forename;
	}
	public WebElement getErrorMsg_Contact_Email() {
		return errorMsg_Contact_Email;
	}
	public WebElement getErrorMsg_Contact_Message() {
		return errorMsg_Contact_Message;
	}
	
	public WebElement gettxt_Forename() {
		return txt_Forename;
	}
	public WebElement gettxt_Surname() {
		return txt_Surname;
	}
	public WebElement gettxt_Email() {
		return txt_email;
	}
	public WebElement gettxt_Telephone() {
		return txt_telephone;
	}
	public WebElement gettxt_Message() {
		return txt_Message;
	}

	public WebElement getmenu_Contacts() {
		return menu_Contacts;
	}
	
	public WebElement getSuccessMsg_Verification() {
		return successMsg_Verification;
	}
	public WebElement getBtn_Submit() {
		return btn_Submit;
	}
	public WebElement getMenu_Home() {
		return menu_Home;
	}
	
	//-----------------Actions ---------------------
	
	public void click_btnContact() {
		WebdriverCommonUtils.clickButton(menu_Contacts);
	}
	
	public void click_btnSubmit() {
		WebdriverCommonUtils.clickButton(btn_Submit);
	}
	
	public void loadHomePage() {
		menu_Home.click();
	}
	
	public void loadContactPage() {
		menu_Contacts.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(btn_Submit));
	}
	

	//Input to Contact Page web elements
	public void enterValuesInContactPage() {
		WebdriverCommonUtils.setText(txt_Forename, "Test Customer");
		WebdriverCommonUtils.setText(txt_email, "test@planit.com.au");
		WebdriverCommonUtils.setText(txt_Message, "Thanks for the quality Product");
	}	
	
	
}
