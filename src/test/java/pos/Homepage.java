package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.Global;

public class Homepage extends Base {

	String homepageString = "";
	Boolean homepageModal;
	By homepageStringSelector;
	By popupOverlay;
	By closeModal;
	
	public Homepage(WebDriver driver) {
		super(driver);
		
		homepageString = Global.getHomepageString();
		homepageStringSelector = By.cssSelector(Global.getHomepageStringSelector());
		homepageModal = Global.getHomepageModal();
		popupOverlay = By.cssSelector(Global.getPopupOverlay());
		closeModal = By.cssSelector(Global.getCloseModal());
	}
	
	public void goToHomepage() {
		visit("");
	}
	
	public Boolean homepageStringDisplayed() {
		return waitForIsDisplayed(homepageStringSelector);
	}
	
	public String getHomepageStringText() {
		return getText(homepageStringSelector);
	}
	
	public String getHomepageString() {
		return homepageString;
	}
	
	public Boolean getHomepageModal() {
		return homepageModal;
	}
	
	public Boolean popupOverlayDisplayed() {
		return waitForIsDisplayed(popupOverlay, 10);
	}
	
	public Boolean closeModalDisplayed() {
		return waitForIsDisplayed(closeModal, 10);
	}
	
	public void clickCloseModal() {
		click(closeModal);
	}

}
