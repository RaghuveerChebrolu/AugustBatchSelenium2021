package com.TestNG.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass2 {
	WebDriver driver = null;

	@Test(priority = 0)
	public void ValidateGMO_OnLineLoadedSuccessfully() {
		System.out.println("inside ValidateGMOONlineLoadedSuccessfully");
		String AcutualTitle = driver.getTitle();
		System.out.println("AcutualTitle: " + AcutualTitle);
		String ExpectedTitle = "Welcome to Green Mountain Outpost";
		Assert.assertEquals(AcutualTitle, ExpectedTitle);

	}

	@Test(priority = 1)
	public void ValidateEnterGMO_OnLineSuccessfully() {
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		String AcutualTitle = driver.findElement(By.xpath("(//*[contains(text(),'OnLine Catalog')])[2]")).getText();
		System.out.println("AcutualTitle: " + AcutualTitle);
		String ExpectedTitle = "OnLine Catalog";
		Assert.assertEquals(AcutualTitle, ExpectedTitle);
	}

	@Test(priority = 2)
	public void ValidateOrderSunGlasses() {
		driver.findElement(By.name("QTY_GLASSES")).sendKeys("3");
		driver.findElement(By.name("bSubmit")).click();
		String ActualTitle = driver.getTitle();
		System.out.println(ActualTitle);
		String ExpectedTitle="Place Order";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		String unitPrice=driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		System.out.println("unitPrice: "+unitPrice);
		String price=unitPrice.substring(2);
		System.out.println("price: "+price);
		Float PriceInIntegerFormat=Float.parseFloat(price);
		Float TotalPriceCalculated = PriceInIntegerFormat*3;
		System.out.println("TotalPriceCalculated: "+TotalPriceCalculated);
		String ActualOrderQtyPrice=driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
		System.out.println("ActualOrderQtyPrice: "+ActualOrderQtyPrice);
		String totalprice=ActualOrderQtyPrice.substring(2);
		System.out.println("totalprice: "+totalprice);
		Float ActuaPriceInFloatFormat=Float.parseFloat(totalprice);
		Assert.assertEquals(ActuaPriceInFloatFormat, TotalPriceCalculated);
	}
	
	@Test(priority=3)
	public void ValidatingAlerts(){
		driver.navigate().to("https://demoqa.com/alerts");
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert obj= driver.switchTo().alert();
		obj.accept();
		driver.findElement(By.id("confirmButton")).click();
		Alert obj1= driver.switchTo().alert();
		String ActualStr=obj1.getText();
		System.out.println("Alert Text: " + ActualStr);
		String Expected = "Do confirm action?";
		//Assert.assertEquals(ActualStr, Expected);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(ActualStr, Expected);
		obj1.dismiss();
		driver.findElement(By.id("promtButton")).click();
		Alert obj3= driver.switchTo().alert();
		obj3.sendKeys("raghu");
		obj3.accept();
		String ActualText=driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
		String ExpectedText = "You entered raghu";
		Assert.assertEquals(ActualText, ExpectedText);
		softAssert.assertAll();
	}
	
	@Test(priority=4)
	public void HandlingFrames(){
		System.out.println("inside HandlingFrames");
		driver.navigate().to("http://demo.automationtesting.in/Frames.html");
		waitForPageToLoad();
		driver.switchTo().frame("singleframe");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("hello");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//*[contains(text(),'Iframe with in an Iframe')]")).click();
		
		WebElement ParentFrameElement = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(ParentFrameElement);
		
		WebElement ChildFrameElement = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(ChildFrameElement);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("inside second frame");
		driver.switchTo().defaultContent();
		
	}
	
	//helper methods
	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		//explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(pageLoadCondition);
	}
	
	public void readPropertyFIle(){
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.borland.com/gmopost/");
		driver.manage().window().maximize();
		// implicit wait : Global wait applicable for all web elements 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("inside beforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
