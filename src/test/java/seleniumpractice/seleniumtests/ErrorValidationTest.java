package seleniumpractice.seleniumtests;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframeworkdesign.pageobjects.CartPage;
import seleniumframeworkdesign.pageobjects.CheckOutPage;
import seleniumframeworkdesign.pageobjects.ConfirmationPage;
import seleniumframeworkdesign.pageobjects.LandingPage;
import seleniumframeworkdesign.pageobjects.ProductCatalog;
import seleniumtestspractice.testcomponents.ShoppingBaseTest;

public class ErrorValidationTest extends ShoppingBaseTest {
	

	
	@Test(groups = {"ErrorHandling"})
	public void invalidLoginTest() throws IOException, InterruptedException
	{
		//String productName = "ZARA COAT 3";
		landingPage.loginApplication("testing12356@gmail.com", "Testing@1234");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	
	
}
