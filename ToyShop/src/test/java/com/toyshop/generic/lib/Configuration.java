package com.toyshop.generic.lib;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Configuration {
	
	public static WebDriver driver;
	public static String chromeDrivePath = System.getProperty("user.dir")+"/Resources/Drivers/chromedriver";
	//public static String chromeDrivePath = "/Resources/Drivers/chromedriver";
	
	public static void initializeBrowser(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDrivePath);
				driver = new ChromeDriver();
				driver.get("http://jupiter.cloud.planittesting.com");
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	@BeforeClass
	public void beforeClass() {
		initializeBrowser("chrome");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
