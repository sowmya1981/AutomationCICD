package rahulShettyAcademy.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;
import rahulShettyAcademy.PageObjects.CartPage;
import rahulShettyAcademy.PageObjects.CheckoutPage;
import rahulShettyAcademy.PageObjects.ConfirmationPage;
import rahulShettyAcademy.PageObjects.LandingPage;
import rahulShettyAcademy.PageObjects.OrdersPage;
import rahulShettyAcademy.PageObjects.ProductCatalogue;
import rahulShettyAcademy.TestComponents.BaseTests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest_PageObjectModel extends BaseTests {

String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = AbstractComponent.goToCartPage();
		Thread.sleep(3000);
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		

	}

	

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rahulShettyAcademy//data//purchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

		/*
		 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
		 * "depurusowmya@gmail.com"); map.put("password", "Sairam81");
		 * map.put("productName","ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
		 * "anshika@gmail.com"); map1.put("password", "Iamking@000");
		 * map1.put("productName","ADIDAS ORIGINAL");
		 * 
		 * return new Object[][] {{map}, {map1}};
		 */

//		    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };

	}
}
