package web.swipBox.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import web.swipBox.PageController.Page;
import web.swipBox.utilities.Utilities;

public class ShipmentDataPage extends Page {
	public void shipmentData() throws IOException {
		SwipBoxHOme Shipments=new SwipBoxHOme();
		Shipments.goToShipments();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click("DboardShipmentsData_XPATH");
		verifyEquals("DboardShipmentDataVerify_XPATH", "Shipment");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchShipmentData(String searchSubjectVal, String searchInput) throws InterruptedException, IOException {
		select("shipmentDataSub_CSS",searchSubjectVal);
		type("shipmentDataSearchInput_CSS",searchInput);
		click("shpDataSearchBtn_XPATH");
		Thread.sleep(1000);
		try {
			verifyEquals("shpDataSearchVerify_XPATH", searchInput);
			if(driver.findElement(By.cssSelector("#main-content-wraper > div > div > app-shipment > div > div > div.card > div > div > div > app-basic-data-table > form > ngx-datatable > div > datatable-body > datatable-selection > datatable-scroller > datatable-row-wrapper > datatable-body-row > div.datatable-row-center.datatable-row-group > datatable-body-cell:nth-child(1) > div > a")) != null) {
			click("shipedDataDetail_XPATH");
			Thread.sleep(1000);
			click("plusMoreDetail_XPAH");
			click("changeExpiryDshipped_XPATH");
			click("buttonExpiryDShipped_XPATH");
			click("confirmExpDateShipped_CSS");
			click("addCommentDataShipped_CSS");
			type("commentBoxShipped_XPATH","Test");
			click("cancelCommentShippedData_XPATH");
			click("closeDetailShippedData_CSS");
			}
			else {
				Utilities.captureScreenshot();
				Reporter.log("<br>");
				Reporter.log("<a target=\"_blank\" href=\"+TestUtil.screenshotName+ \"><img src=" + Utilities.screenshotName
						+ " height=500 width=500></img></a>");
				Reporter.log("<br>");
				test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
			test.log(LogStatus.INFO, "No record found for  : " + searchInput );
			}
		    } catch (Exception e) {
//		    	log.debug("No record found for " + searchSubjectVal + " : "+searchInput);
//		    	test.log(LogStatus.INFO, "No record found for  : " + searchInput );
		    }
		Thread.sleep(1000);
	    clearAllSearch();
	    analyzeLog("searchShipmentData");
		
	}
	public void clearAllSearch() {
		click("searchShipedDataClear_XPATH");
	}
	public void advanceSearch() {
		click("shippedDataAdvanceSarch_XPATH");
	}
	public void ShipmentDataDetail() throws InterruptedException {
		click("shpDataDetail_XPATH");
		Thread.sleep(3000);
	}
}
