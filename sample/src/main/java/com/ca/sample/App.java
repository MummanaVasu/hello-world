package com.ca.sample;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public WebDriver driver = null;
	public ThreadLocal<WebDriver> webDriver = null;
	
	@BeforeTest
	public void main()
	{
		try{

			//one way to star the node 
			/*			CommandLine command = new CommandLine("cmd");
			command.addArgument("/c");
			command.addArgument("java");
			command.addArgument("-jar");
			command.addArgument("C:\\Users\\submu03\\Downloads\\selenium-server-standalone-2.53.0.jar");
			command.addArgument("-role");
			command.addArgument("hub");
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command, resultHandler);*/


			//2nd way to star the grid
			GridHubConfiguration config = new GridHubConfiguration();
			config.setHost("localhost");
			config.setPort(4444);
			Hub hub = new Hub(config);

			System.out.println(hub.getHost());
			hub.start();

			/*			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setPlatform(Platform.WINDOWS);
			capability.setVersion("46.0");
			WebDriver driver = new RemoteWebDriver(new URL("http://10.134.5.193:5566/wd/hub"), capability);
			driver.get("https://www.gmail.com");*/

			/*			//Thread.sleep(10000);
			driver.quit();
			hub.stop();
			//System.exit(0);

			Thread.sleep(5000);
			hub.start();

			driver = new RemoteWebDriver(new URL("http://10.131.77.235:5566/wd/hub"), capability);
			driver.get("https://www.gmail.com");
			System.exit(0);

			//System.out.println(hub.);
			 */			


		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void startTest() throws Exception
	{
		
		webDriver = new ThreadLocal<WebDriver>();

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setPlatform(Platform.WINDOWS);
		capability.setVersion("46.0");
		driver = new RemoteWebDriver(new URL("http://10.131.77.235:5566/wd/hub"), capability);
		//driver = new htmlu
	}

	//This test method declares that its data should be supplied by the Data Provider
	// "getdata" is the function name which is passing the data
	// Number of columns should match the number of input parameters
	@Test(dataProvider="getData")
	public void setData(String username, String password) throws Exception
	{		
		webDriver.set(driver);
		driver.get("https://www.gmail.com");
		
		System.out.println("you have provided username as::"+username);
		System.out.println("you have provided password as::"+password);
		
		Thread.sleep(5000);
		System.out.println("checking");
	}
	
	@AfterMethod
	public void testAfterM(){
		driver.quit();
		webDriver.set(null);
	}
	
	@AfterTest
	public void testAfterT(){
		System.out.println("Parallel testing is done");
	}

	@DataProvider(parallel=true)
	public Object[][] getData()
	{
		//Rows - Number of times your test has to be repeated.
		//Columns - Number of parameters in test data.
		Object[][] data = new Object[3][2];

		// 1st row
		data[0][0] ="sampleuser1";
		data[0][1] = "abcdef";

		// 2nd row
		data[1][0] ="testuser2";
		data[1][1] = "zxcvb";

		// 3rd row
		data[2][0] ="guestuser3";
		data[2][1] = "pass123";

		return data;
	}
}
