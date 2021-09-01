package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ObjectRepository {
		public static final String EnterGMO_OnLine=constants.xpath+"&"+"//input[@name='bSubmit']";
		public static final String EnterGMOOnLineTitle=constants.xpath+"&"+"(//*[contains(text(),'OnLine Catalog')])[2]";
		public static final String OrderSunGlasses = constants.Name+"&"+"QTY_GLASSES";
		public static final String SubmitButton = constants.Name+"&"+"bSubmit";
		//public static final String EnterGMO_OnLine
	//	public static final String EnterGMO_OnLine
		
		public static final String BrokenLinksTagName = constants.tagName+"&"+"a";
		public static final String WebTableLastNames = constants.xpath+"&"+"//table[@id='example']/tbody/tr/td[3]";
		
		public static final String DragItem = constants.ID+"&"+"draggable";
		public static final String DropItem = constants.ID+"&"+"droppable";
		
		public static final String FileDownload = constants.xpath+"&"+"//a[@download='file-sample_100kB.doc']";
	
		public static final String RegisterFirstName = constants.xpath+"&"+"//input[@placeholder='First Name']";
		public static final String RegisterLastNameName = constants.xpath+"&"+"//input[@placeholder='Last Name']";
		public static final String RegisterAddress = constants.xpath+"&"+"//textarea[@ng-model='Adress']";
		public static final String RegisterEmailAddress = constants.xpath+"&"+"//input[@type='email']";
		public static final String RegisterPhone = constants.xpath+"&"+"//input[@type='tel']";
		public static final String RegisterGenderMale = constants.xpath+"&"+"//input[@value='Male']";
		public static final String RegisterGenderFemale = constants.xpath+"&"+"//input[@value='FeMale']";
		public static final String RegisterHobbiesCricket = constants.ID+"&"+"checkbox1";
		public static final String RegisterHobbiesMovies = constants.ID+"&"+"checkbox2";
		public static final String RegisterHobbiesHockey = constants.ID+"&"+"checkbox3";
		public static final String RegisterLaunguages = constants.ID+"&"+"msdd";
		public static final String RegisterSkills = constants.xpath+"&"+"//select[@type='text' and @id='Skills']";
		public static final String RegisterCountry = constants.ID+"&"+"countries";
		public static final String RegisterSelect_Country = constants.xpath+"&"+"//span[@role='combobox']";
		public static final String RegisterDOBYY = constants.ID+"&"+"yearbox";
		public static final String RegisterDOBMM = constants.xpath+"&"+"//select[@placeholder='Month']";
		public static final String RegisterDOBDD = constants.ID+"&"+"daybox";
		public static final String RegisterPWD = constants.ID+"&"+"firstpassword";
		public static final String RegisterConfirmPWD = constants.ID+"&"+"secondpassword";
	
		
		
	}


