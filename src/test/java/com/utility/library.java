package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {

	public static WebDriver driver;
	public static Properties propObj = new Properties();

	/*
	 * ExtentHtmlReporter : responsible for look and feel of the report ,we can
	 * specify the report name , document title , theme of the report
	 * 
	 * ExtentReports : used to create entries in your report , create test cases
	 * in report , who executed the test case, environment name , browser
	 * 
	 * ExtentTest : update pass fail and skips and logs the test cases results
	 */
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports ExtReport;
	public static ExtentTest ExtTest;

	// helper methods

	public static void StartExtentReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReportV4.html");
		htmlReporter.config().setDocumentTitle("AutomationReport");
		htmlReporter.config().setReportName("Selenium");
		htmlReporter.config().setTheme(Theme.STANDARD);
		ExtReport = new ExtentReports();
		ExtReport.attachReporter(htmlReporter);
		ExtReport.setSystemInfo("Host Name", "LocalHost");
		ExtReport.setSystemInfo("user", "Trainer:");
		ExtReport.setSystemInfo("Environemnet", (String) propObj.get("environment"));
		ExtReport.setSystemInfo("Browser", (String) propObj.get("browser"));
	}

	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(pageLoadCondition);
	}

	public static void readPropertyFIle() throws Exception {
		FileInputStream FileInputObj;
		try {
			FileInputObj = new FileInputStream(
					new File(System.getProperty("user.dir") + "/src/test/resources/configProperty.feature"));
			propObj.load(FileInputObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void luanchBrowser() {
		String browser = (String) propObj.get("browser");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(propObj.getProperty("GmoOnloneURL_SIT"));
		driver.manage().window().maximize();
		// implicit wait : Global wait applicable for all web elements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void ScrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static void ScrollIntoViewAndClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		element.click();
	}

	public static void ScrollIntoViewAndDoubleClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Actions obj = new Actions(driver);
		obj.doubleClick(element);
	}

	public static void ScrollIntoViewAndRightClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		Actions obj = new Actions(driver);
		obj.contextClick(element);
	}

	public static void DoubleClick(WebDriver driver, WebElement element) {
		Actions obj = new Actions(driver);
		obj.doubleClick(element).build().perform();
	}

	public static void SwitchtoFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void SwitchtoFrame(WebDriver driver, String name) {
		driver.switchTo().frame(name);
	}

	public static void SwitchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static void verifyinglinks(String Url) throws Exception {
		try {
			URL obj = new URL(Url);
			HttpURLConnection objHttpConnection = (HttpURLConnection) obj.openConnection();
			objHttpConnection.connect();
			int ResponseCode = objHttpConnection.getResponseCode();
			if (ResponseCode > 400 && ResponseCode < 600) {
				System.out.println(Url + ": " + "ResponseCode:" + ResponseCode + " is not a valid Link");
			} else if (ResponseCode > 200 && ResponseCode < 400) {
				System.out.println(Url + ": " + "ResponseCode:" + ResponseCode + " is a valid Link");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String takescreeshot(WebDriver driver) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//src//test//resources//screenshots//" + dateName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	public static String takescreeshot(WebDriver driver, String name) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//src//test//resources//ScreenShots//" + dateName + name
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	public static WebElement findElementByLocator(String ObjRepLocator){
		
		System.out.println(ObjRepLocator);
		String locator=ObjRepLocator.split("&")[0];
		String value = ObjRepLocator.split("&")[1];
		System.out.println("locator: "+locator);
		System.out.println("value: "+value);
		WebElement element=null;
		By search=null;
		if(locator.equals("id")){
			search=By.id(value);
		}else if(locator.equals("name")){
			search=By.name(value);
		}else if(locator.equals("className")){
			search=By.className(value);
		}else if(locator.equals("xpath")){
			search=By.xpath(value);
		}else if(locator.equals("cssSelector")){
			search=By.cssSelector(value);
		}else if(locator.equals("linkText")){
			search=By.linkText(value);
		}else if(locator.equals("partialLinkText")){
			search=By.partialLinkText(value);
		}else if(locator.equals("tagName")){
			search=By.tagName(value);
		}
		return driver.findElement(search);
	}

}
