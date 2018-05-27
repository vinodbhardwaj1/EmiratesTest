package com.Test.TestScript;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Test.AppiumSupport.DriverManager;
import com.Test.Utils.TestCases;

public class TestApp extends DriverManager {

	@Test
	public void TestCaseFirst() {
		boolean result = false;
		try {
			
			TestCases utilObj = new TestCases(driver);
			result = utilObj.NameAndCarDispalyTest();

		} catch (Exception e) {	
			result = false;
		} finally {
			Assert.assertEquals(result, true);
			
		}
	}
	
	@Test
	public void TestCase2() {
		boolean result = false;
		try {
			
			TestCases utilObj = new TestCases(driver);
			result = utilObj.Test2();

		} catch (Exception e) {	
			e.printStackTrace();
			result = false;
		} finally {
			Assert.assertEquals(result, true);
			
		}
	}

}
