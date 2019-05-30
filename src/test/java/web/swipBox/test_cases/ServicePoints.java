package web.swipBox.test_cases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.swipBox.PageController.Page;
import web.swipBox.PageObject.ServicePointsPage;
public class ServicePoints {
	
	
	@Test (priority=1)
	public void servicePoints() throws IOException {
		ServicePointsPage servicePoints=new ServicePointsPage();
		servicePoints.servicePointsPage();
	}
	
	@Test(priority=2,dataProvider = "getData")
	public void addNewServicePointandSave(String locationName, String address1,String address2, String locationDescription,String zip, String city, String latitude, String longitude,String timezone, String municipality,String locationtype, String placesInSPG, String locationOwner, String maintenanceProvider,String supportProvider,String hardwareType,String hardwareModel,String hrdPosistion,String hrdlocationDescript,String hrdOpeningHours,String selectPartner,String alternativeSPID,String tag1,String tag2,String tag3)throws IOException, InterruptedException {
		System.out.println("Service point is running");
		Thread.sleep(2000);
		ServicePointsPage servicePoints=new ServicePointsPage();
		servicePoints.addNewServicePointBasic(locationName,address1, address2,locationDescription,zip,city, latitude,longitude,timezone,municipality,locationtype,placesInSPG,locationOwner,maintenanceProvider,supportProvider,hardwareType,hardwareModel,hrdPosistion,hrdlocationDescript	,hrdOpeningHours,selectPartner,alternativeSPID,tag1,tag2,tag3);
		servicePoints.addHardWareUnit(locationName,address1, address2,locationDescription,zip,city, latitude,longitude,timezone,municipality,locationtype,placesInSPG,locationOwner,maintenanceProvider,supportProvider,hardwareType,hardwareModel,hrdPosistion,hrdlocationDescript	,hrdOpeningHours,selectPartner,alternativeSPID,tag1,tag2,tag3);
		servicePoints.partnerSetting(selectPartner,alternativeSPID,tag1,tag2,tag3);
		Thread.sleep(2000);
		servicePoints.saveServicePoint();
		servicePoints.filterServicePoint("",locationName);
		Thread.sleep(2000);
		
	}
	
	@Test(priority=3,dataProvider = "getData")
	public void addNewServicePointandTerminate(String locationName, String address1,String address2, String locationDescription,String zip, String city, String latitude, String longitude,String timezone, String municipality,String locationtype, String placesInSPG, String locationOwner, String maintenanceProvider,String supportProvider,String hardwareType,String hardwareModel,String hrdPosistion,String hrdlocationDescript,String hrdOpeningHours,String selectPartner,String alternativeSPID,String tag1,String tag2,String tag3)throws IOException, InterruptedException {
		System.out.println("Service point is running");
		Thread.sleep(2000);
		ServicePointsPage servicePoints=new ServicePointsPage();
		servicePoints.addNewServicePointBasic(locationName,address1, address2,locationDescription,zip,city, latitude,longitude,timezone,municipality,locationtype,placesInSPG,locationOwner,maintenanceProvider,supportProvider,hardwareType,hardwareModel,hrdPosistion,hrdlocationDescript	,hrdOpeningHours,selectPartner,alternativeSPID,tag1,tag2,tag3);
		servicePoints.addHardWareUnit(locationName,address1, address2,locationDescription,zip,city, latitude,longitude,timezone,municipality,locationtype,placesInSPG,locationOwner,maintenanceProvider,supportProvider,hardwareType,hardwareModel,hrdPosistion,hrdlocationDescript	,hrdOpeningHours,selectPartner,alternativeSPID,tag1,tag2,tag3);
		servicePoints.partnerSetting(selectPartner,alternativeSPID,tag1,tag2,tag3);
		Thread.sleep(2000);
		servicePoints.terminateServicePoint();
		servicePoints.filterServicePoint("",locationName);
	}
	
	
	@DataProvider
	public Object[][] getData() {
		Page page=new Page();
		String sheetName = "ServicePoints";
		int rows = page.excel.getRowCount(sheetName);
		int cols = page.excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] =page.excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
