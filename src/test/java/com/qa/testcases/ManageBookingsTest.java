package com.qa.testcases;



import java.util.Properties;

import org.apache.log4j.Logger;
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
	Logger log = Logger.getLogger(ManageBookingsTest.class);
	
	
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
		log.info("Location details entered");
	
	
	}
	
	@Test(priority=2, groups="BR-01")
	public void verifyTitleTest() throws InterruptedException, TimeoutException
	{
		String bookingtitle = booking.verifyBookingTitle();
		log.info("Title of the Booking Page" + " : " + bookingtitle);
		Assert.assertEquals(bookingtitle, "Search Bus Tickets", "Title Not Matched");
		
		
		booking.busType();
		
		booking.sortByFare();
	}
	
	
	
	@Test(priority=3,groups="BR-02",dataProvider="getRedBusTestData_1")
	public void BookingSeats(String Busname,String Passenger1_Name, String Passenger1_Age,String Passenger1_Gender, 
			String Passenger2_Name, String Passenger2_Age,String Passenger2_Gender, String Passenger_Email_ID, String Passenger_PhoneNo) throws InterruptedException, FindFailed, TimeoutException
	{
		
		booking.viewSeats(Busname);
		Thread.sleep(4000);
		booking.clickSeats();
		booking.boardingPoint();
		log.info("Boarding Point selected");
		booking.droppingPoint();
		log.info("Dropped Point selected");
		booking.proceedtopay();
		log.info("Clicked on proceed to pay button");
		booking.passenger1_Details(Passenger1_Name, Passenger1_Age, Passenger1_Gender);
		booking.passenger2_Details(Passenger2_Name, Passenger2_Age, Passenger2_Gender);
		booking.contactDetails(Passenger_Email_ID, Passenger_PhoneNo);
		Thread.sleep(3000);
		String title = driver.getTitle();		
		Assert.assertEquals(title, "Please pay to book your ticket on Seabird Tourists", "Title Not Matched");
		
		if(title.equals("Please pay to book your ticket on Seabird Tourists"))
		{
		
			log.info("Congratulations!! You are one step away to complete your payment process");
		}
		else
		{
			log.info("Something went wrong..Please try again");
		}
	
		
	}
	

	
	
	
	@AfterClass
	public void TearDown()
	{
		
		driver.close();
		
	}
	
	
	
}
