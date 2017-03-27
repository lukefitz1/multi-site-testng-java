package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pos.Homepage;
import pos.Header;

public class SearchTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Homepage hp;
	private Header header;
	private String searchTerm;
	JavascriptExecutor js;
	long loadEventEnd;
	long navigationStart;
	ArrayList<String> ar;
	int arSize;
	Random random;
	int randomNum;
	
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
		header = new Header(driver);
		hp.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=1)
	public void loadSearchTermsFile() {
		ar = new ArrayList<String>();
		try {
            BufferedReader in = new BufferedReader(new FileReader("./resources/" + client + "_searchterms.txt"));
            String str;
            String[] term;
            str = in.readLine();
            while ((str = in.readLine()) != null) {
            	term = str.split(",");
            	ar.add(term[0]);
      
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
		arSize = ar.size();
		random = new Random();
		randomNum = random.nextInt(arSize) + 1;
	}
	
	@Test(groups = { "functional" }, priority=2)
	public void getRandomSearchTerm() {
		searchTerm = ar.get(randomNum);
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header displayed");
	}
	
	@Test(groups = { "functional" }, priority=4)
	public void searchCheck() {
		Assert.assertTrue(header.searchDisplayed(), "Search bar displayed");
	}
	
	@Test(groups = { "functional" }, priority=5)
	public void searchFor() throws InterruptedException {
		header.searchFor(searchTerm);
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void assertSearchPageLoads1() {
		Assert.assertTrue(header.headerDisplayed(), "Header displayed");
	}
	
	@Test(groups = { "functional" }, priority=7)
	public void assertSearchPageLoads2() {
		Assert.assertTrue(header.searchDisplayed(), "Search bar displayed");
	}
	
	@Test(groups = { "speed-test" }, priority=8)
	public void echoPageLoadSpeed() {
		js = (JavascriptExecutor) driver;
		loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
		navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
		
		System.out.println("Load event end: " + loadEventEnd);
		System.out.println("Navigation start: " + navigationStart);
		long searchLoadTime = (loadEventEnd - navigationStart) / 1000;
		//System.out.println("Search page load time is " + searchLoadTime + " seconds");
		
		if (searchLoadTime > 0) {
			System.out.println("Search page load time is " + searchLoadTime + " seconds");
		}
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
	
}
