package rahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	// create a constructor
	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;// // Initialization
		PageFactory.initElements(driver, this);
	}

// find products by catalogue and assign to list of webelements
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.id("toast-container");
	
	

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;

	}

	public WebElement getProductByName(String productName)

	{

		WebElement prod = getProductList().stream().filter(product ->

		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		return prod;

	}

	public void addProductToCart(String productName)throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissapear(spinner);


		
	}

}
