package com.Test.AppiumSupport;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumDriverFactory {

	public AppiumDriver<MobileElement> driver = null;
	public DesiredCapabilities capabilities = DesiredCapabilities.android();

	
	public  AppiumDriver<?> getAppDriverSelendroid(String udid, String systemPort) {
		try {
			URL url = new URL("http://0.0.0.0:4723/wd/hub");

			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
//			capabilities.setCapability(MobileCapabilityType.APP, file);
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.selendroid.testapp.HomeScreenActivity");
			capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
			
			driver = new AndroidDriver<MobileElement>(url, capabilities);
			System.out.println("AndroidDriver: "+driver);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

}
