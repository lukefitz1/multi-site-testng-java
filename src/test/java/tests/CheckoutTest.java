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

import pos.Header;
import pos.Product;
import pos.Checkout;
import pos.Cart;

public class CheckoutTest extends Base {

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private Product prod;
	private Header header;
	private Checkout checkout;
	private Cart cart;
	
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
	public void goToTestProductPage() {
		header = new Header(driver);
		prod = new Product(driver);
		prod.goToSimpleProductPage();
	}
	
	@Test(groups = { "functional" }, priority=1)
	public void headerCheck() {
		Assert.assertTrue(header.headerDisplayed(), "Header is displayed");
	}
	
	@Test(groups = { "functional" }, priority=2)
	public void addToCartButtonCheck() {
		Assert.assertTrue(prod.simpleAddToCartButtonDisplayed(), "Simple add to cart displayed");
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void addProductToCart() {
		prod.clickSimpleAddToCartButton();
		Assert.assertTrue(header.miniCartDisplayed(), "Mini cart is displayed");
	}
	
	@Test(groups = { "functional" }, priority=4)
	public void assertMiniCartCheckoutButtonDisplayed() {
		Assert.assertTrue(header.miniCartCheckoutButtonDisplayed(), "Mini cart checkout button is displayed");
	}
	
	@Test(groups = { "functional" }, priority=5)
	public void goToCheckout1() {
		header.clickMiniCartCheckoutButton();
		checkout = new Checkout(driver);
		cart = new Cart(driver);
		Assert.assertTrue(header.headerDisplayed(), "Header is displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void cartPageButtonCheck() {
		if (Global.getDirectToCheckout()) {
			
		}
		else {
			Assert.assertTrue(cart.cartPageCheckoutButtonDisplayed(), "Cart page button is displayed");
		}
	}
	
	@Test(groups = { "functional" }, priority=7)
	public void urlCheck() {
		if (Global.getDirectToCheckout()) {	
			//System.out.println(driver.getCurrentUrl());
			//System.out.println(checkout.getBaseUrl() + checkout.getCheckoutUrl());
			//Assert.assertEquals(driver.getCurrentUrl(), checkout.getBaseUrl() + checkout.getCheckoutUrl());
		}
		else {		
			//System.out.println(driver.getCurrentUrl());
			//System.out.println(cart.getBaseUrl() + cart.getCartUrl());
			//Assert.assertEquals(driver.getCurrentUrl(), cart.getBaseUrl() + cart.getCartUrl());
		}
	}
	
	@Test(groups = { "functional" }, priority=8)
	public void goToCheckout2() {
		if (Global.getDirectToCheckout()) {	
			
		}
		else {		
			cart.clickCartPageCheckoutButton();
			Assert.assertTrue(checkout.checkoutPageTitleDisplayed(), "Checkout page title is displayed");
		}
	}
	
	@Test(groups = { "functional" }, priority=9)
	public void selectCheckoutMethod() {
		checkout.clickContinueAsGuestButton();
		Assert.assertTrue(checkout.billingFormDisplayed(), "Biling form is displayed");
	}
	
	@Test(groups = { "functional" }, priority=10)
	public void fillOutBillingForm() {
		checkout.fillBillingForm();
		checkout.clickBillingContinueButton();
	}
	
	@Test(groups = { "functional" }, priority=11)
	public void shippingMethodsDisplayed() {
		Assert.assertTrue(checkout.shippingMethodFormDisplayed(), "Shipping methods form is displayed");
		//checkout.clickShippingMethodContinueButton();
	}
	
	@Test(groups = { "functional" }, priority=12)
	public void clickShippingMethodContinueButton() {
		checkout.clickShippingMethodContinueButton();
	}
	
	@Test(groups = { "functional" }, priority=13)
	public void ccPaymentOptionDisplayed() {
		Assert.assertTrue(checkout.ccPaymentOptionDisplayed(), "CC payment method is displayed");
		//checkout.clickCCPaymentOption();
	}
	
	@Test(groups = { "functional" }, priority=14)
	public void selectCCPaymentOption() {		
		checkout.clickCCPaymentOptionLabel();
	}
	
	/*@Test(groups = { "functional" }, priority=14)
	public void ccFormDisplayed() {		
		Assert.assertTrue(checkout.paymentMethodFormDisplayed(), "Payment form displayed");
	}
	
	@Test(groups = { "functional" }, priority=15)
	public void fillCCForm() {		
		
		if (Global.getBetterCCForm()) {
			checkout.fillBetterCCForm();
		}
		else {
			checkout.fillCCForm();
		}
		
	}
	
	@Test(groups = { "functional" }, priority=16)
	public void clickPaymentMethodContinueButton() {
		Assert.assertTrue(checkout.paymentMethodContinueButtonDisplayed(), "Payment method continue button displayed");
		checkout.clickPaymentMethodContinueButton();
	}
	
	@Test(groups = { "functional" }, priority=17)
	public void submitOrderButtonDisplayed() {
		Assert.assertTrue(checkout.submitOrderButtonDisplayed(), "Submit order button displayed");
	}*/
	
	@Test(groups = { "functional" }, priority=18)
	public void waitUp() throws InterruptedException {
		//Thread.sleep(7000);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {	
		driver.quit();
	}
}
