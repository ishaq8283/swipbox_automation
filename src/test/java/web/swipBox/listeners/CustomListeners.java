package web.swipBox.listeners;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.relevantcodes.extentreports.LogStatus;

import web.swipBox.PageController.Page;
import web.swipBox.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener
{

	public void onTestStart(ITestResult arg0) 
	{
		test = rep.startTest(arg0.getName().toUpperCase());
		// System.out.println(TestUtil.isTestRunnable(arg0.getName(), excel));
		// Y runmode run and N for Skip

	} 

	public void onTestSuccess(ITestResult args0) 
	{
		test.log(LogStatus.PASS, args0.getName().toUpperCase() + "PASS");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
				+ " height=500 width=500></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult args0)
	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try 
		{
			Utilities.captureScreenshot();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, args0.getName().toUpperCase() + "FAILED with exeption" + args0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		Reporter.log("Click to see the screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
				+ " height=500 width=500></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult args0)
	{
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, args0.getName().toUpperCase() + " Test was Skipped the test is RunMode No ");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context)
	{
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) 
	{
		// TODO Auto-generated method stub

	}

}
