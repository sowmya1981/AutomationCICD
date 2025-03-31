package rahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.LandingPage;
import rahulShettyAcademy.PageObjects.OrdersPage;

public class AbstractComponent {

	static WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDissapear(WebElement ele) {
		// web driver wait to retrieve the items with css selecter mb-3 ie., all items

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		// web driver wait to retrieve the items with css selecter mb-3 ie., all items

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	@FindBy(css = "[routerlink *='cart']")
	static
	WebElement cartHeader;

	/// dashboard/myorders

	@FindBy(css = "[routerlink *='myorders']")
	static
	WebElement Orders;
	
	
	public static CartPage goToCartPage() {

		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

	public static OrdersPage goToOrdersPage() {
		Orders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;

	}
	
	

}
