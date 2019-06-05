package com.qa.testcases;



import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.ManageBookings;
import com.qa.wrappermethod.Wrappermethods;


public class HomePageTest extends TestBase{

	HomePage homepage;
	ManageBookings booking;
	Wrappermethods methods;
	static String sheetName="TripDetails";

	
	public HomePageTest()
	{
		super();
		
	}
	
	
	
	@BeforeMethod
	public void SetUp()
	{
		intilization();
		homepage = new HomePage();
		methods = new Wrappermethods();
		
	}
	
	
	@Test(priority=1, groups="Br-01", enabled=false)
	public void PageTitle()
	{
		String title = homepage.ValidateHomePageTitle();
		Assert.assertEquals(title, "Book Bus Travels, AC Volvo Bus, Hotels & Bus Hire - redBus", "Title Not Matched");
	}
	
	
	@Test(priority=1, groups="Br-02")
	public void TripDetails() throws InterruptedException
	{
		
		homepage.enterSource("Mumbai");
		Thread.sleep(2000);
		homepage.enterDestination("Bangalore");
		Thread.sleep(2000);

		homepage.selectOnwardDate("31-May 2019");
		Thread.sleep(3000);
		booking=homepage.clickSearch();
		
	}
	
	
	
}
			
	

	

