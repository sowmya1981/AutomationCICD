package rahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		
			
			super(driver); //this sends driver to parent class 'AbstractComponent'
			this.driver = driver; //Initialization of driver
			PageFactory.initElements(driver, this);
		}	
	
	
	@FindBy(css="tr td:nth-child(3")
	static
	List <WebElement>  productNames;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	
	

		
	}

