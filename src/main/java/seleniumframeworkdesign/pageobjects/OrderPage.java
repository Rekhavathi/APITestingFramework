package seleniumframeworkdesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworkdesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = 	driver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
		
	

	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(cardProduct->cardProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	

	
	
}
