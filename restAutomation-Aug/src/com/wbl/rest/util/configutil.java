package com.wbl.rest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configutil {
	
	public static String URI;
	
	
	public static void loadproperties(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir")+"//resources//config.properties"));
		URI= prop.getProperty("uri");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.getProperty("uri");
		
	}

}
