package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		
		
//		String projectPath = System.getProperty("user.dir");
//		String path = "/src/main/java/com/qa/config/config.properties";
//		String configPath = projectPath + path;
//		FileReader reader = new FileReader(configPath);
//		Properties prop = new Properties();
//		prop.load(reader);
	}

}
