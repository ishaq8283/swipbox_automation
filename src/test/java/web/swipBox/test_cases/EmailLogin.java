package web.swipBox.test_cases;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.swipBox.PageController.BaseQuit;
import web.swipBox.PageController.Page;
import web.swipBox.PageObject.LoginSwipBoxHome;
public class EmailLogin extends BaseQuit {

	@Test(dataProvider="getData")
	public void emailLogin(String email,String password) {
		LoginSwipBoxHome login=new LoginSwipBoxHome();
		//Remove last character from password
		String password1 = password.substring(0, password.length() - 1);
			login.doLogin(email, password1);
		
	}
	
	@DataProvider
	public Object[][] getData() 
	
	{
		//Page page = new Page();
		String sheetName = "EmailLogin";
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