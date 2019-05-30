package web.swipBox.PageObject;
import web.swipBox.PageController.Page;

public class Test_login extends Page

{

	public void test_login (String A_email , String Pas_code) throws InterruptedException 
	
	{
		type("loginUserName_CSS", A_email);
		type("loginPassword_CSS", Pas_code);
		click("LoginButton_CSS");
		
		Thread.sleep(1000);
		//quit();
	}

}
