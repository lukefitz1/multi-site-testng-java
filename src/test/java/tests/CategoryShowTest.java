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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pos.Category;
import pos.Header;

public class CategoryShowTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Category cat;
	private Header header;
	private int show;
	private int count;
	private int showOptionsCount;
	
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
	
	@Test(groups = { "functional" }, priority=0)
	public void goToTestCategoryPage() {
		header = new Header(driver);
		cat = new Category(driver);
		cat.goToTestCategoryPage();
	}
	
	@Test(groups = { "functional" }, priority=1)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header displayed");
	}
	
	@Test(groups = { "functional" }, priority=2)
	public void gridCheck() {
		Assert.assertTrue(cat.productGridDisplayed(), "Product grid displayed");
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void showSelectCheck() {
		Assert.assertTrue(cat.showSelectDisplayed(), "Show select displayed");
	}
	
	@Test(groups = { "functional" }, priority=4)
	public void getSelectedShowOption() {
		String s = cat.getSelectedShowOption();
		show = Integer.parseInt(s);
	}
	
	@Test(groups = { "functional" }, priority=5)
	public void numProductsDisplayedCheck() {
		count = cat.countProducts();
		Assert.assertTrue(count <= show, "Correct amount of products displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void changeShowOption() {
		showOptionsCount = cat.countShowOptions();
		cat.changeShowOption(1);
		cat.waitForOverlayNotDisplayed();
	}
	
	@Test(groups = { "functional" }, priority=7)
	public void numProductsDisplayedCheck2() {
		String s = cat.getSelectedShowOption();
		show = Integer.parseInt(s);	
		count = cat.countProducts();
		
		System.out.println(count + " " + show);
		Assert.assertTrue(count <= show, "Correct amount of products displayed");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
