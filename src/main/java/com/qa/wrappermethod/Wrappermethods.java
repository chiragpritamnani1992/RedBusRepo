package com.qa.wrappermethod;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.emory.mathcs.backport.java.util.concurrent.TimeoutException;

public class Wrappermethods {

	public static WebDriver driver;
	static Properties prop;
	public static Workbook book= null;
	public static Sheet  sheet= null;
	public static String TestSheetPath= "../RedBus_Assignment/src/main/java/com/qa/testdata/redbus_datasheet.xlsx";
			
	

	
	public static Object[][] getTestData(String sheetName)
	{
		
		FileInputStream file= null;
		
		
		try {
			file = new FileInputStream(TestSheetPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sheet=book.getSheet(sheetName);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum(); i++){
			for (int k=0; k<sheet.getRow(0).getLastCellNum();k++){
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
			}
			
		}
		return data;
		
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String currentDir = System.getProperty("user.dir");
	FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
}

	public void ScrollToExpectedElement(WebDriver screenName,String xpath){

		WebElement elem = screenName.findElement(By.xpath(xpath));
		JavascriptExecutor exec = (JavascriptExecutor)screenName;
		exec.executeScript("arguments[0].scrollIntoView();", elem);
	}
	
	public void selectDatebyJs(WebDriver driver,WebElement element, String dateval )
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].setAttribute('value','"+dateval+"');", element);
	}
	
	
	
	
	public void waitforElementClickable(String element) throws TimeoutException
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		} catch (NoSuchElementException e) {
			
			e.printStackTrace();
		} catch (NoSuchWindowException te){
			te.printStackTrace();
		}
	}
	
	public void cikcByJS(WebElement ElementName)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", ElementName);
	}
	
	
	public void selectDate(String day) throws InterruptedException
	
	{
		
		
		driver.findElement(By.xpath("//span[@class='fl icon-calendar_icon-new icon-onward-calendar icon']")).click();
	/*	List<WebElement> elements = driver.findElements(By.xpath("//div[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[2]"));
		
		for (int i=0; i<elements.size();i++)
		{
			System.out.println(elements.get(i).getText());
			//Selecting the month
			if(elements.get(i).getText().equals(monthYear))
			{	*/
				String beforeXpath="//*[@id='rb-calendar_onward_cal']/table/tbody/tr[";
				String afterXpath="]/td[";	
		
				final int TotalNoofWeeks=7;
				boolean flag=false;
		
				String dayVal=null;

					for(int rowNum=3;rowNum<=7;rowNum++)
						{
							for(int colNum=1;colNum<=TotalNoofWeeks;colNum++)
								{
				
									try {
					
											dayVal = driver.findElement(By.xpath(beforeXpath+rowNum+afterXpath+colNum+"]")).getText();
								
										}
									catch (NoSuchElementException e) 
										{
											System.out.println("Pelase enter correct value");
											flag=false;
											break;
										}
				
				System.out.println(dayVal);
				
				if(dayVal.equals(day))
				{
					driver.findElement(By.xpath(beforeXpath+rowNum+afterXpath+colNum+"]")).click();
					flag=true;
					break;
					
				}
				if(flag)
				{
					break;
				}
			
				
							}
		
						}
	
			
			}

		
	
	
	/*	}
		driver.findElement(By.xpath(".//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[3]/button")).click();
		selectDate(monthYear,day);*/
}
	

