package com.TestNG.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.utility.ObjectRepository;
import com.utility.constants;
import com.utility.library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.input.WindowsLineEndingInputStream;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass4 extends library {

	@Test(priority = 0)
	public void ValidateGMO_OnLineLoadedSuccessfully() {
		System.out.println("inside ValidateGMOONlineLoadedSuccessfully");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		String AcutualTitle = driver.getTitle();
		System.out.println("AcutualTitle: " + AcutualTitle);
		String ExpectedTitle = "Welcome to Green Mountain Outpost";
		Assert.assertEquals(AcutualTitle, ExpectedTitle);

	}

	@Test(priority = 1)
	public void ValidateEnterGMO_OnLineSuccessfully() {
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		library.findElementByLocator(ObjectRepository.EnterGMO_OnLine).click();
		String AcutualTitle = library.findElementByLocator(ObjectRepository.EnterGMOOnLineTitle).getText();
		System.out.println("AcutualTitle: " + AcutualTitle);
		String ExpectedTitle = "OnLine Catalog";
		Assert.assertEquals(AcutualTitle, ExpectedTitle);
		
		
	}

	@Test(priority = 2)
	public void ValidateOrderSunGlasses() {
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.name("QTY_GLASSES")).sendKeys(constants.QTY_GLASSES);
		driver.findElement(By.name("bSubmit")).click();
		String ActualTitle = driver.getTitle();
		System.out.println(ActualTitle);
		String ExpectedTitle = "Place Order";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
		String unitPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		System.out.println("unitPrice: " + unitPrice);
		String price = unitPrice.substring(constants.pricesubstring);
		System.out.println("price: " + price);
		Float PriceInIntegerFormat = Float.parseFloat(price);
		Float TotalPriceCalculated = PriceInIntegerFormat * Integer.parseInt(constants.QTY_GLASSES);
		System.out.println("TotalPriceCalculated: " + TotalPriceCalculated);
		String ActualOrderQtyPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
		System.out.println("ActualOrderQtyPrice: " + ActualOrderQtyPrice);
		String totalprice = ActualOrderQtyPrice.substring(2);
		System.out.println("totalprice: " + totalprice);
		Float ActuaPriceInFloatFormat = Float.parseFloat(totalprice);
		Assert.assertEquals(ActuaPriceInFloatFormat, TotalPriceCalculated);
	}

	@Test(priority = 3)
	public void ValidatingAlerts() {
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to("https://demoqa.com/alerts");
		library.waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert obj = driver.switchTo().alert();
		obj.accept();
		driver.findElement(By.id("confirmButton")).click();
		Alert obj1 = driver.switchTo().alert();
		String ActualStr = obj1.getText();
		System.out.println("Alert Text: " + ActualStr);
		String Expected = "Do confirm action?";
		// Assert.assertEquals(ActualStr, Expected);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(ActualStr, Expected);
		obj1.dismiss();
		driver.findElement(By.id("promtButton")).click();
		Alert obj3 = driver.switchTo().alert();
		obj3.sendKeys("raghu");
		obj3.accept();
		String ActualText = driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
		String ExpectedText = "You entered raghu";
		Assert.assertEquals(ActualText, ExpectedText);
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void HandlingFrames() {
		System.out.println("inside HandlingFrames");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("FramesURL"));
		waitForPageToLoad();
		library.SwitchtoFrame(driver, constants.Singleframe);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(constants.SingleFrameText);
		library.SwitchToDefaultContent(driver);
		driver.findElement(By.xpath("//*[contains(text(),'Iframe with in an Iframe')]")).click();
		WebElement ParentFrameElement = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		library.SwitchtoFrame(driver, ParentFrameElement);
		WebElement ChildFrameElement = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		library.SwitchtoFrame(driver, ChildFrameElement);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(constants.SecondFrameText);
		library.SwitchToDefaultContent(driver);

	}

	@Test(priority = 5)
	public void HandlingWindows() {
		System.out.println("HandlingWindows");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("WindowsURL"));
		waitForPageToLoad();
		Set<String> allWindows = driver.getWindowHandles();
		int CountOfWindows = allWindows.size();
		System.out.println(CountOfWindows);
		for (String window : allWindows) {
			driver.switchTo().window(window);
			String Title = driver.getTitle();
			System.out.println("Title: " + Title);
			if (Title.equals("Cognizant")) {
				driver.manage().window().maximize();
				driver.close();// this will close the current browser
			} else if (Title.equals("Tech Mahindra")) {
				driver.manage().window().maximize();
				driver.close();
			} else if (Title.equals("Jobs - Recruitment - Job Search - Employment -Job Vacancies - Naukri.com")) {
				driver.close();
			}
		}
		// driver.quit();//will close all windows (all instances of webdriver)
	}

	@Test(priority = 6)
	public void HandlingWebTable() {
		System.out.println("inside HandlingWebTable");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("WebTableURL"));
		waitForPageToLoad();
		String LastNames = propObj.getProperty("webtableLastNames");
		//List<WebElement> AllItmes = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[3]"));
		List<WebElement> AllItmes = library.findElementsByLocator(ObjectRepository.WebTableLastNames);
		int Count = AllItmes.size();
		// System.out.println(LastNames);
		String AllLastNames[] = LastNames.split(",");
		for (String Name : AllLastNames) {
			for (int i = 1; i <= Count; i++) {
				String LastName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[3]"))
						.getText();
				System.out.println(LastName);
				if (LastName.equals(Name)) {
					String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[7]"))
							.getText();
					System.out.println(Salary);
					break;
				}
			}
		}
	}

	@Test(priority = 7)
	public void HandlingMouseActionsRightClick() {
		System.out.println("inside HandlingMouseActions");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("mouseOpeartionRightClick"));
		waitForPageToLoad();
		Actions obj = new Actions(driver);
		WebElement target = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
		obj.contextClick(target).build().perform();
		String action = driver
				.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-copy']/span"))
				.getText();
		System.out.println("action: " + action);
		driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-copy']/span"))
				.click();
		Alert alertobj = driver.switchTo().alert();
		String AlertText = alertobj.getText().substring(9);
		System.out.println("AlertText: " + AlertText);
		// String text=AlertText.substring(9);
		// AlertText.contains(action.toLowerCase())
		Assert.assertEquals(AlertText, action.toLowerCase());
		alertobj.accept();
	}

	@Test(priority = 8)
	public void HandlingMouseOpeartionDoubleClick() {
		System.out.println("inside HandlingMouseOpeartionDoubleClick");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("mouseOpeartionDoubleClick"));
		waitForPageToLoad();
		// js.executeScript("window.scrollBy(0,500)");//scroll 500 pixels
		// vertically downwards
		// js.executeScript("window.scrollBy(0,-500)");//scroll 500 pixels
		// vertically upwards
		// js.executeScript("window.scrollBy(1000,0)");//scroll 1000 pixels
		// horizontally rightwards
		// js.executeScript("window.scrollBy(-300,0)");//scroll 300 pixels
		// horizontally leftwards
		WebElement frameElement = driver.findElement(By.xpath("//iframe"));
		library.ScrollIntoView(driver, frameElement);
		driver.switchTo().frame(frameElement);
		WebElement target = driver
				.findElement(By.xpath("//*[contains(text(),'Double click the block')]/preceding-sibling::div"));
		library.DoubleClick(driver, target);
	}

	@Test(priority = 9)
	public void HandlingMouseOpearationDragAndDrop() {
		System.out.println("inside HandlingMouseOpearationDragAndDrop");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		Actions obj = new Actions(driver);
		WebElement frameElement = driver.findElement(By.xpath("//iframe"));
		library.SwitchtoFrame(driver, frameElement);
		//WebElement source = driver.findElement(By.id("draggable"));
	//	WebElement target = driver.findElement(By.id("droppable"));
		WebElement source = library.findElementByLocator(ObjectRepository.DragItem);
		WebElement target = library.findElementByLocator(ObjectRepository.DropItem);
		// obj.dragAndDrop(source, target).build().perform();
		obj.clickAndHold(source).build().perform();
		obj.moveToElement(target).click().build().perform();
		String ActualText = driver.findElement(By.xpath("//div[@id='droppable']/p")).getText();
		String ExpectedtText = "Dropped!";
		Assert.assertEquals(ActualText, ExpectedtText);
		driver.switchTo().defaultContent();
	}

	@Test(priority = 10)
	public void HandlingBrokenLinks() {
		System.out.println("inside HandlingBrokenLinks");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("BrokenLinks"));
		waitForPageToLoad();
		//List<WebElement> AllLinks = driver.findElements(By.tagName("a"));
		List<WebElement> AllLinks = library.findElementsByLocator(ObjectRepository.BrokenLinksTagName);
		for (int i = 1; i < AllLinks.size(); i++) {
			WebElement IndividualLink = AllLinks.get(i);
			String IndividualLinkUrl = IndividualLink.getAttribute("href");
			try {
				library.verifyinglinks(IndividualLinkUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Test(priority = 11)
	public void HandlingFileUpload() throws AWTException, InterruptedException {
		System.out.println("inside HandlingFileUpload");
		ExtTest = ExtReport.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(propObj.getProperty("FileUpload"));
		waitForPageToLoad();

		WebElement Ele = driver.findElement(By.xpath("//input[@id='input-4']/preceding-sibling::span"));
		// Ele.click();
		Actions obj = new Actions(driver);
		obj.moveToElement(Ele).click().build().perform();

		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		try {
			Transferable objTransferable = objClipboard.getContents(null);
			if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Robot objRobot = new Robot();
		objRobot.delay(250);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod(ITestResult Result) {
		System.out.println("inside afterMethod");
		if (Result.getStatus() == ITestResult.FAILURE) {
			ExtTest.log(Status.FAIL, "TestCase failed is :" + Result.getName());
			ExtTest.log(Status.FAIL, "TestCase failed is :" + Result.getThrowable());
			try {
				String screencastPath = library.takescreeshot(driver, Result.getName());
				ExtTest.addScreenCaptureFromPath(screencastPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (Result.getStatus() == ITestResult.SUCCESS) {
			ExtTest.log(Status.PASS, "TestCase Pass is :" + Result.getName());
		} else if (Result.getStatus() == ITestResult.SKIP) {
			ExtTest.log(Status.SKIP, "TestCase Skipp is :" + Result.getName());
		}

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		library.StartExtentReport();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		library.luanchBrowser();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		ExtReport.flush();// dont forget to give this inrder to generate report
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		System.out.println("inside beforeSuite");
		library.readPropertyFIle();
	}
}