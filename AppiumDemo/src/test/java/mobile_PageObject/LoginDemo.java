package mobile_PageObject;

import com.qa.mobile.utilities.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginDemo extends DemoBase{
	
	public LoginDemo() {		
	}
	
	TestUtils util = new TestUtils();
	
	@AndroidFindBy (accessibility = "test-Username") 
	private MobileElement username;

	@AndroidFindBy (accessibility = "test-Password") 
	private MobileElement password;
	
	@AndroidFindBy (accessibility = "test-LOGIN") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	private MobileElement title;
	
	public LoginDemo enterUserName(String user) throws InterruptedException {	
		sendKeys(username, user);
		return this;
	}

	public LoginDemo enterPassword(String pass) {
		sendKeys(password, pass);
		return this;
	}

	public LandingPage pressLoginBtn() {
		click(loginBtn);
		return new LandingPage();
	}
	
	public String getTitle() {
		return getText(title, "title is: ");
	}

}
