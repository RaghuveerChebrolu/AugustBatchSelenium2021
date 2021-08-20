package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class library {

	public static WebDriver driver;
	public static Properties propObj = new Properties();

	// helper methods
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
}
