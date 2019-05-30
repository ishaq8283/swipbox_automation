package web.swipBox.test_cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.swipBox.PageController.BaseQuit;
import web.swipBox.PageController.Page;
import web.swipBox.PageObject.Test_login;

public class Login_page extends BaseQuit 

{

	
	@Test(dataProvider="getData")
	public void login_page (String email , String password) throws InterruptedException
	
	{	
		Test_login obj = new Test_login();
		obj.test_login(email, password);	
		
	}
	
	
	@DataProvider
	public Object[][] getData() 
	
	{
		//Page page = new Page();
		String sheetName = "Login_page";
		int rows = Page.excel.getRowCount(sheetName);
		int cols = Page.excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) 
		{
			for (int colNum = 0; colNum < cols; colNum++) 
			{
				data[rowNum - 2][colNum] = Page.excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
	
}
