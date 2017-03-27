package tests.aaa.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Base;
import tests.Global;

public class Product extends Base {

	String simpleProductUrl;
	By simpleAddToCartButton;
	
	public Product(WebDriver driver) {
		super(driver);
		getSimpleProductUrl();
		getSimpleProductAddToCartButton();
	}
	
	private void getSimpleProductUrl() {
		simpleProductUrl = Global.getSimpleProductUrl();
	}
	
	public void getSimpleProductAddToCartButton() {
		simpleAddToCartButton = By.cssSelector(Global.getSimpleProductAddToCartButton());
	}
	
	public void goToSimpleProductPage() {
		if (Global.getFileExtension()) {
			visit(simpleProductUrl, true);
		}
		else {
			visit(simpleProductUrl);
		}
		
	}

	public Boolean simpleAddToCartButtonDisplayed() {
		return waitForIsDisplayed(simpleAddToCartButton);
	}
	
	public void clickSimpleAddToCartButton() {
		click(simpleAddToCartButton);
	}

}
