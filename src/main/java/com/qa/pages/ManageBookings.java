package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
/*import org.openqa.selenium.JavascriptExecutor;*/
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;*/
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.qa.base.TestBase;
import com.qa.wrappermethod.Wrappermethods;

import edu.emory.mathcs.backport.java.util.concurrent.TimeoutException;

public class ManageBookings extends TestBase {
	Wrappermethods methods = new Wrappermethods();
	
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
		methods.waitforElementClickable(type);
		type.click();
	}
	
	public void sortByFare() throws TimeoutException
	{
		//methods.waitforElementClickable(sort);
		sort.click();
	}

	public void viewSeats(String datasheet) throws InterruptedException
	{
		
		int i=0;

	       List<WebElement> ele=driver.findElements(By.xpath("//div[@class='button view-seats fr' and contains(.,'View Seats')]/preceding::div[@class='travels lh-24 f-bold d-color']"));
	       for(WebElement e:ele)

	       {

	              if(e.getText().contains(datasheet))

	              {

	                     driver.findElement(By.xpath("(//div[@class='button view-seats fr' and contains(.,'View Seats')])["+i+"]")).click();
	                     break;

	              }

	              i++;

	       }
		
		/*WebElement seats = driver.findElements(By.xpath("//div[text()='View Seats']")).get(1);
		
		WebElement element = driver.findElement(By.xpath("//*[@id='12210989']/div/div[2]/div[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", seats);*/
		
	}
	
	public void clickSeats() throws FindFailed, InterruptedException
	{
		Screen sc = new Screen();
		Pattern imag1 = new Pattern("C:\\Users\\home\\workspace\\RedBus_Assignment\\src\\main\\java\\com\\qa\\img\\seat.png");
		sc.click(imag1);
		Thread.sleep(2000);
		Pattern imag2 = new Pattern("C:\\Users\\home\\workspace\\RedBus_Assignment\\src\\main\\java\\com\\qa\\img\\seat_2.png");
		sc.click(imag2);
	}
	
	
	
	public void boardingPoint()
	{
		//bPoint.click();
		Wrappermethods.cikcByJS(bPoint);
		
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
