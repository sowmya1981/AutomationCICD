package rahulShettyAcademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.CheckoutPage;
import rahulShettyAcademy.PageObjects.ConfirmationPage;
import rahulShettyAcademy.PageObjects.LandingPage;
import rahulShettyAcademy.PageObjects.ProductCatalogue;
import rahulShettyAcademy.TestComponents.BaseTests;

public class StepDefinitionsImpl extends BaseTests{


	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	
	@Given("I landed on Ecommerce Page")
	
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}

	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	
	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("Checkout {string} and submit the order")
	public void checkout_submit_order(String productName)
	{
		rahulShettyAcademy.PageObjects.CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		rahulShettyAcademy.PageObjects.CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.geterrorMessage());
    	driver.close();
    }	
}