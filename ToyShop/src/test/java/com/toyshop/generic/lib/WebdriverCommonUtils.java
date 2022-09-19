package com.toyshop.generic.lib;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WebdriverCommonUtils extends Configuration{
	
	//WebDriver driver = Driver.getDriver();
	public WebDriver driver;
	
	public WebdriverCommonUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public void explicitwait(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	//Click Specific Button
	public static void clickButton(WebElement ele) {
		if (ele.isDisplayed()) {
			ele.click();
		} else {
			Reporter.log("Element is missing in the page loaded. Please check.");
		}
	}
	
	
	//Check if Specific Button exists
		public static void elementExists(WebElement ele) {
			if (ele.isDisplayed()) {
				Reporter.log("WebElement exists in the page.");
			} else {
				Reporter.log("WebElement "+ ele+" does not exists. Please check.");
			}
		}
	
	//Enter the text to a specific Text Field
	public static void setText(WebElement ele, String text) {
		if (ele.isDisplayed()) {
			ele.sendKeys(text);
		} else {
			Reporter.log("Element is missing in the page loaded. Please check.");
		}
	}
	
	public static void assertEqual(WebElement ele, String expectedMsg, String errorMsg) {
		Assert.assertEquals(ele.getText(),expectedMsg, errorMsg);
	}
	
	public static void assertTrue(boolean flag, String expectedMsg) {
		Assert.assertEquals(flag,expectedMsg);
	}

	// Validate the string exists in the page or not
	public void checkElementExists(ArrayList<String> errMsg, boolean flagExists) {
		for (String str : errMsg) {
			if (flagExists) { // error message exists
				if ((driver.getPageSource().contains(str))) {
					Reporter.log("Required Error message " + str + " displayed in the page");
				} else {
					Reporter.log("Required Error message " + str + " does not present in the page");
				}
			} else {
				if ((driver.getPageSource().contains(str)) && (!flagExists)) {
					Reporter.log("Error message " + str + " still present in the page");
				}

				else {
					Reporter.log("Error message " + str + " still present in the page");
				}
			}
		}
	}

}
