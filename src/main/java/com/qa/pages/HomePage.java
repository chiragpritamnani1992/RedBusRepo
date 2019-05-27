package com.qa.pages;


import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;


public class HomePage extends TestBase {
	
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
		sourceClick.click();
		Thread.sleep(2000);
		destination.sendKeys(dest);
		Thread.sleep(2000);
		destClick.click();
		
		String date = dt;
		String split[] = date.split("-");
		String month_year=split[1];
		String day = split[0];
		selectDate(month_year,day);
		
		Thread.sleep(2000);
		searchBtn.click();
		
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
	
	public void selectDate(String monthYear,String day)
	{
		driver.findElement(By.xpath("//span[@class='fl icon-calendar_icon-new icon-onward-calendar icon']")).click();
		
		String beforeXpath = "//*[@id='rb-calendar_onward_cal']/table/tbody/tr[";
		String afterXpath = "]/td[";

		final int TotalNoofWeeks = 7;
		boolean flag = false;

		String dayVal = null;

		for (int rowNum = 3; rowNum <= 7; rowNum++) {
			for (int colNum = 1; colNum <= TotalNoofWeeks; colNum++) {

				try {

					dayVal = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();

				} catch (NoSuchElementException e) {
					System.out.println("Pelase enter correct value");
					flag = false;
					break;
				}

				//System.out.println(dayVal);

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

}

	