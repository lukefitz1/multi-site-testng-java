package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;

import pos.Header;
import pos.Homepage;

public class HeaderVisualTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	
	@Parameters({"pbrowser", "pversion", "pos", "purl", "pdevice"})
	@BeforeClass(alwaysRun = true)
	public void setUpTests(String pbrowser, String pversion, String pos, String purl, String pdevice) throws MalformedURLException {		
		browser = pbrowser;
		device = pdevice;
		capabilities = new DesiredCapabilities();
		
		if (device.equalsIgnoreCase("desktop")) {			
			capabilities.setBrowserName(pbrowser);
			capabilities.setVersion(pversion);
			capabilities.setPlatform(setPlatform(pos));
			
			driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
			driver.manage().window().setSize(new Dimension(width, height));

			driver = eyes.open(driver, client.toUpperCase() + " header visual tests", client.toUpperCase() + " header tests in " + browser + " " + version + " on " + os + " " + device, new RectangleSize(width, height));
		}
		else if (device.equalsIgnoreCase("iPhone 6")){
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
			
			driver = new IOSDriver(new URL(gridUrl), capabilities);
		}

	}
	
	@Test(groups = { "functional" }, priority=0)
	public void goToHomePage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=1)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header is displayed");
	}
	
	@Test(groups = { "functional" }, priority=2)
	public void searchCheck() {
		Assert.assertTrue(header.searchDisplayed(), "Search is displayed");
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void waitForModal() {
		if (getHomepageModal() == true) {
			System.out.println("There is a modal");
		}
		else {
			System.out.println("There is no modal");
		}
	}
	
	@Test(groups = { "functional" }, priority=4)
	public void closeModal() {
		if (getHomepageModal() == true) {
			Assert.assertTrue(hp.closeModalDisplayed(), "Close Modal Displayed");
			hp.clickCloseModal();
		}
		else {
			System.out.println("There is no modal");
		}
	}
	
	@Test(groups = { "visual" }, priority=5)
	public void headerScreenshot() {
		WebElement e = driver.findElement(header.getHeader());
		this.checkRegWithShift(eyes, e, 0, client.toUpperCase() + " header in " + browser);
	}
	
	@Test(groups = { "visual" }, priority=6)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		eyes.abortIfNotClosed();
		driver.quit();
	}
}
