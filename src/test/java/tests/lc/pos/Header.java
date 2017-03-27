package tests.lc.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pos.Base;

public class Header extends Base {

	By headerObj = By.cssSelector("");
	
	public Header(WebDriver driver) {
		super(driver);
	}
	
	public By getHeaderObj() {
		return headerObj;
	}
	
	public Boolean headerObjDisplayed() {
		return waitForIsDisplayed(headerObj, 10);
	}
}
