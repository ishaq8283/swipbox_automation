package web.swipBox.test_cases;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.swipBox.PageController.BaseQuit;
import web.swipBox.PageController.Page;
import web.swipBox.PageObject.ServicePointsPage;

public class ServicePointFilters extends BaseQuit {
	@Test(priority = 1)
	public void servicePoints() throws IOException {
		ServicePointsPage servicePoints = new ServicePointsPage();
		servicePoints.servicePointsPage();
	}

	@Test(priority = 2,dependsOnMethods = { "servicePoints" }, dataProvider = "getData")
	public void servicePointsFiltersById(String id, String locationName, String address, String Type)
			throws IOException, InterruptedException {
		ServicePointsPage servicePoints = new ServicePointsPage();
		servicePoints.filterServicePoint("id", id);
		servicePoints.clearServicePointfilter();
	}


	
	@Test(priority = 3,dependsOnMethods = { "servicePointsFiltersById" }, dataProvider = "getData")
	public void servicePointsFiltersByType(String id, String locationName, String address, String Type)
			throws IOException, InterruptedException {
		ServicePointsPage servicePoints = new ServicePointsPage();
		servicePoints.filterServicePoint("type", Type);
		Thread.sleep(1000);
		servicePoints.clearServicePointfilter();
	}
	
	@Test(priority = 4,dependsOnMethods = { "servicePointsFiltersByType" },dataProvider = "getData")
	public void servicePointsFiltersBylocation(String id, String locationName, String address, String Type)
			throws IOException, InterruptedException {
		ServicePointsPage servicePoints = new ServicePointsPage();
		servicePoints.filterServicePoint("location", locationName);
		servicePoints.clearServicePointfilter();
	}
	
	@Test(priority = 5,dependsOnMethods = { "servicePointsFiltersBylocation" }, dataProvider = "getData")
	public void servicePointsFiltersByAddress(String id, String locationName, String address, String Type)
			throws IOException, InterruptedException {
		ServicePointsPage servicePoints = new ServicePointsPage();
		servicePoints.filterServicePoint("address", address);
		servicePoints.clearServicePointfilter();
		
	}

	

	@DataProvider
	public Object[][] getData() {
		String sheetName = "ServicePointFilters";
		int rows = Page.excel.getRowCount(sheetName);
		int cols = Page.excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = Page.excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
