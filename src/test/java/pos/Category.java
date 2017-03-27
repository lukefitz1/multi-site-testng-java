package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import tests.Global;

public class Category extends Base {

	String testCategoryAPage;
	String testCategoryBPage;
	By productGrid = By.cssSelector("#amshopby-page-container > div.category-products > ul");
	By showSelect = By.cssSelector("#amshopby-page-container > div.category-products > div.toolbar > div.pager > div > div > div > select");
	By selectedShowOption = By.cssSelector("#amshopby-page-container > div.category-products > div.toolbar > div.pager > div > div > div > div > span > span.ba-shiv-content");
	By products = By.cssSelector("#amshopby-page-container > div.category-products > ul > li");
	By showOptions = By.cssSelector("#amshopby-page-container > div.category-products > div.toolbar > div.pager > div > div > div > select > option");
	By overlay = By.cssSelector("#amshopby-page-container > div.amshopby-overlay");

	public Category(WebDriver driver) {
		super(driver);
		getCatAUrl();
		getCatBUrl();	
	}
	
	private void getCatAUrl() {
		testCategoryAPage =  Global.getCatAUrl();
	}
	
	private void getCatBUrl() {
		testCategoryBPage =  Global.getCatBUrl();
	}
	
	public void goToTestCategoryPage() {
		visit(testCategoryBPage);
	}
	
	public By getProductGrid() {
		return productGrid;
	}
	
	public Boolean productGridDisplayed() {
		return waitForIsDisplayed(productGrid);
	}
	
	public By getShowSelect() {
		return showSelect;
	}
	
	public Boolean showSelectDisplayed() {
		return waitForIsDisplayed(showSelect);
	}
	
	public String getSelectedShowOption() {
		return getText(selectedShowOption).trim();
	}
	
	public int countProducts() {
		return count(products);
	}
	
	public int countShowOptions() {
		return count(showOptions);
	}
	
	public void changeShowOption(int optionIndex) {
		Select showSelectList = new Select(driver.findElement(showSelect));
		showSelectList.selectByIndex(optionIndex);
	}
	
	public void waitForOverlayNotDisplayed() {
		waitForIsNotDisplayed(overlay, 10);
	} 
	
}
