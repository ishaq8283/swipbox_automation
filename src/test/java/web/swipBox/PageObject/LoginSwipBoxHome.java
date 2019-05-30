package web.swipBox.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;

import web.swipBox.PageController.Page;


public class LoginSwipBoxHome extends Page {
	public void doLogin(String userName, String password) {
		type("loginUserName_CSS", userName);
		type("loginPassword_CSS", password);
		click("LoginButton_CSS");
		try {
			verifyEquals("DashBoardMainPage_XPATH", "Dashboard");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void goToTermAndCondition() {
		driver.findElement(By.cssSelector(
				"div > app-login > div > div > div.card-body > p > a:nth-child(1)"))
				.click();
	}

	public void goToPrivacyPolicy() {
		driver.findElement(By.cssSelector(
				"div > app-login > div > div > div.card-body > p > a:nth-child(2)"))
				.click();
	}

	public void goToForgotPassword() {
		driver.findElement(By.cssSelector(
				"div > app-login > div > div > div.card-body > h6 > a"))
				.click();
	}
}
