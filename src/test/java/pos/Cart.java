package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.Global;

public class Cart extends Base {

	private String cartUrl;
	By cartPageCheckoutButton;
	
	public Cart(WebDriver driver) {
		super(driver);
		setCartUrl();
		setCartPageCheckoutButton();
	}
	
	private void setCartUrl() {
		cartUrl = Global.getCartUrl();
	}
	
	private void setCartPageCheckoutButton() {
		cartPageCheckoutButton = By.cssSelector(Global.getCartPageCheckoutButton());
	}
	
	public String getCartUrl() {
		return cartUrl;
	}
	
	public Boolean cartPageCheckoutButtonDisplayed() {
		return waitForIsDisplayed(cartPageCheckoutButton);
	}
	
	public void clickCartPageCheckoutButton() {
		click(cartPageCheckoutButton);
	}

}
