package com.qa.wrappermethod;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {
	static Logger log = Logger.getLogger(WebEventListener.class);

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		log.info("Before navigating to:'"+ url + "'");

		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("After navigating to:'"+ url + "'");
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		log.info("Naviagted back to prevoius page");
		
	}

	public void afterNavigateBack(WebDriver driver) {
		log.info("Naviagted back to prevoius page");
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		log.info("Naviagted forward to next page");
		
	}

	public void afterNavigateForward(WebDriver driver) {
		log.info("Naviagted forward to next page");
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Trying to find Element By : " + by.toString());
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		log.info("Found Element By : " + " " + by.toString());
		
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.info("Trying to click on :" + element.toString());
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on :" + element.toString());
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info("Vaue of the:"+ element.toString()
		+ "before any change made");		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		log.info("Element value changed to:"+ element.toString());
		
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		log.info("Exception Occured:" + throwable);
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement element, WebDriver driver) {
		log.info("Vaue before get text:"+ element.toString());	
	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {
		log.info("Vaue after get text:"+ element.toString());
		
	}

}
