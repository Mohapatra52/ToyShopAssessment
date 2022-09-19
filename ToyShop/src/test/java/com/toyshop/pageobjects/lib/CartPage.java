package com.toyshop.pageobjects.lib;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.toyshop.generic.lib.Configuration;
import com.toyshop.generic.lib.WebdriverCommonUtils;


public class CartPage extends Configuration{
	
	HashMap <String, Double> products = new HashMap<String, Double>();
	HashMap <String, Double> productPrice = new HashMap<String, Double>();
	
	public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebdriverCommonUtils wdcUtil = new WebdriverCommonUtils(driver);

	@FindBy(xpath="//a[@class='btn btn-danger ng-scope']")
	private WebElement btn_EmptyCart;
	
	@FindBy(xpath="//table[@class='table table-striped cart-items']")
	private WebElement table_cart;
	
	@FindBys(value = { @FindBy (xpath="//tr[@class='cart-item ng-scope']")})
	private List<WebElement> cart_Rows;
	
	@FindBy(xpath="//strong[@class='total ng-binding']")
	private WebElement totalPrice;
	
	@FindBy(id="nav-cart")
	private WebElement menu_cart;
	
	
	@FindBy(id="nav-shop")
	private WebElement menu_shop;
	
	public WebElement getmenu_Shop() {
		return menu_shop;
	}
	
	@FindBys(value = { @FindBy (xpath="//li[@class='product ng-scope']")})
	private List<WebElement> productList;
	
	public List<WebElement> getproductList() {
		return productList;
	}
	

	
	
	public WebElement getmenu_cart() {
		return menu_cart;
	}
	
	public WebElement gettotalPrice() {
		return totalPrice;
	}
	
	public WebElement getbtn_EmptyCart() {
		return btn_EmptyCart;
	}
	
	public WebElement gettable_cart() {
		return table_cart;
	}
	
	public List<WebElement> getcart_Rows() {
		return cart_Rows;
	}
	
	
	//-----------------Actions ---------------------
	
	public void click_menuShop() {
		WebdriverCommonUtils.clickButton(menu_shop);
	}
	
	public void click_menuCart() {
		WebdriverCommonUtils.clickButton(menu_cart);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(getbtn_EmptyCart()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
	}
	
	//Fetch Cart Details
	public void getCartDetails() {
		List<WebElement> rowItems = getcart_Rows();
		for(WebElement wb:rowItems) {
			String myCartDetails = wb.getText(); 
			String[] myDetails = myCartDetails.split(" ");
			String itemName = myDetails[0]+" "+myDetails[1];
			String totalPrice = myDetails[3].replace("$", "");
			double subTotal = Double.parseDouble(totalPrice);
			products.put(itemName, subTotal);
		}			
	}
		
	// Calculate Individual price details 
	public void calculateSubTotal(String itemName, int noOfItems)
		{
		getCartDetails();
		double myProductPrice = 0.0;
		double indPrice = productPrice.get(itemName);
		myProductPrice = indPrice * noOfItems;
		if (myProductPrice == products.get(itemName)) {
			Reporter.log("Price matched");
		} else {
			Reporter.log("Price didn't matched");
		}
	}
	
	// Calculate Total Price Details 
  	public double totalPriceMatch() {
  		getCartDetails();
  		double totalPrice = 0.0;
		for(double me:products.values())
		{
			totalPrice = totalPrice + me;
		}
		return totalPrice;
  	}
  	
 // Identify the item's index in the Shop Page 
 	public int shoppingItems(String itemName)
 	{ 
 	  List<WebElement> items = getproductList(); 
 	  int index = 1; 
 	  for(WebElement sItem : items) {
 		  if((sItem.isDisplayed())&&((sItem.getText()).contains(itemName))) { 
 			  String myProductDetails = sItem.getText(); 
 			  String[] myProduct = myProductDetails.split("[$]"); 
 			  String itemPrice = myProduct[1].replace(" Buy", ""); 
 			  double individualPrice = Double.parseDouble(itemPrice); 
 			  productPrice.put(itemName, individualPrice);
 			  //setProductPrice(productPrice);
 			  break; 
 			  } 
 		  index++; 
 		  }
 	  		return index;   
 	}
  	
 	public void buyItems(String itemName, int noOfItems) {
		int position = shoppingItems(itemName);
		String myXpath = "(//li[@class='product ng-scope']/div/p/a)[" + position + "]";
		WebElement wm = driver.findElement(By.xpath(myXpath));
		for (int i = 0; i < noOfItems; i++) {
			wm.click();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Error in multiple clicks on BUY button");
			}
		}

	}
 	
  	//Verify that total = sum(sub totals) 
  	public void matchTotalAndSubTotal() {
	  double totalPrice = totalPriceMatch();
	  String []cartPrice = gettotalPrice().getText().split("Total: "); 
	  double sumPrice = Double.parseDouble(cartPrice[1]);
  
	  if(totalPrice==sumPrice) {
		  Reporter.log("Total sum of sub modules matches with the total Cart Price"); }
	  else { 
		  Reporter.log("Total sum of sub modules does not match with the total Cart Price"); } 
  	}
}
