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

import pos.MyAccount;

public class AccountLoginTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private MyAccount acct;
	
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
		acct = new MyAccount(driver);
		acct.goToLogin();
	}
	
	@Test(groups = { "login" }, priority=0)
	public void loginFormCheck() {
		Assert.assertTrue(acct.loginFormDisplayed(), "Login / register form is not displayed");
	}
	
	@Test(groups = { "login" }, priority=3)
	public void loginEmailCheck() {
		//System.out.println(acct.loginEmailFieldExists());
		//System.out.println(acct.loginEmailFieldDisplayed());
		
		Assert.assertTrue(acct.loginEmailFieldDisplayed(), "Login email field is not displayed");
	}
	
	@Test(groups = { "login" }, priority=6)
	public void loginPwCheck() {
		Assert.assertTrue(acct.loginPwFieldDisplayed(), "Login pw field is not displayed");
	}
	
	@Test(groups = { "login" }, priority=9)
	public void loginButtonCheck() {
		Assert.assertTrue(acct.loginButtonDisplayed(), "Login button is not displayed");
	}
	
	@Test(groups = { "login" }, priority=12)
	public void login() {
		acct.fillLoginForm("luke.fitzgerald@blueacorn.com", "pass4luke");
		acct.clickLoginButton();
		Assert.assertTrue(acct.acctDashboardDisplayed(), "Account dashboard is not displayed");
	}
	
	@Test(groups = { "login" }, priority=15) 
	public void testLoginGreeting() throws InterruptedException {
		Assert.assertEquals(acct.getDashGreeting(), "Hello, Luke Fitzgerald!");
	}	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
