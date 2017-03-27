package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pos.*;
import utilities.HttpResponseCode;

public class HttpStatusCodeTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private HttpResponseCode http;

	@BeforeClass(alwaysRun = true)
	public void setUpTests() throws MalformedURLException {		
		capabilities = new DesiredCapabilities();
		
		if (device.equalsIgnoreCase("desktop")) {
			System.out.println("Desktop tests");
			
			capabilities.setBrowserName(browser);
			capabilities.setVersion(version);
			capabilities.setPlatform(setPlatform(os));
			
			driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			driver.manage().window().setSize(new Dimension(width, height));
		}
		else if (device.equalsIgnoreCase("iPhone 6")){
			System.out.println("mobile tests");
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, setPlatform(os));
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
			
			System.out.println(gridUrl);
			driver = new IOSDriver(new URL(gridUrl), capabilities);
		}
	}
	
	@Test(groups = { "functional" }, priority=0)
	public void goToHomepage() {
		hp = new Homepage(driver);
		http = new HttpResponseCode(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void httpStatusCheck() {
		Assert.assertEquals(http.checkHttpResponseCode(driver.getCurrentUrl()), 200, "Response code is a 200!");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void homepageStringPresent() {
		Assert.assertTrue(hp.homepageStringDisplayed(), "Homepage string is displayed");
	}
	
	@Test(groups = { "functional" }, priority=9)
	public void homepageStringCheck() {
		String a = hp.getHomepageStringText().toLowerCase();
		String b = hp.getHomepageString().toLowerCase(); 
		Assert.assertEquals(a, b, "Homepage string matches expected value");
	}
	
	@Test(groups = { "visual" }, priority=12)
	public void visualTest() {
		System.out.println("THIS IS A TEST IN THE VISUAL GROUP - THIS IS A TEST IN THE VISUAL GROUP - THIS IS A TEST IN THE VISUAL GROUP");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
