package rahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	@FindBy(css = ".totalRow button")
	static WebElement checkoutele;

	@FindBy(css = "button[class='btn btn-danger']")
	private List<WebElement> getDeletebuttons;

	@FindBy(css = ".ng-star-inserted >h1")
	WebElement emptyCartMessage;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;// // Initialization
		PageFactory.initElements(driver, this);
	}

	public boolean verifyProductDisplay(String productName) {
		boolean match = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout() {
		checkoutele.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}
	
	

	/*
	 * // Method to delete all items from the cart public void deleteAllItems()
	 * throws InterruptedException { List<WebElement> deleteButtons =
	 * getDeletebuttons; while (!deleteButtons.isEmpty()) { for (WebElement
	 * deleteButton : deleteButtons) { try { deleteButton.click();
	 * waitForStaleness(deleteButton); } catch (StaleElementReferenceException e) {
	 * // Handle stale element reference exception // Re-locate the elements or
	 * handle the situation accordingly
	 * System.out.println("Stale element reference: " + e.getMessage()); } }
	 * deleteButtons = getDeletebuttons; } }
	 * 
	 * // Method to check if the cart is empty public boolean isCartEmpty() { try {
	 * // WebElement emptyCartMessage = driver.findElement(By.cssSelector(".mt-3"));
	 * System.out.println("Cart Message is:" +emptyCartMessage.getText()); return
	 * emptyCartMessage.getText().equals("No Products in Your Cart !"); } catch
	 * (org.openqa.selenium.NoSuchElementException e) { // If the element is not
	 * found, assume cart is empty return true; } }
	 */

}
