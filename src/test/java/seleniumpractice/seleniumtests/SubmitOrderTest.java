package seleniumpractice.seleniumtests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframeworkdesign.pageobjects.CartPage;
import seleniumframeworkdesign.pageobjects.CheckOutPage;
import seleniumframeworkdesign.pageobjects.ConfirmationPage;
import seleniumframeworkdesign.pageobjects.LandingPage;
import seleniumframeworkdesign.pageobjects.OrderPage;
import seleniumframeworkdesign.pageobjects.ProductCatalog;
import seleniumtestspractice.testcomponents.ShoppingBaseTest;

public class SubmitOrderTest extends ShoppingBaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		//String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products = productCatalog.getProductList();
		
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalog productCatalog = landingPage.loginApplication("testing123456@gmail.com", "Testing@1234");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		//Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));		
	}
	
	public String  getScreenShot(String testCaseName) throws IOException
	{
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\seleniumpractice\\data\\PurchaseOrder.json";
		System.out.println("Path"+System.getProperty("user.dir"));
		List<HashMap<String, String>> dataList = getJsonDataToMap(path);
		return new Object[][] {{dataList.get(0)},{dataList.get(1)}};
		
	}
}
