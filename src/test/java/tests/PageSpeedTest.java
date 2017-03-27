package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pos.Homepage;
import pos.Header;

public class PageSpeedTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	JavascriptExecutor js;
	long loadEventEnd;
	long navigationStart;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, String pdevice) throws MalformedURLException {		
		browser = pbrowser;
		device = pdevice;
		capabilities = new DesiredCapabilities();
		
		if (device.equalsIgnoreCase("desktop")) {
			System.out.println("Desktop tests");
			
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			capabilities.setPlatform(setPlatform(pos));
			
			driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			driver.manage().window().setSize(new Dimension(width, height));
		}
		else if (device.equalsIgnoreCase("iPhone 6")){
			System.out.println("mobile tests");
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
			
			driver = new IOSDriver(new URL(gridUrl), capabilities);
		}
	}
	
	@Test(groups = { "speed-test" }, priority=0)
	public void goToHomePage() {
		hp = new Homepage(driver);
		header = new Header(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "speed-test" }, priority=1)
	public void measureHomepageLoadTime() {
		Assert.assertTrue(header.headerDisplayed(), "Header is not displayed");
	}
	
	@Test(groups = { "speed-test" }, priority=2)
	public void echoPageLoadSpeed() {
		js = (JavascriptExecutor) driver;
		loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
		navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
		
		System.out.println("Homepage load time is " + ((loadEventEnd - navigationStart) / 1000) + " seconds");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
