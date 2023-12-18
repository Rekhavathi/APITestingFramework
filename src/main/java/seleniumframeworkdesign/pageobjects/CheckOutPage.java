package seleniumframeworkdesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumframeworkdesign.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = 	driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions actions = new Actions(driver);
		actions.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
}
