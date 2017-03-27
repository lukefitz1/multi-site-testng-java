package tests.aaa.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Base;
import tests.Global;

public class Checkout extends Base {

	String checkoutUrl;
	By continueAsGuestButton;
	By checkoutPageTitle;
	By billingForm;
	By billingFName;
	By billingLName;
	By billingEmail;
	By billingAddress1;
	By billingAddress2;
	By billingCity;
	By billingStateSelect;
	By billingZip;
	By billingPhone;
	By billingContinueButton;
	
	public Checkout(WebDriver driver) {
		super(driver);
		
		checkoutUrl = Global.getCheckoutUrl();
		continueAsGuestButton = By.cssSelector(Global.getContinueAsGuestButton());
		checkoutPageTitle = By.cssSelector(Global.getCheckoutPageTitle());
		billingForm = By.cssSelector(Global.getBillingForm());
		billingFName = By.cssSelector(Global.getBillingFName());
		billingLName = By.cssSelector(Global.getBillingLName());
		billingEmail = By.cssSelector(Global.getBillingEmail());
		billingAddress1 = By.cssSelector(Global.getBillingAddress1());
		billingAddress2 = By.cssSelector(Global.getBillingAddress2());
		billingCity = By.cssSelector(Global.getBillingCity());
		billingStateSelect = By.cssSelector(Global.getBillingStateSelect());
		billingZip = By.cssSelector(Global.getBillingZip());
		billingPhone = By.cssSelector(Global.getBillingPhone());
		billingContinueButton = By.cssSelector(Global.getBillingContinueButton());
	}
	
	public void goToCheckout() {
		visit(checkoutUrl);
	}
	
	public String getCheckoutUrl() {
		return checkoutUrl;
	}
	
	public Boolean checkoutPageTitleDisplayed() {
		return waitForIsDisplayed(checkoutPageTitle);
	}
	
	public void clickContinueAsGuestButton() {
		click(continueAsGuestButton);
	}
	
	public Boolean billingFormDisplayed() {
		return waitForIsDisplayed(billingForm);
	}
	
	public void fillBillingForm() {
		type("Luke", billingFName);
		type("Fitzgerald", billingLName);
		type("luke.fitzgerald@blueacorn.com", billingEmail);
		type("8779442583", billingPhone);
		//type("145 Williman St", billingAddress1);
		type("Charleston", billingCity);
		type("29403", billingZip);
	}
	
	public void clickBillingContinueButton() {
		click(billingContinueButton);
	}

}
