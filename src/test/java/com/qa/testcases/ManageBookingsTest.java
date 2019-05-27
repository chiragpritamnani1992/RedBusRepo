package com.qa.testcases;



import java.util.Properties;


import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.ManageBookings;
import com.qa.wrappermethod.Wrappermethods;

import edu.emory.mathcs.backport.java.util.concurrent.TimeoutException;


public class ManageBookingsTest  extends TestBase{

	ManageBookings booking;
	HomePage homepage;
	static Wrappermethods methods;
	static Properties prop;
	static String sheetName="TripDetails";
	static String sheetName_1="TicketDetails";
			
	
	public ManageBookingsTest()
	{
		super();
		
	}
	
	@BeforeClass()
	public void SetUp() throws InterruptedException
	{
		intilization();
		methods = new Wrappermethods();
		homepage = new HomePage();
		
	}
	
	@DataProvider
	public static Object[][] getRedBusTestData()
	{
		
		Object data[][]= Wrappermethods.getTestData(sheetName);
		
		return data;
	}
	
	@DataProvider
	public static Object[][] getRedBusTestData_1()
	{
		
		Object data[][]= Wrappermethods.getTestData(sheetName_1);
		
		return data;
	}
	
	
	
	@Test(priority=1, groups="BR_01", dataProvider="getRedBusTestData")
	public void enterLocation(String Source, String Destination, String Date) throws InterruptedException
	{
		booking= homepage.enterDetails(Source, Destination, Date);
	}
	
	@Test(priority=2, groups="BR-01")
	public void verifyTitleTest() throws InterruptedException, TimeoutException
	{
		String bookingtitle = booking.verifyBookingTitle();
		System.out.println("Title of the Booking Page" + " : " + bookingtitle);
		Assert.assertEquals(bookingtitle, "Search Bus Tickets", "Title Not Matched");
		
		
		booking.busType();
		
		booking.sortByFare();
	}
	
	
	
	@Test(priority=3,groups="BR-02",dataProvider="getRedBusTestData_1")
	public void BookingSeats(String Passenger1_Name, String Passenger1_Age,String Passenger1_Gender, 
			String Passenger2_Name, String Passenger2_Age,String Passenger2_Gender, String Passenger_Email_ID, String Passenger_PhoneNo) throws InterruptedException, FindFailed, TimeoutException
	{
		
		booking.viewSeats();
		Thread.sleep(4000);
		booking.clickSeats();
		booking.boardingPoint();
		booking.droppingPoint();
		booking.proceedtopay();
		booking.passenger1_Details(Passenger1_Name, Passenger1_Age, Passenger1_Gender);
		booking.passenger2_Details(Passenger2_Name, Passenger2_Age, Passenger2_Gender);
		booking.contactDetails(Passenger_Email_ID, Passenger_PhoneNo);
		Thread.sleep(3000);
		String title = driver.getTitle();		
		Assert.assertEquals(title, "Please pay to book your ticket on Seabird Tourists", "Title Not Matched");
		
		if(title.equals("Please pay to book your ticket on Seabird Tourists"))
		{
			System.out.println("Congratulations!! You are one step away to complete your payment process");
		}
		else
		{
			System.out.println("Something went wrong..Please try again");
		}
	
		
	}
	

	
	
	
	@AfterClass
	public void TearDown()
	{
		driver.close();
	}
	
	
	
}
