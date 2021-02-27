package com.qa.mobile.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;



public class CapabilitiesManager {
	
	TestUtils utils = new TestUtils();
	final String filePath = ".//configFile_Folder/demo_config.properties";

	public DesiredCapabilities getCaps() throws Exception {
		GlobalParams params = new GlobalParams();
		Properties props = new Properties();
		FileInputStream read = new FileInputStream(filePath);
		props.load(read);

		try {
			utils.log().info("getting capabilities");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
			caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			caps.setCapability("appPackage",props.getProperty("androidAppPackage"));
			caps.setCapability("appActivity",props.getProperty("androidAppActivity"));  
			caps.setCapability("systemPort", params.getSystemPort());
			caps.setCapability("chromeDriverPort", params.getChromeDriverPort());

			File file = new File(props.getProperty("androidAppLocation"));
			utils.log().info("appUrl is" + file.getAbsolutePath());
			caps.setCapability("app", file.getAbsolutePath());

			return caps;

		}
		catch(Exception e) {
			e.printStackTrace();
			utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
			throw e;
		}
	}

}
