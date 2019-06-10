package web.swipBox.PageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import web.swipBox.utilities.ExcelReader;
import web.swipBox.utilities.ExtentManager;
import web.swipBox.utilities.Utilities;
public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static FluentWait<WebDriver> wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Jenkins Browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
//	                options.addArguments("headless");
//	                options.addArguments("window-size=1200x600");
//				driver = new ChromeDriver(options);

				 driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,
					TimeUnit.SECONDS);
			wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(10, TimeUnit.SECONDS)
				       .pollingEvery(2, TimeUnit.SECONDS)
				       .ignoring(NoSuchElementException.class)
				       .withMessage("User defined wait timed out after 10 seconds");
			
			//wait = new WebDriverWait(driver, 10);
			System.out.println("================== BROWSER LOGS =======================");

			LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
			for (LogEntry entry : logEntries) {
				System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage()
						+ " " + entry.toString());
			}
			System.out.println("=======================================================");
		}
	}

	public static void quit() {

		driver.quit();

	}

	// Browser Logs
	public void analyzeLog(String Test) {
		System.out.println("================== Test " + Test + " =======================");
		System.out.println("================== Test case " + Test + " executed =======================");
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.toString());
		}
		System.out.println("***************************************************************************");
	}

	// Common Keywords
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty(locator)))).click();
		//	driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(locator)))).click();
		} else if (locator.endsWith("_ID")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(OR.getProperty(locator)))).click();
		}
		log.debug("Clicking on an Element : " + locator);
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		log.debug("Typing in an Element : " + locator + " entered value as : " + value);

		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.debug("Selecting from an element : " + locator + " value as : " + value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	}

	public void comboSelect(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		type(locator, value);
		Actions actionObject = new Actions(driver);
		actionObject = actionObject.sendKeys(Keys.ARROW_DOWN); // ASSIGN the return or you lose this event.
		actionObject.perform();
		actionObject = actionObject.sendKeys(Keys.ENTER);
		actionObject.perform();

		log.debug("Selecting from an element : " + locator + " value as : " + value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	static WebElement inputField;

	public static void clearElement(String locator) {
		if (locator.endsWith("_CSS")) {
			inputField = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			inputField = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			inputField = driver.findElement(By.id(OR.getProperty(locator)));
		}
		inputField.clear();
	}

	public static void verifyEquals(String locator, String ExpectedText) throws IOException {
		String Actualltext = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		ExpectedText = driver
				.findElement(By.xpath(OR.getProperty(locator) + "[contains(text()," + "'" + ExpectedText + "')]"))
				.getAttribute("innerText");

		try {
			Assert.assertEquals(Actualltext, ExpectedText);
			test.log(LogStatus.INFO, "You are on right place, the text you are searching is : " + ExpectedText);
			Utilities.captureScreenshot();
			Reporter.log("<br>" + "Verification Success and you got text on page : " + ExpectedText + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
					+ " height=500 width=500></img></a>");
			Reporter.log("<br>");
			test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
		} catch (Throwable t) {

			Utilities.captureScreenshot();
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
					+ " height=500 width=500></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			test.log(LogStatus.FAIL, "Varification failed with exeption" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

			test.log(LogStatus.INFO, Actualltext + ExpectedText);
		}

	}

	public static void testcaseScreenshot() {
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
				+ " height=500 width=500></img></a>");
		Reporter.log("<br>");
		test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void verifyEqualTable(String Actualltext, String ExpectedText) throws IOException {
		// String Actualltext = locator.getText();
//		ExpectedText = driver
//				.findElement(By.xpath(OR.getProperty(locator) + "[contains(text()," + "'" + ExpectedText + "')]"))
		// .getAttribute("innerText");

		try {
			Assert.assertEquals(Actualltext, ExpectedText);
			test.log(LogStatus.INFO, "You are on right place, the text you are searching is : " + ExpectedText);
			Utilities.captureScreenshot();
			Reporter.log("<br>" + "Verification Success and you got text on page : " + ExpectedText + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
					+ " height=500 width=500></img></a>");
			Reporter.log("<br>");
			test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
		} catch (Throwable t) {

			Utilities.captureScreenshot();
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
					+ " height=500 width=500></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			test.log(LogStatus.FAIL, "Varification failed with exeption" + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

			test.log(LogStatus.INFO, Actualltext + ExpectedText);
		}

	}

}
