package com.qa.mobile.utilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

	private static ThreadLocal<AppiumDriver<?>> driver = new ThreadLocal<AppiumDriver<?>>();
	TestUtils utils = new TestUtils();
	
	public AppiumDriver<?> getDriver() {
		return driver.get();	
	}
	
	public void setDriver(AppiumDriver<?> driver2) {
		driver.set(driver2);
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	public void initializeDriver() throws Exception {
		AppiumDriver<?> driver = null;
		
		if(driver == null) {
			try {
				  utils.log().info("initializing Appium driver");
			      driver =  new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
			      utils.log().info("Driver is initialized"+driver);
			      this.driver.set(driver);
				
			}
			catch(Exception e) {
				e.printStackTrace();
				utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
				throw e;
			}
		}
	}
	
}
