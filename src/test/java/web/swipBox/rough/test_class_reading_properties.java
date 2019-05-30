package web.swipBox.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class test_class_reading_properties

{

	public static void main(String[] args) throws IOException 
	
	{
		// TODO Auto-generated method stub

		
		System.out.println(System.getProperty("user.dir"));
		
		Properties config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/config.properties");
		config.load(fis);
		
		Properties OR=new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
		OR.load(fis);
		System.out.println(config.getProperty("browser"));
		
		System.out.println(OR.getProperty("loginUserName_CSS"));
		System.out.println(OR.getProperty("loginPassword_CSS"));
		//Properties OR=new Properties();
	}

}
