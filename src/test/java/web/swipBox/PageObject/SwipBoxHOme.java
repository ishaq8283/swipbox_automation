package web.swipBox.PageObject;

import org.openqa.selenium.By;

import web.swipBox.PageController.Page;

public class SwipBoxHOme extends Page {
//	WebDriver driver;
//	public SwipBoxHOme(WebDriver driver) {
//		this.driver=driver;
//	}
	public void toggleDashboard() {
		driver.findElement(By.xpath("//*[@id=\"sidebarToggle\"]/i")).click();
	}
	public void goToDashboard() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[1]/a/span")).click();
	}

	public void goToShipments() {
		driver.findElement(By.cssSelector("#outer-most-wrapper > div.app-sidebar > app-sidebar > div.sidebar-content.ps-container.ps-theme-default > div.nav-container > ul > li:nth-child(2) > a > span")).click();
		analyzeLog("goToShipments");
	}

	public void goToServicePoints() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[3]/a/span")).click();
	}

	public void goToCarriers() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[4]/a/span")).click();
	}

	public void goToPartners() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[5]/a/span")).click();
	}
	public void goToUserSettings() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[6]/a/span")).click();
	}

	public void goToAdmin() {
		driver.findElement(By.xpath("//*[@id=\"outer-most-wrapper\"]/div[1]/app-sidebar/div[2]/div[1]/ul/li[7]/a/span")).click();
	}

}
