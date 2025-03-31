package rahulShettyAcademy.Tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.CheckoutPage;
import rahulShettyAcademy.PageObjects.ConfirmationPage;
import rahulShettyAcademy.PageObjects.LandingPage;
import rahulShettyAcademy.PageObjects.ProductCatalogue;
import rahulShettyAcademy.TestComponents.BaseTests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ErrorValidationTest extends BaseTests {
	

	@Test(groups={"ErrorHanding"})
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("depuruwmya@gmail.com", "Sairam");
		Assert.assertEquals("Incorrect email or password.", landingPage.geterrorMessage());
	}
	
	@Test(groups={"ErrorHanding"})
	public void productErrorValidation()throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";

	ProductCatalogue productCatalogue = landingPage.loginApplication("depurusowmya@gmail.com", "Sairam81");//create and use different user 
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.getProductByName(productName);
	productCatalogue.addProductToCart(productName);
	// object for cartpage is defined in productCatalogue page-- please refer
	CartPage cartPage = productCatalogue.goToCartPage();
	Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
	}

	
}
