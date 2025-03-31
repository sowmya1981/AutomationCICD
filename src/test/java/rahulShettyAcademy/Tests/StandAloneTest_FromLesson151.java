package rahulShettyAcademy.Tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.PageObjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest_FromLesson151 {

	static String productName = "ZARA COAT 3";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);

		// Enter email address
		driver.findElement(By.id("userEmail")).sendKeys("depurusowmya@gmail.com");

		// enter password
		driver.findElement(By.id("userPassword")).sendKeys("Sairam81");

		// Login
		driver.findElement(By.xpath("//input[@class='btn btn-block login-btn']")).click();

		// web driver wait to retrieve the items with css selecter mb-3 ie., all items
		// loading
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// Get List of items and store in a webelement
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// using streams - GET PRODUCT NAMES
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		// using prod webelemet we can directly add - Click add to cart button
		prod.findElement(By.cssSelector(".card-body button:last-child")).click();

		// toast-container//class=toast-bottom-right toast-container
		// WebDriver wait to catch the success message after product added to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// checking animation is no longer displaying
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.cssSelector("[routerlink *='cart']")).click();

		// verify zara coat is correct in cart section
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		// Checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// Next page after checkout. using Actions to enter data
		Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();


		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));


		    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		    
		    //Added this part because the place order is not visible, so we used javascript executor
		    WebElement placeOrder =  driver.findElement(By.cssSelector(".action__submit"));

		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    js.executeScript("arguments[0].click();", placeOrder);



		    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		   // driver.close();
	}

}
