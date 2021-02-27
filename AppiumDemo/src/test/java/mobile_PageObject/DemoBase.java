package mobile_PageObject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import com.qa.mobile.utilities.DriverManager;
import com.qa.mobile.utilities.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class DemoBase {
	
	protected AppiumDriver<?> driver;

	public DemoBase() {
		this.driver = new DriverManager().getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}

	/*
	 * <-----------Reusable methods------------------>
	 */

	@SuppressWarnings("rawtypes")
	public void cursorPosition(int x, int y) {
		TouchAction t = new TouchAction(driver);
		t.press(PointOption.point(x, y));
		t.release();
		t.perform();
	}
	
	@SuppressWarnings("rawtypes")
	public void clearText(MobileElement e) {
		 String txt;    
		 txt = getAttribute(e, "text");
		 int maxChars1 = txt.length();
	     for(int i =0 ; i < maxChars1 ; i++)
	      {
	    	((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DEL));
	      }
	}

	//Generating random mobile number
	public String generateMobileNumber()
	{
		long num = (long) (Math.random()*100000 + 7833300000L);
		String str = Long.toString(num);
		return str;	  
	}

	//Generating random name,username and lastname
	public String randomUsername(String text)
	{	
		Faker randomValue = new Faker();

		if(text.equals("name"))
		{
			String firstName =  randomValue.name().firstName();
			return firstName ; 
		}

		if(text.equals("username"))
		{
			String userName = randomValue.name().username();
			return userName ;
		}

		if(text.equals("lastname"))
		{
			String lastName = randomValue.name().lastName();
			return lastName;
		}

		return text;
	}

	public boolean contentIsPresent(MobileElement e) {
		waitForVisibility(e);
		return e.isDisplayed();
	}

	public int convertStringToInt(String str) {
		int x = Integer.parseInt(str);
		return x;
	}

	public void clear(MobileElement e) {
		waitForVisibility(e);
		e.clear();
	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void waitForInvisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.invisibilityOf(e));
	}

	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
		driver.hideKeyboard();
	}

	@SuppressWarnings("deprecation")
	public void insertValue(MobileElement e, String txt) {
		waitForVisibility(e);
		driver.getKeyboard().sendKeys(txt);
		driver.hideKeyboard();
	}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute).trim();
	}

	public String getText(MobileElement e, String msg) {
		waitForVisibility(e);
		String txt;    
		txt = getAttribute(e, "text").trim();

		return txt;
	}

	public void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) 
		{
			System.out.println("Fail to pause script");
		}
	}

	public void hideAndroidKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			System.out.println("KeyBoard is not available");
		}

	}

	public MobileElement scrollToElement(MobileElement element, String direction) throws Exception {
		pause(1);
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.width * 0.5);
		int endX = (int) (size.width * 0.5);
		int startY = 0;
		int endY = 0;
		boolean isFound = false;

		if (direction.contains("up")) {
			endY = (int) (size.height * 0.4);
			startY = (int) (size.height * 0.6);
		}
		if(direction.contains("down")) {
			endY = (int) (size.height * 0.6);
			startY = (int) (size.height * 0.4);
		}

		for (int i = 0; i < 3; i++) {
			if (find(element, 1)) {
				isFound = true;
				break;
			} else {
				swipe(startX, startY, endX, endY, 1000);
			}
		}
		if(!isFound){
			throw new Exception("Element not found");
		}
		return element;
	}


	public boolean find(final MobileElement element, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					if (element.isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public void swipe(int startX, int startY, int endX, int endY, int millis)
			throws InterruptedException {
		TouchAction t = new TouchAction(driver);
		t.press(PointOption.point(startX,startY)).waitAction().moveTo(PointOption.point(endX, endY)).release().perform();
	}


}
