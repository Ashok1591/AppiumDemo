package com.qa.mobile.utilities;

import java.io.File;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class ServerManager {
	
	private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<AppiumDriverLocalService>(); 
	TestUtils utils = new TestUtils();


	public AppiumDriverLocalService getServer() {
		return server.get();
	}

	public void startServer() {
		AppiumDriverLocalService server = WindowsGetAppiumService();
		server.start();

		if(server == null || !server.isRunning()) {
			utils.log().fatal("Appium server not started. ABORT!!!");
			throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
		}

		this.server.set(server);
		utils.log().info("Appium server started");
	}

	public AppiumDriverLocalService getAppiumServerDefault() {
		return	AppiumDriverLocalService.buildDefaultService();
	}

	public AppiumDriverLocalService WindowsGetAppiumService() {

		GlobalParams params = new GlobalParams();

		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingAnyFreePort()
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withLogFile(new File(params.getPlatformName() + "_" + params.getDeviceName() + File.separator + "Server.log")));
	}

}
