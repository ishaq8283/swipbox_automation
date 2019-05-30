package web.swipBox.PageController;

import org.testng.annotations.AfterSuite;

public class BaseQuit extends Page

{
	@AfterSuite
	public void tearDown ()
	{
		
		driver.quit();
	}

}
