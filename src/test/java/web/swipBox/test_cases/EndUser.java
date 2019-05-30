package web.swipBox.test_cases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import web.swipBox.PageController.BaseQuit;
import web.swipBox.PageObject.PageShipmentEndUser;
import web.swipBox.utilities.Utilities;

public class EndUser extends BaseQuit {
	@Test
	public void endUser() throws IOException {
		PageShipmentEndUser endUserNav=new PageShipmentEndUser();
		endUserNav.shipmentEndUser();
	}
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void searchEndUser(Hashtable<String,String> data) throws IOException, InterruptedException {
		PageShipmentEndUser searchEndUser=new PageShipmentEndUser();
		Thread.sleep(1000);
		searchEndUser.searchEndUser(data.get("filter"), data.get("search"));
		Thread.sleep(1000);
		searchEndUser.ShipmentEndUserSearchVerify(data.get("filter"),data.get("search"));
		Thread.sleep(1000);
		searchEndUser.clear();
		
	}
//	@Test
//	public void endUserDetail() throws IOException, InterruptedException {
//		ShipmentEndUserPage searchEndUser=new ShipmentEndUserPage();
//		searchEndUser.ShipmentEndUserDetail();
//		Thread.sleep(3000);
//	}
}
