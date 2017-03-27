package tests.aaa.pos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Base;
import tests.Global;

public class Header extends Base {

	By headerObj = By.cssSelector("");
	By header = By.cssSelector("#header");
	By signInLink= By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account");
	By openSignInDropdown = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-content.ajaxlogin.skip-active");
	By loginForm = By.cssSelector("#ajax-login");
	By loginEmail = By.cssSelector("#email-login");
	By loginPw = By.cssSelector("#password-login");
	By loginButton = By.cssSelector("#ajax-login > div > div > div.window-box.last > div > button");
	By loggedInLabel = By.cssSelector("#header > div.header-outer-wrapper > div > div.skip-links > div > a.skip-link.skip-account > span.logged-in-label");
	By mobileLoggedInLabel = By.cssSelector("#header-account > div.your-account-tablet-title > span.logged-in-label");
	By signOutButton = By.cssSelector("#header-account > div.links > ul > li.last > a");
	By miniCartWrapper;
	By miniCartCheckoutButton;
	
	public Header(WebDriver driver) {
		super(driver);
		
		getMiniCartWrapper();
		getMiniCartCheckoutButton();
	}
	
	private void getMiniCartWrapper() {
		miniCartWrapper = By.cssSelector(Global.getMiniCartWrapper());
	}
	
	private void getMiniCartCheckoutButton() {
		miniCartCheckoutButton = By.cssSelector(Global.getMiniCartCheckoutButton());
	}
	
	public By getHeaderObj() {
		return headerObj;
	}
	
	public Boolean headerObjDisplayed() {
		return waitForIsDisplayed(headerObj, 10);
	}
	
	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header);
	}
	
	public Boolean signInLinkDisplayed() {
		return waitForIsDisplayed(signInLink);
	}
	
	public void clickSignInLink() {
		click(signInLink);
	}
	
	public Boolean signInDropdownOpen() {
		return waitForIsDisplayed(openSignInDropdown);
	}
	
	public Boolean loginFormDisplayed() {
		return waitForIsDisplayed(loginForm, 10);
	}
	
	public Boolean loginEmailFieldDisplayed() {
		return waitForIsDisplayed(loginEmail, 10);
	}
	
	public Boolean loginPwFieldDisplayed() {
		return waitForIsDisplayed(loginPw, 10);
	}
	
	public Boolean loginButtonDisplayed() {
		return waitForIsDisplayed(loginButton, 10);
	}
	
	public void fillLoginForm(String un, String pw) {
		type(un, loginEmail);
		type(pw, loginPw);
	}
	
	public void clickLoginButton() {
		click(loginButton);
	}
	
	public void clickLoggedInLabel() {
		click(loggedInLabel);
	}
	
	public Boolean loggedInLabelDisplayed() {
		return waitForIsDisplayed(loggedInLabel, 10);
	}
	
	public Boolean signOutButtonDisplayed() {
		return waitForIsDisplayed(signOutButton, 10);
	}
	
	public Boolean mobileLoggedInLabelDisplayed() {
		return waitForIsDisplayed(mobileLoggedInLabel, 10);
	}
	
	public Boolean miniCartDisplayed() {
		return waitForIsDisplayed(miniCartWrapper);
	}
	
	public Boolean miniCartCheckoutButtonDisplayed() {
		return waitForIsDisplayed(miniCartCheckoutButton);
	}
	
	public void clickMiniCartCheckoutButton() {
		click(miniCartCheckoutButton);
	}

}
