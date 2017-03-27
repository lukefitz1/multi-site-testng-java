package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.Global;

public class MyAccount extends Base {

	String acctLoginUrl = "customer/account/login/";
	String acctDashUrl = "customer/account/";
	By accountLoginForm;
	By loginEmail;
	By loginPw;
	By loginButton;
	By acctDash;
	By acctDashName;;
	
	public MyAccount(WebDriver driver) {
		super(driver);
		setLoginForm();
		setLoginEmail();
		setLoginPw();
		setLoginButton();
		setAcctDash();
		setAcctDashName();	
	}
	
	private void setLoginEmail() {
		loginEmail = By.cssSelector(Global.getLoginEmail());
	}
	
	private void setLoginPw() {
		loginPw = By.cssSelector(Global.getLoginPw());	
	}
	
	private void setLoginButton() {
		loginButton = By.cssSelector(Global.getLoginButton());
	}
	
	private void setAcctDash() {
		acctDash = By.cssSelector(Global.getAcctDash());
	}
	
	private void setAcctDashName() {
		acctDashName = By.cssSelector(Global.getAcctDashName());
	}
	
	private void setLoginForm() {
		accountLoginForm = By.cssSelector(Global.getAcctLoginForm());
	}
	
	public void goToLogin() {
		visit(acctLoginUrl);
	}
	
	public Boolean loginFormDisplayed() {
		return waitForIsDisplayed(accountLoginForm, 10);
	}
	
	public Boolean loginEmailFieldDisplayed() {
		System.out.println("Email selector: " + loginEmail);
		return waitForIsDisplayed(loginEmail, 10);
	}
	
	public Boolean loginEmailFieldExists() {
		return isPresent(loginEmail);
	}
	
	public Boolean loginPwFieldDisplayed() {
		return waitForIsDisplayed(loginPw, 10);
	}
	
	public Boolean loginButtonDisplayed() {
		return waitForIsDisplayed(loginButton, 10);
	}
	
	public Boolean acctDashboardDisplayed() {
		return waitForIsDisplayed(acctDash, 10);
	}

	public void fillLoginForm(String un, String pw) {
		type(un, loginEmail);
		type(pw, loginPw);	
	}
	
	public void clickLoginButton() {
		click(loginButton);
	}
	
	public String getAccountDashboardUrl() {
		return acctDashUrl;
	}
	
	public String getDashGreeting() {
		return getText(acctDashName);
	}
}
