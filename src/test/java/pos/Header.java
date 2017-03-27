package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tests.Global;

public class Header extends Base {

	String searchBarStr = "search";
	By header;
	By search;
	By searchButton = By.cssSelector("#search_mini_form > div.input-box > button");
	By miniCart = By.cssSelector("#header-cart");
	By miniCartCheckoutButton;
	By miniCartWrapper;
	
	public Header(WebDriver driver) {
		super(driver);
		
		miniCartWrapper = By.cssSelector(Global.getMiniCartWrapper());
		miniCartCheckoutButton = By.cssSelector(Global.getMiniCartCheckoutButton());
		header = By.cssSelector(Global.getHeader());
		search = By.cssSelector(Global.getSearch());
	}

	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public Boolean searchDisplayed() {
		return waitForIsDisplayed(search, 10);
	}
	
	public By getHeader() {
		//System.out.println(header);
		return header;
	}
	
	public By getSearchBar() {
		return search;
	}
	
	public void searchFor(String searchTerm) {
		type(searchTerm, search);
		WebElement e = driver.findElement(search);
		e.submit();
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
