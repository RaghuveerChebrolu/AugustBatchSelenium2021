package com.TestNG.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNgClass1 {
	@Test (priority=2)
	public void Testcase1() {
		System.out.println("inside Testcase1");
	}

	@Test (invocationCount=8)
	public void abcTestcase2() {
		System.out.println("inside abcTestcase2");
	}
	
	@Test (invocationCount=8)
	public void abcdTestcase2() {
		System.out.println("inside abcdTestcase2");
	}

	@Test (priority=4,invocationCount=15,dependsOnMethods={"Testcase1"})
	public void Testcase3() {
		System.out.println("inside Testcase3");
	}
	
	@Test (priority=-8,enabled=false)
	public void Testcase4() {
		System.out.println("inside Testcase4");
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
		System.out.println("inside beforeSuite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
