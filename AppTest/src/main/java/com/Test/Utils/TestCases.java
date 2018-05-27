package com.Test.Utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TestCases {

	AppiumDriver<?> driver;

	@AndroidFindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	private MobileElement browserIcon;

	@AndroidFindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
	private MobileElement showPopupWindowBtn;

	@AndroidFindBy(id = "io.selendroid.testapp:id/exceptionTestButton")
	private MobileElement exceptionButton;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Enter your name here!']")
	private MobileElement nameBox;

	@AndroidFindBy(xpath = "//android.widget.Spinner[@content-desc='Volvo']")
	private MobileElement preferredCarDropDownBox;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Audi']")
	private MobileElement carAudi;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Send me your name!']")
	private MobileElement sendMeYourNameBtn;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=='\"Audi\"']")
	private MobileElement carAudiDisplayed;


	public TestCases(AppiumDriver<?> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, 60, TimeUnit.SECONDS), this);
	}

	// ===================================

	public boolean Test2() {
		boolean result = false;
		try {
			waitForElement(driver, 90, "id", "io.selendroid.testapp:id/buttonStartWebview");
			showPopupWindowBtn.click();
			Thread.sleep(2000);

			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.dismiss();

			exceptionButton.click();
			Thread.sleep(2000);
			getAdbLogs();

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	public boolean NameAndCarDispalyTest() {
		boolean result = false;
		try {
			if (waitForElement(driver, 90, "id", "io.selendroid.testapp:id/buttonStartWebview")) {
				browserIcon.click();
			}

			 nameBox.click();
			 nameBox.clear();
			 nameBox.sendKeys("Vinod");
//			 nameBox.setValue("Vinod");

			preferredCarDropDownBox.click();
			carAudi.click();
			sendMeYourNameBtn.click();

			if (waitForElement(driver, 90, "id",
					"//android.view.View[@contains(content-desc,'Audi')]")) {
				result = true;
				System.out.println("Audi displayed");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;
	}

	private static void getAdbLogs() throws IOException {
		Process p = Runtime.getRuntime().exec("adb logcat -d");
		PrintStream originalOutStream = System.out;

		PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\vinod.kumar\\Desktop\\output.txt", false));
		System.setOut(out);

		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		System.out.println("==================================STARTING===============================");
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("==================================ENDING===============================");
		System.setOut(originalOutStream);
		out.flush();
		out.close();
		p.destroy();
	}

	
	public static boolean waitForElement(AppiumDriver<?> driver, int maxWait, String findBy, String findByText) {
		boolean result = false;
		MobileElement waitElement = null;
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		try {
			if (findBy.equalsIgnoreCase("id"))
				waitElement = (MobileElement) wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(findByText)));
			else if (findBy.equalsIgnoreCase("xpath"))
				waitElement = (MobileElement) wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(findByText)));

			if (waitElement != null && waitElement.isDisplayed()) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
