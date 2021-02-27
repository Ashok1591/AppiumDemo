package mobile_StepDefinitions;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import com.qa.mobile.utilities.DriverManager;
import com.qa.mobile.utilities.GlobalParams;
import com.qa.mobile.utilities.ServerManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Base_Hook {
	@Before()
	public void initialize() throws Exception 
	{
		GlobalParams params = new GlobalParams();
		
		params.initializeGlobalParams();
		ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());
		
		new ServerManager().startServer();
		new DriverManager().initializeDriver();	
	}
	
	@After
	public void quit(Scenario scenario)  
	{
		if(scenario.isFailed()){
			byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		
	 	DriverManager driverManager = new DriverManager();

		if(driverManager.getDriver() != null){
			driverManager.getDriver().quit();
			driverManager.setDriver(null);
		}

		ServerManager serverManager = new ServerManager();

		if(serverManager.getServer() != null){
			serverManager.getServer().stop();
		}
	
	}
}
