package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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
	By shippingMethodContinueButton;
	By shippingMethodForm;
	By paymentMethodForm;
	By ccPaymentOptionLabel;
	By ccForm;
	By ccNum;
	By ccType;
	By ccExpMonth;
	By ccExpYear;
	By ccVeriNum;
	By paymentMethodContinueButton;
	By submitOrderButton;
	
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
		shippingMethodContinueButton = By.cssSelector(Global.getShippingMethodContinueButton());
		shippingMethodForm = By.cssSelector(Global.getShippingMethodsForm());
		paymentMethodForm = By.cssSelector(Global.getPaymentMethodsForm());
		ccPaymentOptionLabel = By.cssSelector(Global.getCCPaymentOptionLabel());
		ccForm = By.cssSelector(Global.getCCForm());
		ccNum = By.cssSelector(Global.getCCNum());
		ccType = By.cssSelector(Global.getCCType());
		ccExpMonth = By.cssSelector(Global.getCCExpMonth());
		ccExpYear = By.cssSelector(Global.getCCExpYear());
		ccVeriNum = By.cssSelector(Global.getCCVeriNum());
		paymentMethodContinueButton = By.cssSelector(Global.getPaymentMethodContinueButton());
		submitOrderButton = By.cssSelector(Global.getSubmitOrderButton());
		
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
		Select billingState = new Select(driver.findElement(billingStateSelect));
		
		type("Luke", billingFName);
		type("Fitzgerald", billingLName);
		type("luke.fitzgerald@blueacorn.com", billingEmail);
		type("145 Williman St", billingAddress1);
		type("145 Williman St", billingAddress2);
		billingState.selectByIndex(54);
		type("Charleston", billingCity);
		type("29403", billingZip);
		type("8779442583", billingPhone);
	}
	
	public void clickBillingContinueButton() {
		click(billingContinueButton);
	}
	
	public Boolean shippingMethodFormDisplayed() {
		return waitForIsDisplayed(shippingMethodForm, 10);
	}
	
	public void clickShippingMethodContinueButton() {
		click(shippingMethodContinueButton);
	}
	
	public Boolean paymentMethodFormDisplayed() {
		return waitForIsDisplayed(paymentMethodForm, 10);
	}
	
	public Boolean ccPaymentOptionDisplayed() {
		return waitForIsDisplayed(ccPaymentOptionLabel, 10);
	}
	
	public void clickCCPaymentOptionLabel() {
		click(ccPaymentOptionLabel);
	}
	
	public Boolean ccFormDisplayed() {
		return waitForIsDisplayed(ccForm);
	}
	
	public void fillBetterCCForm() {
		Select ccExpYearSelect = new Select(driver.findElement(ccExpYear));
		Select ccExpMonthSelect = new Select(driver.findElement(ccExpMonth));
		
		type("6011222233334444", ccNum);
		ccExpYearSelect.selectByIndex(2);
		ccExpMonthSelect.selectByIndex(2);
		
	}
	
	public void fillCCForm() {
		Select ccTypeSelect = new Select(driver.findElement(ccType));
		Select ccExpYearSelect = new Select(driver.findElement(ccExpYear));
		Select ccExpMonthSelect = new Select(driver.findElement(ccExpMonth));
		
		type("6011222233334444", ccNum);
		type("123", ccVeriNum);
		
		ccTypeSelect.selectByIndex(4);
		ccExpYearSelect.selectByIndex(2);
		ccExpMonthSelect.selectByIndex(2);
	}
	
	public void clickPaymentMethodContinueButton() {
		click(paymentMethodContinueButton);
	}
	
	public Boolean paymentMethodContinueButtonDisplayed() {
		return waitForIsDisplayed(paymentMethodContinueButton, 10);
	}
	
	public Boolean submitOrderButtonDisplayed() {
		return waitForIsDisplayed(submitOrderButton, 10);
	}
}
