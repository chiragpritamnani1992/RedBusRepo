package com.qa.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.qa.base.TestBase;
import com.qa.wrappermethod.Wrappermethods;

import edu.emory.mathcs.backport.java.util.concurrent.TimeoutException;

public class ManageBookings extends TestBase {
	Wrappermethods methods = new Wrappermethods();
	static Logger log = Logger.getLogger(ManageBookings.class);
	
	@FindBy(xpath="//label[@class='custom-checkbox' and @for='bt_AC']")
	WebElement type;
	
	
	@FindBy(xpath="//div[@class='sort-sec clearfix onward ']//following-sibling::div[@class='w-15 fl f-12 d-color']//a[contains(text(),'Fare')]")
	WebElement sort;
	
	@FindBy(xpath="//div[@class='radio-css ']//following-sibling::div[@class='bpDpAddr-css ']//parent::span[@title='Borivali E National Park 022 24031526 022 244031527']")
	WebElement bPoint;
	
	@FindBy(xpath="//div[@class='radio-css ']//following-sibling::div[@class='bpDpAddr-css ']//parent::span[starts-with(@title,'Anandarao Circle')]")
	WebElement dPoint;
	
	
	@FindBy(xpath="//button[@class='button continue inactive']")
	WebElement proccedToPay;
	
	
	@FindBy(xpath="//input[@name='Name_0']")
	WebElement pass1_Name;
	
	@FindBy(xpath="//input[@name='Age_0']")
	WebElement pass1_Age;
	
	@FindBy(xpath="//input[@name='Gender0' and @value='Male']")
	WebElement pass1_GenderM;
	
	@FindBy(xpath="//input[@name='Gender0' and @value='Female']")
	WebElement pass1_GenderF;
	
	@FindBy(xpath="//input[@name='Name_1']")
	WebElement pass2_Name;
	
	@FindBy(xpath="//input[@name='Age_1']")
	WebElement pass2_Age;
	

	@FindBy(xpath="//input[@name='Gender1' and @value='Male']")
	WebElement pass2_GenderM;
	
	@FindBy(xpath="//input[@name='Gender1' and @value='Female']")
	WebElement pass2_GenderF;
	
	@FindBy(xpath="//input[@placeholder='Email ID']")
	WebElement email;
	
	@FindBy(xpath="//input[@placeholder='Phone']")
	WebElement phoneNo;
	
	@FindBy(xpath="//span[@class='checkmark-radio']//parent::label[@class='ins-container']//following-sibling::input[@id='insuranceNotOpted']")
	WebElement insuranceNotOpted;
	
	
	@FindBy(xpath="//input[@value='Proceed to pay']")
	WebElement ProtoPay;
	
	public ManageBookings() 
	{

		PageFactory.initElements(driver, this);
	
	}
	
	public String verifyBookingTitle()
	{		
		
		
		return driver.getTitle();
	}
	
	
	
	public void busType() throws TimeoutException
	{
		
		type.click();
		log.info("Bus Type Checkbox Selected");
		
	}
	
	public void sortByFare() throws TimeoutException
	{
	
		sort.click();
		log.info("Clicked on sort");
	}

	public void viewSeats(String busname)
	{
	/*{
		
		WebElement seats = driver.findElements(By.xpath("//div[text()='View Seats']")).get(1);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", seats);
		
	}*/

			int i=0;

	       List<WebElement> ele=driver.findElements(By.xpath("//div[@class='button view-seats fr' and contains(.,'View Seats')]/preceding::div[@class='travels lh-24 f-bold d-color']"));
	       for(WebElement e:ele)

	       {
	              if(e.getText().contains(busname))

	              {
	            	  //driver.findElement(By.xpath("//div[@class='button view-seats fr' and contains(.,'View Seats')]["+i+"]")).click();
	            		WebElement seats = driver.findElements(By.xpath("//div[text()='View Seats']")).get(i);
	            		JavascriptExecutor executor = (JavascriptExecutor)driver;
	            		executor.executeScript("arguments[0].click();", seats);
	            		break;
	              }

	             i++;
	   
	       }
	}

	
	
	public void clickSeats() throws FindFailed, InterruptedException
	{
		Screen sc = new Screen();
		Pattern imag1 = new Pattern("../RedBus_Assignment/src/main/java/com/qa/img/seat.png");
		sc.click(imag1);
		log.info("1st seat is selected");
		Thread.sleep(2000);
		Pattern imag2 = new Pattern("../RedBus_Assignment/src/main/java/com/qa/img/seat_2.png");
		sc.click(imag2);
		log.info("2nd seat is selected");
	}
	
	
	
	public void boardingPoint()
	{
		bPoint.click();		
		
	}
	
	public void droppingPoint()
	{
		dPoint.click();
	}
	
	public void proceedtopay()
	{
		proccedToPay.click();
	}
	
	public void passenger1_Details(String name, String age,String gender)
	{
		pass1_Name.sendKeys(name);
		pass1_Age.sendKeys(age);
		if(gender.equals("Male"))
		{

			pass1_GenderM.click();
			
		}
		else
		{
			pass1_GenderF.click();
		}
		
		
	}
	public void passenger2_Details(String name, String age, String gender)
	{
		pass2_Name.sendKeys(name);
		pass2_Age.sendKeys(age);
		
		if(gender.equals("Male"))
		{

			pass2_GenderM.click();
			
		}
		else
		{
			pass2_GenderF.click();
		}
		
	}
	public void contactDetails(String emailId, String pNo) throws InterruptedException
	{
		email.sendKeys(emailId);
		phoneNo.sendKeys(pNo);
		insuranceNotOpted.click();
		Thread.sleep(2000);
		ProtoPay.click();
		
		
	}
}
