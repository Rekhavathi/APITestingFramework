package seleniumtestspractice.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframeworkdesign.pageobjects.LandingPage;
import seleniumpractice.data.DataReader;

public class ShoppingBaseTest {
	
	protected WebDriver driver;
	protected LandingPage landingPage;
	DataReader dataReader = new DataReader();
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\seleniumframeworkdesign\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browserName =  prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		
		//read json to string
		//System.out.println("path"+filePath);
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to Hashmap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){			
		});
		return data;
		
		
	}
	
	
	//List<HashMap<String, String>> dataList = dataReader.getJsonDataToMap();
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
	


}
