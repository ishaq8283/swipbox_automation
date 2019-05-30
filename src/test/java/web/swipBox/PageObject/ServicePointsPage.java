package web.swipBox.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;

import web.swipBox.PageController.Page;

public class ServicePointsPage extends Page {
	public void servicePointsPage() throws IOException {
		SwipBoxHOme servicePoints = new SwipBoxHOme();
		servicePoints.goToServicePoints();
		click("ServicePointPage_XPATH");
		verifyEquals("verifyServicePointPage_XPATH", "Service points");

	}

	public void addNewServicePointBasic(String locationName, String Address1, String Address2,
			String locationDescription, String zip, String City, String latitude, String longitude, String timeZone,
			String municipality, String locationtype, String servicePointGroup, String locationOwner,
			String maintenanceProvider, String supportProvider, String hardwareType, String hardwareModel,
			String hrdPosistion, String hrdlocationDescript, String hrdOpeningHours, String selectPartner,
			String alternativeSPID, String tag1, String tag2, String tag3) {
		click("addNewServicePoint_CSS");
		type("SPLocationName_CSS", locationName);
		type("SPLocationAddress1_CSS", Address1);
		type("SPLocationAddress2_CSS", Address2);
		type("SPZIP_CSS", zip);
		type("SPCity_CSS", City);
		type("SPLatitude_CSS", latitude);
		type("SPLongitude_CSS", longitude);

		click("SPCalculate_CSS");
		select("SPTimeZone_CSS", timeZone);
		click("municipility_CSS");
		comboSelect("municipilityInput_CSS", municipality);
		click("SPlocationType_CSS");
		comboSelect("SPlocationInput_CSS", locationtype);
		type("SPlocationDescription_CSS", locationDescription);
		select("SPLocationOwner_CSS", locationOwner);
		select("SPMaintananceProvider_CSS", maintenanceProvider);
		select("SPSupportProvider_CSS", supportProvider);
		click("SPActiveStatus_CSS");

		// Screenshot
		testcaseScreenshot();
	}

	public void addHardWareUnit(String locationName, String Address1, String Address2, String locationDescription,
			String zip, String City, String latitude, String longitude, String timeZone, String municipality,
			String locationtype, String servicePointGroup, String locationOwner, String maintenanceProvider,
			String supportProvider, String hardwareType, String hardwareModel, String hrdPosistion,
			String hrdlocationDescript, String hrdOpeningHours, String selectPartner, String alternativeSPID,
			String tag1, String tag2, String tag3) {
		click("SPHardwareUnit_CSS");

		click("SPAddHardwareUnit_CSS");
		select("SPHardwareType_CSS", hardwareType);
		select("SPHardModel_CSS", hardwareModel);
		select("SPHardPosition_CSS", hrdPosistion);
		type("SPHardLocationDesc_CSS", locationDescription);
		type("SPHardOpeningHours_CSS", hrdOpeningHours);
		click("SPHardOpenInstallationStatus_CSS");
		click("SPHardActiveStatus_CSS");

		// Screenshot
		testcaseScreenshot();
	}

	public void terminatehardWareUnit() {
		click("SPHardUnitSetting_CSS");
		click("SPHardUnitTerminate_CSS");
	}

	public void partnerSetting(String partner, String alternativeService, String tag1, String tag2, String tag3) {
		click("SPPartnerSetting_CSS");
		select("SPSelectPartner_CSS", partner);
		type("SPAlternativeSPID_CSS", alternativeService);
		type("SPTag1_CSS", tag1);
		type("SPTag2_CSS", tag2);
		type("SPTag3C_CSS", tag3);
		// Screenshot
		testcaseScreenshot();
	}

	public void terminateServicePoint() {
		click("SPMore_CSS");
		click("TerminateServicePoint_CSS");
		click("doYouWantTermintateSP_CSS");

		click("terminateAndSaveSP_CSS");
		click("ServicePointPage_XPATH");

	}

	public void saveServicePoint() {
		click("SPSave_CSS");

		// ScreenshotWWW
		testcaseScreenshot();

		click("doyouWanttoSaveSP_XPATH");

		click("ServicePointPage_XPATH");

	}

	public void closeServicePoint() {
		click("ServicePointClose_CSS");
		click("leaveSPincomplete_CSS");
	}

	public void ServicefilterByType(String byType) {
		select("typeServicePoint_CSS", byType);
	}

	public void ServicefilterByGroup(String byGroup) {
		select("groupServicePoint_CSS", byGroup);
	}

	public void ServicefilterByPartner(String byPartner) {
		select("partnerServicePoint_CSS", byPartner);
	}

	public void ServicefilterByStatus(String ByStatus) {
		select("statusServicePoint_CSS", ByStatus);
	}

	public void filterServicePoint(String filterType, String filterInput) {
		type("filterServicePoint_CSS", filterInput);
		click("searchServicePoint_CSS");
		if (filterType != null) {
			if (filterType.equals("id")) {
				try {
					verifyEquals("verifyIdSP_XPATH", filterInput);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (filterType.equals("location")) {
					try {
						verifyEquals("verifyLocationSP_XPATH", filterInput);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (filterType.equals("type")) {
					try {
						verifyEquals("verifyTypeSP_XPATH", filterInput);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (filterType.equals("address")) {
					try {
						verifyEquals("verifyAddressSP_XPATH", filterInput);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					verifyEquals("verifyfilterSP_XPATH", filterInput);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			driver.findElement(By.cssSelector("dt-header > div > div > div > div.p-0 > form > div > div > input")).clear();

		}

	}

	public void clearServicePointfilter() throws InterruptedException {
		Thread.sleep(2000);
		clearElement("filterServicePoint_CSS");
		Thread.sleep(2000);
	}

	public void editServicePoint() {

	}

}
