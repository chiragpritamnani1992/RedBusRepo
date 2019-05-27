package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.wrappermethod.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	static Properties prop;
	static EventFiringWebDriver e_driver;
	static WebEventListener eventlistener;
	
	public TestBase()
	{
		
		try {
			prop= new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\home\\workspace\\RedBus_Assignment\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void intilization()
	{
		String browsername = prop.getProperty("browser");
		
		if(browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\home\\workspace\\RedBus_Assignment"
					+ "\\src\\main\\java\\com\\qa\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browsername.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\home\\workspace\\RedBus_Assignment"
					+ "\\src\\main\\java\\com\\qa\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		eventlistener = new WebEventListener();
		e_driver.register(eventlistener);
		driver= e_driver;
		
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
			
	}
	
	
	
	
}
