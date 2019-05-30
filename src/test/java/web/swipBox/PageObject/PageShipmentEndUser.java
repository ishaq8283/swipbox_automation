package web.swipBox.PageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import web.swipBox.PageController.Page;

public class PageShipmentEndUser extends Page {
	public void shipmentEndUser() throws IOException {

		//// *[@id="outer-most-wrapper"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[2]/ul/li[1]
		String Shipmentd = driver
				.findElement(
						By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[2]/ul/li[1]"))
				.getAttribute("class");
		if (Shipmentd != "is-shown") {
			SwipBoxHOme Shipments = new SwipBoxHOme();
			Shipments.goToShipments();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click("DboardEndUserData_XPATH");

		verifyEquals("verifyEndUserData_XPATH", "End-users search");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchEndUser(String searchFilterVal, String searchInput) throws IOException, InterruptedException {
		select("endUserFilter_XPATH", searchFilterVal);
		type("endUserSearch_XPATH", searchInput);
		Thread.sleep(1000);
		click("endUserSearchBtn_XPATH");
		
			Thread.sleep(1000);
		
	}

	public void ShipmentEndUserSearchVerify(String filter,String Search) throws InterruptedException, IOException {
		if(filter=="User ID") {
			verifyEquals("endUserDetailColUID_XPATH", Search);
		
		}
			if(filter=="Phone number") {
				verifyEquals("endUserDetailColPhone_XPATH", Search);
			}
			
			if(filter=="End-user name") {
				verifyEquals("endUserNoRecord_XPATH", Search);
			}	
		
			
	}

	public void clear() {
		click("endUserSearchClear_XPATH");
	}
}
