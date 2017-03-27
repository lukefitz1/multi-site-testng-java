package tests.lc.pos;
import org.openqa.selenium.WebDriver;

import pos.Base;

public class Homepage extends Base {

	public Homepage(WebDriver driver) {
		super(driver);
	}
	
	public void goToHomepage() {
		visit("");
	}
}
