package com.qa.pages;



import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;


public class HomePage extends TestBase {
	
	Logger log = Logger.getLogger(HomePage.class);
	
	@FindBy(xpath="//input[@id='src']")
	WebElement source;

	@FindBy(xpath="//input[@id='dest']")
	WebElement destination;


	@FindBy(xpath="//input[@id='onward_cal']")
	WebElement onwardDate;
	
	
	@FindBy(xpath="//button[@id='search_btn']")
	WebElement searchBtn;
	
	@FindBy(xpath="//*[@id='search']/div/div[1]/div/ul/li[1]")
	WebElement sourceClick;
	
	@FindBy(xpath="//*[@id='search']/div/div[2]/div/ul/li[1]")
	WebElement destClick;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String ValidateHomePageTitle()
	{
		return driver.getTitle();
	}
	
	
	
	public ManageBookings enterDetails(String src, String dest, String dt) throws InterruptedException
	{
		source.sendKeys(src);
		log.info("Souce Entered:"+ " "+ src);
		sourceClick.click();
		
		
		Thread.sleep(2000);
		destination.sendKeys(dest);
		log.info("Destination Entered:"+ " "+ dest);
		Thread.sleep(2000);
		destClick.click();
		
		String date = dt;
		String split[] = date.split("-");
		String month_year=split[1];
		String day = split[0];
		selectDate(month_year,day);
		log.info("Onward date selected :"+ " "+ dt);
		Thread.sleep(2000);
		searchBtn.click();
		log.info("Clicked on search button");
		return new ManageBookings();
	}
	
	
	
	
	public void enterSource(String src) throws InterruptedException
	{
		source.sendKeys(src);
		Thread.sleep(2000);
		sourceClick.click();
		
		
	}
	public void enterDestination(String dest) throws InterruptedException 
	{
		destination.sendKeys(dest);
		Thread.sleep(2000);
		destClick.click();
	}
	
	public void selectOnwardDate(String dt) throws InterruptedException
	{
		
		String date = dt;
		String split[] = date.split("-");
		String month_year=split[1];
		String day = split[0];
		
		selectDate(month_year,day);

	}
	public ManageBookings clickSearch()
	{
		searchBtn.click();
		
		return new ManageBookings();
	}
	
	public void selectDate(String monthYear,String day) throws InterruptedException
	
	{
	
		List<WebElement> months =driver.findElements(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[2]"));
		for(int i=0;i<months.size();i++)

		{
			if(months.get(i).getText().equals(monthYear))
			{


				String beforeXpath = "//*[@id='rb-calendar_onward_cal']/table/tbody/tr[";
				String afterXpath = "]/td[";

				final int TotalNoofWeeks = 7;
				boolean flag = false;

				String dayVal = null;
				for (int rowNum = 3; rowNum <= 7; rowNum++)
				{
					for (int colNum = 1; colNum <= TotalNoofWeeks; colNum++)
					{

						try {

							dayVal = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();

						} catch (NoSuchElementException e) {
							System.out.println("Pelase enter correct value");
							flag = false;
							break;
						}

						if (dayVal.equals(day)) {
							driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
							flag = true;
							break;

						}
						if (flag) {
							break;
						}
						
					}
					
				}
				
			}
			
			else
			{
				Thread.sleep(3000);
				driver.findElement(By.xpath("//td[@class='next']//following::button[2]")).click();
				selectDate(monthYear,day);
				
			}
			


		}

	}
	
	
	
	
}






/*List<WebElement> months =driver.findElements(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[2]"));
for(int i=0;i<months.size();i++)

{
	if(months.get(i).getText().equals(monthYear))
	{*/
/*Thread.sleep(3000);
driver.findElement(By.xpath("//td[@class='next']//following::button[2]")).click();
Thread.sleep(3000);
selectDate(monthYear,day);*/