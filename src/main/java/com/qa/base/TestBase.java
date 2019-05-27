package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public static WebDriver driver;
	static Properties prop;
	
	
	
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
		else
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\home\\workspace\\RedBus_Assignment"
					+ "\\src\\main\\java\\com\\qa\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
			
	}
	
	
	
	
}
