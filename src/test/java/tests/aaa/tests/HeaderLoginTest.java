package tests.aaa.tests;

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

import tests.Base;
import tests.aaa.pos.Header;
import tests.aaa.pos.Homepage;

public class HeaderLoginTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Header header;
	private Homepage hp;
	
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
	
	@Test(groups = { "login" }, priority=0)
	public void goToLoginPage() {
		header = new Header(driver);
		hp = new Homepage(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "login" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header is displayed");
	}
	
	@Test(groups = { "login" }, priority=6)
	public void signInLinkCheck() {
		Assert.assertTrue(header.signInLinkDisplayed(), "Sign in link displayed");
	}
	
	@Test(groups = { "login" }, priority=9)
	public void openSignInDropdown() {
		header.clickSignInLink();
		Assert.assertTrue(header.signInDropdownOpen(), "Sign in dropdown is displayed");
	}
	
	@Test(groups = { "login" }, priority=12)
	public void loginFormCheck() {
		Assert.assertTrue(header.loginFormDisplayed(), "Login / register form is not displayed");
	}
	
	@Test(groups = { "login" }, priority=15)
	public void loginEmailCheck() {
		Assert.assertTrue(header.loginEmailFieldDisplayed(), "Login email field is not displayed");
	}
	
	@Test(groups = { "login" }, priority=18)
	public void loginPwCheck() {
		Assert.assertTrue(header.loginPwFieldDisplayed(), "Login pw field is not displayed");
	}
	
	@Test(groups = { "login" }, priority=21)
	public void loginButtonCheck() {
		Assert.assertTrue(header.loginButtonDisplayed(), "Login button is not displayed");
	}
	
	@Test(groups = { "login" }, priority=24)
	public void login() throws InterruptedException {
		header.fillLoginForm("luke.fitzgerald@blueacorn.com", "pass4luke");
		header.clickLoginButton();
		
		if (device.equalsIgnoreCase("desktop")) {
			System.out.println("Desktop login test");
			Assert.assertTrue(header.loggedInLabelDisplayed(), "Logged in label is not displayed");
		}
		else {
			System.out.println("Mobile login test");
			Thread.sleep(5000);
			Assert.assertTrue(header.signInLinkDisplayed(), "Sign in link is displayed");
			
			header.clickSignInLink();
			Assert.assertTrue(header.mobileLoggedInLabelDisplayed(), "Logged in label is not displayed");
		}
	}
	
	@Test(groups = { "login" }, priority=27)
	public void verifySignOutDropdown() {
	
		if (device.equalsIgnoreCase("desktop")) {
			header.clickLoggedInLabel();
			Assert.assertTrue(header.signOutButtonDisplayed(), "Sign out button is not displayed");
		}
		else {
			Assert.assertTrue(header.signOutButtonDisplayed(), "Sign out button is not displayed");
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
