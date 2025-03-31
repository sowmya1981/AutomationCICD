package rahulShettyAcademy.PageObjects;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	// create a constructor
	public LandingPage(WebDriver driver) {
		
		super(driver); //this sends driver to parent class 'AbstractComponent'
		this.driver = driver; //Initialization of driver
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
	}

	
	//PAGEFACTORY method to initialize web elements on page.
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(xpath="(//input[@name='search'])[2]")
	WebElement search;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String geterrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	@FindBy(xpath = "//*[@id='products']/div[1]/div[2]/div/div/div/h5") // Adjust the selector as per your page
    WebElement productList;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[1]")
	WebElement Home;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	public void searchProduct(String text) {
        waitForElementToBeClickable(search);
        search.click();
        search.clear(); // Clear the search field before entering new text
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
        waitForSearchResults(); // Wait for search results to appear
    }
	
	public void searchProduct() {
        waitForElementToBeClickable(search);
        search.click();
        search.clear(); 
	}

    private void waitForSearchResults() {
        // Wait for the product list to be visible after performing the search
        wait.until(ExpectedConditions.visibilityOf(productList));
    }

    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void HomeLink()
    {
    	Home.click();
    }
		
}
	

