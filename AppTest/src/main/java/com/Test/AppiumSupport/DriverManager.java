package com.Test.AppiumSupport;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

	public AppiumDriver<?> driver;
	public String udid;
	public String systemPort;
	AppiumDriverFactory obj = new AppiumDriverFactory();
	
	@Parameters({"udid","systemPort"})
	@BeforeMethod
	public void startDriver(String udid, String systemPort){
		this.udid = udid;
		this.systemPort = systemPort;
		
		driver = obj.getAppDriverSelendroid(udid, systemPort);
	}
	
	@AfterMethod
	public void killDriver(){
		if(driver!=null)
		driver.quit();
	}
	
	
	@AfterTest
	public void tearDown(){
		if(driver!=null)
		driver.quit();
	}
	
	
}
